package service;

//CLASES
import java.util.ArrayList;
//DEPENDENCIAS
//OBJETOS
import model.Suscripcion;
import dao.SuscripcionDao;
//ECEPCIONES
import java.sql.SQLException;

public class ServiceSuscripcion {
	private SuscripcionDao Dao;

	public ArrayList<Suscripcion> getSuscripciones() throws SQLException, ClassNotFoundException {
		Dao = new SuscripcionDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Suscripcion> suscripciones = Dao.getSuscripciones();
		Dao.disconnect();

		return suscripciones;
	}
	
	public Suscripcion getSuscripcion(int id) throws SQLException, ClassNotFoundException {
		Dao = new SuscripcionDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Suscripcion suscripcion = Dao.getSuscripcion(id);
		Dao.disconnect();

		return suscripcion;
	}
	
	public void postSuscripcion(Suscripcion suscripcion) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new SuscripcionDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.postSuscripcion(suscripcion);
		
		Dao.disconnect();
	}
	
	public void updateSuscripcion(int id, Suscripcion suscripcion) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new SuscripcionDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.updateSuscripcion(id, suscripcion);
		
		Dao.disconnect();
	}
	
	public void deleteSuscripcion(int id) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new SuscripcionDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.deleteSuscripcion(id);
		
		Dao.disconnect();
	}
}
