package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConstantsApi;
import model.ComentarioServicio;

public class ComentarioServicioDao {
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
	
	public ComentarioServicio getComentarioServicio(int id) throws SQLException, ClassNotFoundException {
		ComentarioServicio comentarioObtenido = new ComentarioServicio();
		
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_COMENTARIO_SERVICIO);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			comentarioObtenido = new ComentarioServicio(
					rs.getInt("id"),
					rs.getString("comentario"),
					rs.getInt("id_servicio"),
					rs.getInt("id_cliente"),
					rs.getString("fecha"),
					"nombre"
			);

		}

		return comentarioObtenido;
	}
}
