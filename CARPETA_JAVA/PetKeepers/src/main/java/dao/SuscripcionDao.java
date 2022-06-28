package dao;

//CLASES
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//DEPENDENCIAS
//OBJETOS
import config.ConstantsApi;
import model.Suscripcion;
//ECEPCIONES
import java.sql.SQLException;

public class SuscripcionDao {
	private Connection bbddConnection;

	public void connect() throws SQLException, ClassNotFoundException {
		bbddConnection = DriverManager.getConnection(
				ConstantsApi.CONNECTION, 
				ConstantsApi.USER_CONNECTION,
				ConstantsApi.PASS_CONNECTION
		);
	}

	public void disconnect() throws SQLException {
		if (bbddConnection != null) {
			bbddConnection.close();
		}
	}

	public ArrayList<Suscripcion> getSuscripciones() throws SQLException, ClassNotFoundException {
		ArrayList<Suscripcion> suscripciones = new ArrayList<>();

		String select = ConstantsApi.GET_SUSCRIPCIONES;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {

			Suscripcion suscripcionObtenida = new Suscripcion(rs.getInt("id"), rs.getString("tiempo"),
					rs.getString("precio"), rs.getString("descripcion"));
			suscripciones.add(suscripcionObtenida);

		}

		return suscripciones;
	}
	
	public Suscripcion getSuscripcion(int id) throws SQLException, ClassNotFoundException {
		Suscripcion suscripcionObtenida = new Suscripcion();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SUSCRIPCION_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			suscripcionObtenida = new Suscripcion(
					rs.getInt("id"), 
					rs.getString("tiempo"),
					rs.getString("precio"),
					rs.getString("descripcion")
			);
		}

		return suscripcionObtenida;
	}

	public void postSuscripcion(Suscripcion suscripcion) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_SUSCRIPCION);

		ps.setString(1, suscripcion.getTiempo());
		ps.setString(2, suscripcion.getPrecio());
		ps.setString(3, suscripcion.getDescripcion());

		ps.execute();
		ps.close();
	}

	public void updateSuscripcion(int id, Suscripcion suscripcion) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_SUSCRIPCION);

		ps.setString(1, suscripcion.getTiempo());
		ps.setString(2, suscripcion.getPrecio());
		ps.setString(3, suscripcion.getDescripcion());
		ps.setInt(4, id);

		ps.execute();
		ps.close();
	}
	
	public void deleteSuscripcion(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_SUSCRIPCION);

		ps.setInt(1, id);
		ps.execute();
		ps.close();

	}

}
