package service;

//CLASES
import java.util.ArrayList;
//DEPENDENCIAS
//OBJETOS
import model.Suscriptor;
import dao.SuscriptorDao;
//ECEPCIONES
import java.sql.SQLException;

public class ServiceSuscriptor {
	private SuscriptorDao Dao;

	public ArrayList<Suscriptor> getSuscriptores() throws SQLException, ClassNotFoundException {
		Dao = new SuscriptorDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Suscriptor> suscriptores = Dao.getSuscriptores();
		Dao.disconnect();

		return suscriptores;
	}
	
	public Suscriptor getSuscriptor(int id) throws SQLException, ClassNotFoundException {
		Dao = new SuscriptorDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Suscriptor suscriptor = Dao.getSuscriptor(id);
		Dao.disconnect();

		return suscriptor;
	}
	
	public void postSuscriptor(Suscriptor suscriptor) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new SuscriptorDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.postSuscriptor(suscriptor);
		
		Dao.disconnect();
	}
	
	public void updateSuscriptor(int id, Suscriptor suscriptor) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new SuscriptorDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.updateSuscriptor(id, suscriptor);
		
		Dao.disconnect();
	}
	
	public void deleteSuscriptor(int id) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new SuscriptorDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.deleteSuscriptor(id);
		
		Dao.disconnect();
	}
	
	// Endpoints adicionales:
	public Suscriptor getSuscriptorByIdUsuario(int id) throws SQLException, ClassNotFoundException {
		Dao = new SuscriptorDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Suscriptor suscriptor = Dao.getSuscriptorByIdUsuario(id);
		Dao.disconnect();

		return suscriptor;
	}
}
