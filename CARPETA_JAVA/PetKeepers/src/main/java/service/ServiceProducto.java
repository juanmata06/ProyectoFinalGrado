package service;

//CLASES
import java.util.ArrayList;
//DEPENDENCIAS
//OBJETOS
import model.Producto;
import dao.ProductoDao;
//ECEPCIONES
import java.sql.SQLException;

public class ServiceProducto {
	private ProductoDao Dao;

	public ArrayList<Producto> getProductos() throws SQLException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Producto> productos = Dao.getProductos();
		Dao.disconnect();
		
		return productos;
	}
	
	public Producto getProducto(int id) throws SQLException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Producto producto = Dao.getProducto(id);
		Dao.disconnect();
		
		return producto;
	}
	
	public void postProducto(Producto producto) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.postProducto(producto);
		Dao.disconnect();
	}
	
	public void updateProducto(int id, Producto producto) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.updateProducto(id, producto);
		Dao.disconnect();
	}
	
	public void deleteProducto(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Dao.deleteProducto(id);;
		Dao.disconnect();
	}
	
	// Endpoints adicionales:
	public ArrayList<Producto> getTop5() throws SQLException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Producto> productos = Dao.getTop5();
		Dao.disconnect();
		
		return productos;
	}
	
	public ArrayList<Producto> getgetProductosByNombreroductos(String nombre) throws SQLException, ClassNotFoundException {
		Dao = new ProductoDao();
		try {
			Dao.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Producto> productos = Dao.getProductosByNombre(nombre);
		Dao.disconnect();
		
		return productos;
	}

}
