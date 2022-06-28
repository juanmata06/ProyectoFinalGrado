package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import config.ConstantsApi;
import model.Token;

public class TokenDao {
	private Connection bbddConnection;

	public void connect() throws SQLException, ClassNotFoundException {
		bbddConnection = DriverManager.getConnection(ConstantsApi.CONNECTION, ConstantsApi.USER_CONNECTION,
				ConstantsApi.PASS_CONNECTION);
	}

	public void disconnect() throws SQLException {
		if (bbddConnection != null) {
			bbddConnection.close();
		}
	}

	public Token crearToken(int id) throws SQLException, ClassNotFoundException {
		// Establecemos la clave secreta
		String key = "password";

		// Generamos el algotirmo
		Algorithm alg = Algorithm.HMAC256(key);

		// Generamos el token a partir del id
		String valor = JWT.create().withIssuer("Petkeepers") // EMISOR DE TOKEN
				.withSubject(String.valueOf(id)) // ID
				.sign(alg);

		return new Token(valor);
	}

	public boolean verificarToken(String token) throws SQLException, ClassNotFoundException {

		// Establecemos la clave secreta
		String key = "password";

		// Generamos el algotirmo
		Algorithm alg = Algorithm.HMAC256(key);

		// Verificamos el token a partir del issuer
		JWTVerifier verifier = JWT.require(alg).withIssuer("Petkeepers").build();
		try {
			verifier.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}

		return false;
	}

	public int obtenerUsuarioByToken(String token) throws SQLException, ClassNotFoundException {

		// Establecemos la clave secreta
		String key = "password";

		// Generamos el algotirmo
		Algorithm alg = Algorithm.HMAC256(key);

		// Devolvemos el id de usuario
		try {
			DecodedJWT originToken = JWT.decode(token);
			return Integer.parseInt(originToken.getSubject());
		} catch (JWTDecodeException e) {
			e.printStackTrace();
		}

		return -1;
	}
}
