package dao;

//CLASES
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//DEPENDENCIAS
//OBJETOS
import config.ConstantsApi;
import model.Proveedor;
//ECEPCIONES
import java.sql.SQLException;

public class ProveedorDao {
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

	public ArrayList<Proveedor> getProveedores() throws SQLException, ClassNotFoundException {
		ArrayList<Proveedor> proveedores = new ArrayList<>();

		String select = ConstantsApi.GET_PROVEEDORES;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {

			Proveedor proveedorObtenido = new Proveedor(
					rs.getInt("id"), 
					rs.getString("nombre"),
					rs.getString("cif"), 
					rs.getString("email"),
					rs.getString("telefono"),
					rs.getString("ciudad"), 
					rs.getString("direccion")
			);
			proveedores.add(proveedorObtenido);

		}

		return proveedores;
	}
	
	public Proveedor getProveedor(int id) throws SQLException, ClassNotFoundException {
		Proveedor proveedorObtenido = new Proveedor();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_PROVEEDOR_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			proveedorObtenido = new Proveedor(
					rs.getInt("id"), 
					rs.getString("nombre"),
					rs.getString("cif"), 
					rs.getString("email"),
					rs.getString("telefono"),
					rs.getString("ciudad"), 
					rs.getString("direccion")
			);
		}

		return proveedorObtenido;
	}
	
	public void postProveedor(Proveedor proveedor) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_PROVEEDOR);

		ps.setString(1, proveedor.getNombre());
		ps.setString(2, proveedor.getCif());
		ps.setString(3, proveedor.getEmail());
		ps.setString(4, proveedor.getTelefono());
		ps.setString(5, proveedor.getCiudad());
		ps.setString(6, proveedor.getDireccion());

		ps.execute();
		ps.close();
	}

	public void updateProveedor(int id, Proveedor proveedor) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_PROVEEDOR);

		ps.setString(1, proveedor.getNombre());
		ps.setString(2, proveedor.getCif());
		ps.setString(3, proveedor.getEmail());
		ps.setString(4, proveedor.getTelefono());
		ps.setString(5, proveedor.getCiudad());
		ps.setString(6, proveedor.getDireccion());
		ps.setInt(7, id);

		ps.execute();
		ps.close();
	}
	
	public void deleteProveedor(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_PROVEEDOR);

		ps.setInt(1, id);
		ps.execute();
		ps.close();

	}
}
