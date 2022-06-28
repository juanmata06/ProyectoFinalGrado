package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.ConstantsApi;
import model.ComentarioMascota;

public class ComentarioMascotaDao {
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
	
	public ComentarioMascota getComentarioMascota(int id) throws SQLException, ClassNotFoundException {
		ComentarioMascota comentarioObtenido = new ComentarioMascota();
		
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_COMENTARIO_MASCOTA);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			comentarioObtenido = new ComentarioMascota(
					rs.getInt("id"),
					rs.getString("comentario"),
					rs.getInt("id_mascota"),
					rs.getInt("id_suscriptor"),
					rs.getString("fecha"),
					"nombre"
			);

		}

		return comentarioObtenido;
	}
}
