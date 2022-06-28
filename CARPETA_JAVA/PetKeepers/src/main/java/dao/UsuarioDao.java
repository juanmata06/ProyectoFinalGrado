package dao;

//CLASES
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

//DEPENDENCIAS
//OBJETOS
import config.ConstantsApi;
import model.CredencialesLogin;
import model.Usuario;
//ECEPCIONES
import java.sql.SQLException;

public class UsuarioDao {
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

	public ArrayList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
		ArrayList<Usuario> usuarios = new ArrayList<>();

		String select = ConstantsApi.GET_SUSCRIPTORES;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			Usuario usuarioObtenido = getUsuario(rs.getInt("id"));
			usuarios.add(usuarioObtenido);

		}

		return usuarios;
	}

	public Usuario getUsuario(int id) throws SQLException, ClassNotFoundException {
		Usuario usuarioObtenido = new Usuario();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_USUARIO);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			MascotaDao daoMascota = new MascotaDao();
			daoMascota.connect();
			ArrayList<Integer> mascotas = daoMascota.getMascotasByClient(rs.getInt("id"));
			daoMascota.disconnect();

			usuarioObtenido = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("primer_apellido"),
					rs.getString("segundo_apellido"), rs.getString("email"), rs.getString("password"),
					rs.getString("dni"), rs.getString("nacimiento"), rs.getString("telefono"), rs.getString("ciudad"),
					rs.getString("direccion"), rs.getString("foto"), rs.getInt("tipo_usuario"), mascotas);
		}

		return usuarioObtenido;
	}

	public void postUsuario(Usuario usuario) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_USUARIO);

		ps.setString(1, usuario.getNombre());
		ps.setString(2, usuario.getPrimer_apellido());
		ps.setString(3, usuario.getSegundo_apellido());
		ps.setString(4, usuario.getEmail());
		ps.setString(5, usuario.getPassword());
		ps.setString(6, usuario.getDni());
		ps.setString(7, usuario.getNacimiento());
		ps.setString(8, usuario.getTelefono());
		ps.setString(9, usuario.getCiudad());
		ps.setString(10, usuario.getDireccion());
		ps.setString(11, usuario.getFoto());
		ps.setInt(12, usuario.getTipo_usuario());

		ps.execute();
		ps.close();
	}

	public void updateUsuario(int id, Usuario usuario) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_USUARIO);

		ps.setString(1, usuario.getNombre());
		ps.setString(2, usuario.getPrimer_apellido());
		ps.setString(3, usuario.getSegundo_apellido());
		ps.setString(4, usuario.getEmail());
		ps.setString(5, usuario.getPassword());
		ps.setString(6, usuario.getDni());
		ps.setString(7, usuario.getNacimiento());
		ps.setString(8, usuario.getTelefono());
		ps.setString(9, usuario.getCiudad());
		ps.setString(10, usuario.getDireccion());
		ps.setString(11, usuario.getFoto());
		ps.setInt(12, usuario.getTipo_usuario());
		ps.setInt(13, id);

		ps.execute();
		ps.close();
	}

	public void deleteUsuario(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_USUARIO);

		ps.setInt(1, id);
		ps.execute();
		ps.close();

	}

	// Endpoints adicionales:
	public int getUsarioIdByLogin(CredencialesLogin credenciales) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_USUARIO_BY_LOGIN);

		ps.setString(1, credenciales.getEmail());
		ps.setString(2, credenciales.getPassword());

		System.out.println(ps);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			System.out.println(rs.getInt("id"));

			if (rs.getInt("id") != -1)
				return rs.getInt("id");

		}
		return -1;
	}

}