package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UsuarioDao;
import model.Usuario;
import model.CredencialesLogin;

public class ServiceUsuario {
	private UsuarioDao Dao;

	public ArrayList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
		Dao = new UsuarioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Usuario> usuarios = Dao.getUsuarios();
		Dao.disconnect();

		return usuarios;
	}
	
	public Usuario getUsuario(int id) throws SQLException, ClassNotFoundException {
		Dao = new UsuarioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Usuario usuario = Dao.getUsuario(id);
		Dao.disconnect();

		return usuario;
	}
	
	public void postUsuario(Usuario usuario) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new UsuarioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.postUsuario(usuario);
		
		Dao.disconnect();
	}
	
	public void updateUsuario(int id, Usuario usuario) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new UsuarioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.updateUsuario(id, usuario);
		
		Dao.disconnect();
	}
	
	public void deleteUsuario(int id) throws SQLException, ClassNotFoundException, NullPointerException {
		Dao = new UsuarioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Dao.deleteUsuario(id);
		
		Dao.disconnect();
	}
	
	// Endpoints adicionales:
	public int getUsarioIdByLogin(CredencialesLogin credenciales) throws SQLException, ClassNotFoundException {
		Dao = new UsuarioDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int id = Dao.getUsarioIdByLogin(credenciales);
		Dao.disconnect();

		return id;
	}
}
