package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ServicioDao;
import model.Servicio;
import model.ComentarioServicio;


public class ServiceServicio {
	private ServicioDao Dao;

	public Servicio getServicio(int id) throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Servicio servicio = Dao.getServicio(id);
		Dao.disconnect();

		return servicio;
	}

	public ArrayList<Servicio> getServicios() throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Servicio> servicios = Dao.getServicios();
		Dao.disconnect();

		return servicios;
	}
		
	public void postServicio(Servicio servicio) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.postServicio(servicio);
		Dao.disconnect();
	}

	public void updateServicio(int id, Servicio servicio)
			throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.updateServicio(id, servicio);
		Dao.disconnect();
	}

	public void deleteServicio(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.deleteServicio(id);
		;
		Dao.disconnect();
	}

	// Endpoints adicionales:
	public ArrayList<Servicio> getServiciosAdquiridosByCliente(int id) throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Servicio> servicios = Dao.getServiciosAdquiridosByCliente(id);
		Dao.disconnect();

		return servicios;
	}

	public ArrayList<ComentarioServicio> getComentariosServicio(int id) throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<ComentarioServicio> comentarios = Dao.getComentariosServicio(id);
		Dao.disconnect();

		return comentarios;
	}
	
	public void postComentarioServicio(ComentarioServicio comentario) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.postComentarioServicio(comentario);
		Dao.disconnect();
	}
	
	public ArrayList<Servicio> getServiciosByNombre(String nombre) throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Servicio> servicios = Dao.getServiciosByNombre(nombre);
		Dao.disconnect();

		return servicios;
	}
	
	public ArrayList<Servicio> getTop5() throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Servicio> servicios = Dao.getTop5();
		Dao.disconnect();

		return servicios;
	}
	
	public ArrayList<Servicio> getServiciosBySuscriptorId(int id) throws SQLException, ClassNotFoundException {
		Dao = new ServicioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Servicio> servicios = Dao.getServiciosBySuscriptorId(id);
		Dao.disconnect();

		return servicios;
	}
}
