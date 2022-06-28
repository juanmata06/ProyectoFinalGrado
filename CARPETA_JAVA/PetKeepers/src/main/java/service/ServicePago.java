package service;

//CLASES
import java.util.ArrayList;

import model.Pago;
//DEPENDENCIAS
//OBJETOS
import dao.PagoDao;
//ECEPCIONES
import java.sql.SQLException;

public class ServicePago {
	private PagoDao Dao;

	public ArrayList<Pago> getPagos() throws SQLException, ClassNotFoundException {
		Dao = new PagoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Pago> pagos = Dao.getPagos();
		Dao.disconnect();

		return pagos;
	}
	
	public Pago getPago(int id) throws SQLException, ClassNotFoundException {
		Dao = new PagoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Pago pago = Dao.getPago(id);
		Dao.disconnect();

		return pago;
	}
	
	public void postPago(Pago pago) throws SQLException, NullPointerException {
		Dao = new PagoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.postPago(pago);
		
		Dao.disconnect();
	}
	
	public void updatePago(int id, Pago pago) throws SQLException, NullPointerException {
		Dao = new PagoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.updatePago(id, pago);
		
		Dao.disconnect();
	}
	
	public void deletePago(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new PagoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.deletePago(id);
		
		Dao.disconnect();
	}
}