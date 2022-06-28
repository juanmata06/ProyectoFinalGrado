package dao;

//CLASES
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
//DEPENDENCIAS
import org.json.JSONObject;
import org.json.JSONException;
//OBJETOS
import config.ConstantsApi;
import model.Mascota;
import model.Objeto;
import model.Producto;
import externalLibrary.MyFunctions;
//ECEPCIONES
import java.sql.SQLException;

public class ProductoDao {
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

	public ArrayList<Producto> getProductos() throws SQLException, ClassNotFoundException {
		System.out.println(bbddConnection);
		ArrayList<Producto> productos = new ArrayList<>();

		String select = ConstantsApi.GET_PRODUCTOS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			ProveedorDao daoProveedor = new ProveedorDao();
			daoProveedor.connect();

			// String[] imagenes = new String[rs.getArray("imagenes")];
			ArrayList<String> imagenes = new ArrayList<>();
			Producto proveedorObtenido = new Producto(
					rs.getInt("id"), 
					rs.getString("nombre"),
					rs.getString("descripcion"), 
					rs.getDouble("precio"), 
					rs.getDouble("puntuacion"),
					rs.getBoolean("activo"), 
					rs.getString("imagenes"), 
					daoProveedor.getProveedor(rs.getInt("id_proveedor")));
			daoProveedor.disconnect();
			productos.add(proveedorObtenido);

		}
		return productos;
	}

	public Producto getProducto(int id) throws SQLException, ClassNotFoundException {
		Producto productoObtenido = new Producto();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_PRODUCTO_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ProveedorDao daoProveedor = new ProveedorDao();
			daoProveedor.connect();
				
			ArrayList<String> imagenes = new ArrayList<>();
			productoObtenido = new Producto(
					rs.getInt("id"), 
					rs.getString("nombre"), 
					rs.getString("descripcion"),
					rs.getDouble("precio"), 
					rs.getDouble("puntuacion"), 
					rs.getBoolean("activo"), 
					rs.getString("imagenes"),
					daoProveedor.getProveedor(rs.getInt("id_proveedor")));
		}

		return productoObtenido;
	}

	public void postProducto(Producto producto) throws SQLException, NullPointerException {
		PreparedStatement ps =  bbddConnection.prepareStatement(ConstantsApi.POST_PRODUCTO);
		
		ps.setString(1, producto.getNombre());
		ps.setString(2, producto.getDescripcion());
		ps.setDouble(3, producto.getPrecio());
		ps.setDouble(4, producto.getPuntuacion());
		ps.setBoolean(5, producto.isActivo());
		ps.setString(6, producto.getImagenes());
		ps.setInt(7, producto.getProveedor().getId());
		
		ps.execute(); 
		ps.close();
	}
	
	public void updateProducto(int id, Producto producto) throws SQLException, NullPointerException {
		PreparedStatement ps =  bbddConnection.prepareStatement(ConstantsApi.UPDATE_PRODUCTO);

		ps.setString(1, producto.getNombre());
		ps.setString(2, producto.getDescripcion());
		ps.setDouble(3, producto.getPrecio());
		ps.setDouble(4, producto.getPuntuacion());
		ps.setBoolean(5, producto.isActivo());
		ps.setString(6, producto.getImagenes());
		ps.setInt(7, producto.getProveedor().getId());
		ps.setInt(8, id);

		ps.execute(); 
		ps.close();
	}

	public void deleteProducto(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_PRODUCTO);

		ps.setInt(1, id);
		ps.execute();
		ps.close();

	}
	
	// Endpoints adicionales:
	public ArrayList<Producto> getTop5() throws SQLException, ClassNotFoundException {
		ArrayList<Producto> productos = new ArrayList<>();

		String select = ConstantsApi.GET_TOP_5_PRODUCTOS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			productos.add(getProducto(rs.getInt("id")));
		}

		return productos;
	}
	
	public ArrayList<Producto> getProductosByNombre(String nombre) throws SQLException, ClassNotFoundException {
		ArrayList<Producto> productos = new ArrayList<>();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_PRODUCTOS_BY_NOMBRE);
		ps.setString(1, nombre);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			productos.add(getProducto(rs.getInt("id")));
		}

		return productos;
	}
	
}
