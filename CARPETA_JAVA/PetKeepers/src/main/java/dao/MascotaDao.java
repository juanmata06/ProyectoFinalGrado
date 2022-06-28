package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import config.ConstantsApi;
import externalLibrary.MyFunctions;
import model.Mascota;
import model.Objeto;
import model.Servicio;
import model.ComentarioMascota;

public class MascotaDao {
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

	public ArrayList<Integer> getMascotasByClient(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Integer> mascotas = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_MASCOTAS_BY_CLIENT_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int mascotaObtenida = rs.getInt("id");
			mascotas.add(mascotaObtenida);
		}

		return mascotas;
	}

	public Mascota getMascota(int id) throws SQLException, ClassNotFoundException {
		Mascota mascotaObtenida = new Mascota();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_MASCOTA);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			mascotaObtenida = new Mascota(
					rs.getInt("id"), 
					rs.getString("nombre"), 
					rs.getString("tipo"), 
					rs.getString("descripcion"),
					rs.getDouble("precio"), 
					rs.getDouble("puntuacion"), 
					rs.getBoolean("activo"),
					rs.getString("imagenes"), 
					rs.getInt("id_cliente"),
					rs.getString("nombre_mascota"));

		}

		return mascotaObtenida;
	}

	public ArrayList<Mascota> getMascotas() throws SQLException, ClassNotFoundException {
		ArrayList<Mascota> mascotas = new ArrayList<>();

		String select = ConstantsApi.GET_MASCOTAS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			Mascota mascotaObtenida = getMascota(rs.getInt("id"));
			mascotas.add(mascotaObtenida);
		}

		return mascotas;
	}

	public void postMascota(Mascota mascota) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_MASCOTA);

		ps.setString(1, mascota.getNombre());
		ps.setString(2, mascota.getTipo());
		ps.setString(3, mascota.getNombre_mascota());
		ps.setString(4, mascota.getDescripcion());
		ps.setDouble(5, mascota.getPrecio());
		ps.setDouble(6, mascota.getPuntuacion());
		ps.setBoolean(7, mascota.isActivo());
		ps.setString(8, mascota.getImagenes());
		ps.setInt(9, mascota.getUsuario());

		ps.execute();
		ps.close();
	}

	public void updateMascota(int id, Mascota mascota) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_MASCOTA);

		ps.setString(1, mascota.getNombre());
		ps.setString(2, mascota.getTipo());
		ps.setString(3, mascota.getNombre_mascota());
		ps.setString(4, mascota.getDescripcion());
		ps.setDouble(5, mascota.getPrecio());
		ps.setDouble(6, mascota.getPuntuacion());
		ps.setBoolean(7, mascota.isActivo());
		ps.setString(8, mascota.getImagenes());
		ps.setInt(9, mascota.getUsuario());
		ps.setInt(10, id);

		ps.execute();
		ps.close();
	}

	public void deleteMascota(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_MASCOTA);

		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}

	// Endpoints adicionales:
	public ArrayList<Mascota> getMascotasAdquiridasBySuscriptor(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Mascota> mascotas = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_MASCOTAS_BY_SUSCRIPTOR_ID);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Mascota mascotaObtenida = getMascota(rs.getInt("id_mascota"));
			System.out.println(mascotaObtenida);
			mascotas.add(mascotaObtenida);
		}

		return mascotas;
	}
	
	public ArrayList<ComentarioMascota> getComentariosMascota(int id) throws SQLException, ClassNotFoundException {
		ArrayList<ComentarioMascota> comentarios = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_COMENTARIOS_MASCOTA);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		
		while (rs.next()) {
			ComentarioMascotaDao dao = new ComentarioMascotaDao();
			dao.connect();
			
			ComentarioMascota comentarioObtenido = dao.getComentarioMascota(rs.getInt("id"));
			comentarios.add(comentarioObtenido);
			
			dao.disconnect();
		}
		

		return comentarios;
	}
	
	public void postComentarioMascota(ComentarioMascota comentario) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_COMENTARIO_MASCOTA);

		ps.setString(1, comentario.getComentario());
		ps.setInt(2, comentario.getId_mascota());
		ps.setInt(3, comentario.getId_suscriptor());
		ps.setString(4, comentario.getFecha());
		
		ps.execute();
		ps.close();
	}
	
	public ArrayList<Mascota> getTop5() throws SQLException, ClassNotFoundException {
		ArrayList<Mascota> mascotas = new ArrayList<>();

		String select = ConstantsApi.GET_TOP_5_MASCOTAS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			mascotas.add(getMascota(rs.getInt("id")));
		}

		return mascotas;
	}
	
	public ArrayList<Mascota> getMascotasByTipo(String tipo) throws SQLException, ClassNotFoundException {
		ArrayList<Mascota> mascotas = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_MASCOTAS_BY_TIPO);
		ps.setString(1, tipo);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Mascota mascotaObtenida = getMascota(rs.getInt("id"));
			mascotas.add(mascotaObtenida);
		}

		return mascotas;
	}
	
	public ArrayList<Mascota> getMascotasByClientId(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Mascota> mascotas = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_MASCOTAS_BY_CLIENT_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Mascota mascotaObtenida = getMascota(rs.getInt("id"));
			mascotas.add(mascotaObtenida);
		}

		return mascotas;
	}
}