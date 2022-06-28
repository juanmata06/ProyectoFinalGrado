package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import config.ConstantsApi;
import model.Servicio;
import externalLibrary.MyFunctions;
import model.ComentarioServicio;

public class ServicioDao {
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

	public ArrayList<Integer> getServiciosBySuscriptor(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Integer> servicios = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SERVICIOS_BY_SUSCRIPTOR_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int servicioObtenido = rs.getInt("id");
			servicios.add(servicioObtenido);
		}

		return servicios;
	}

	public Servicio getServicio(int id) throws SQLException, ClassNotFoundException {
		Servicio servicioObtenido = new Servicio();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SERVICIO);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			servicioObtenido = new Servicio(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"),
					rs.getDouble("precio"), rs.getDouble("puntuacion"), rs.getBoolean("activo"),
					rs.getString("imagenes"), rs.getInt("id_suscriptor"));
		}

		return servicioObtenido;
	}

	public ArrayList<Servicio> getServicios() throws SQLException, ClassNotFoundException {
		ArrayList<Servicio> servicios = new ArrayList<>();

		String select = ConstantsApi.GET_SERVICIOS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			servicios.add(getServicio(rs.getInt("id")));
		}

		return servicios;
	}

	public void postServicio(Servicio servicio) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_SERVICIO);

		ps.setString(1, servicio.getNombre());
		ps.setString(2, servicio.getDescripcion());
		ps.setDouble(3, servicio.getPrecio());
		ps.setDouble(4, servicio.getPuntuacion());
		ps.setBoolean(5, servicio.isActivo());
		ps.setString(6, servicio.getImagenes());
		ps.setInt(7, servicio.getSuscriptor());

		ps.execute();
		ps.close();
	}

	public void updateServicio(int id, Servicio servicio) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_SERVICIO);

		ps.setString(1, servicio.getNombre());
		ps.setString(2, servicio.getDescripcion());
		ps.setDouble(3, servicio.getPrecio());
		ps.setDouble(4, servicio.getPuntuacion());
		ps.setBoolean(5, servicio.isActivo());
		ps.setString(6,servicio.getImagenes());
		ps.setInt(7, servicio.getSuscriptor());
		ps.setInt(8, id);

		ps.execute();
		ps.close();
	}

	public void deleteServicio(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_SERVICIO);

		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}

	// Endpoints adicionales:
	public ArrayList<Servicio> getServiciosAdquiridosByCliente(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Servicio> servicios = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SERVICIOS_BY_CLIENT_ID);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Servicio servicioObtenido = getServicio(rs.getInt("id_servicio"));
			servicios.add(servicioObtenido);
		}

		return servicios;
	}
	
	public ArrayList<ComentarioServicio> getComentariosServicio(int id) throws SQLException, ClassNotFoundException {
		ArrayList<ComentarioServicio> comentarios = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_COMENTARIOS_SERVICIO);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ComentarioServicioDao dao = new ComentarioServicioDao();
			dao.connect();
			
			ComentarioServicio comentarioObtenido = dao.getComentarioServicio(rs.getInt("id"));
			comentarios.add(comentarioObtenido);
			
			dao.disconnect();
		}

		return comentarios;
	}
	
	public void postComentarioServicio(ComentarioServicio comentario) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_COMENTARIO_SERVICIO);

		ps.setString(1, comentario.getComentario());
		ps.setInt(2, comentario.getId_Servicio());
		ps.setInt(3, comentario.getId_Usuario());
		ps.setString(4, comentario.getFecha());

		ps.execute();
		ps.close();
	}
	
	public ArrayList<Servicio> getServiciosByNombre(String nombre) throws SQLException, ClassNotFoundException {
		ArrayList<Servicio> servicios = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SERVICIOS_BY_NOMBRE);
		ps.setString(1, nombre);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			servicios.add(getServicio(rs.getInt("id")));
		}

		return servicios;
	}
	
	public ArrayList<Servicio> getTop5() throws SQLException, ClassNotFoundException {
		ArrayList<Servicio> servicios = new ArrayList<>();

		String select = ConstantsApi.GET_TOP_5_SERVICIOS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			servicios.add(getServicio(rs.getInt("id")));
		}

		return servicios;
	}
	
	public ArrayList<Servicio> getServiciosBySuscriptorId(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Servicio> servicios = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SERVICIOS_BY_SUSCRIPTOR_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Servicio servicioObtenido = getServicio(rs.getInt("id"));
			servicios.add(servicioObtenido);
		}

		return servicios;
	}
	
}
