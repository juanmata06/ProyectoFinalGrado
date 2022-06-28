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
import externalLibrary.MyFunctions;
import model.Pago;
import model.Suscripcion;
import model.Suscriptor;
//ECEPCIONES
import java.sql.SQLException;

public class SuscriptorDao {
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

	public ArrayList<Suscriptor> getSuscriptores() throws SQLException, ClassNotFoundException {
		ArrayList<Suscriptor> suscriptores = new ArrayList<>();

		String select = ConstantsApi.GET_SUSCRIPTORES;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {
			Suscriptor suscriptorObtenido = getSuscriptor(rs.getInt("id"));
			suscriptores.add(suscriptorObtenido);
		}

		return suscriptores;
	}

	public Suscriptor getSuscriptor(int id) throws SQLException, ClassNotFoundException {
		Suscriptor suscriptorObtenido = new Suscriptor();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SUSCRIPTOR);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			MascotaDao daoMascota = new MascotaDao();
			daoMascota.connect();
			ArrayList<Integer> mascotas = daoMascota.getMascotasByClient(rs.getInt("id_cliente"));
			daoMascota.disconnect();

			ServicioDao daoServicio = new ServicioDao();
			daoServicio.connect();
			ArrayList<Integer> servicios = daoServicio.getServiciosBySuscriptor(rs.getInt("id_cliente"));
			daoServicio.disconnect();

			PagoDao daoPago = new PagoDao();
			daoPago.connect();
			Pago pago = daoPago.getPago(rs.getInt("metodo_de_pago"));
			daoPago.disconnect();

			SuscripcionDao daoSuscripcion = new SuscripcionDao();
			daoSuscripcion.connect();
			Suscripcion suscripcion = daoSuscripcion.getSuscripcion(rs.getInt("id_suscripcion"));
			daoSuscripcion.disconnect();

			suscriptorObtenido = new Suscriptor(rs.getInt("id"), rs.getString("nombre"),
					rs.getString("primer_apellido"), rs.getString("segundo_apellido"), rs.getString("email"),
					rs.getString("password"), rs.getString("dni"), rs.getString("nacimiento"), rs.getString("telefono"),
					rs.getString("ciudad"), rs.getString("direccion"), rs.getString("foto"), rs.getInt("tipo_usuario"),
					mascotas, rs.getInt("id_cliente"), pago, suscripcion, servicios);
		}

		return suscriptorObtenido;
	}

	public void postSuscriptor(Suscriptor suscriptor) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_SUSCRIPTOR);

		ps.setString(1, suscriptor.getNombre());
		ps.setString(2, suscriptor.getPrimer_apellido());
		ps.setString(3, suscriptor.getSegundo_apellido());
		ps.setString(4, suscriptor.getEmail());
		ps.setString(5, suscriptor.getDni());
		ps.setString(6, suscriptor.getNacimiento());
		ps.setString(7, suscriptor.getTelefono());
		ps.setString(8, suscriptor.getCiudad());
		ps.setString(9, suscriptor.getDireccion());
		ps.setString(10, suscriptor.getFoto());
		ps.setString(11, suscriptor.getPassword());
		ps.setInt(12, suscriptor.getId_cliente());
		ps.setInt(13, suscriptor.getTipo_usuario());
		ps.setInt(14, suscriptor.getPago().getId());
		ps.setInt(15, suscriptor.getSuscripcion().getId());

		ps.execute();
		ps.close();
	}

	public void updateSuscriptor(int id, Suscriptor suscriptor) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_SUSCRIPTOR);

		ps.setString(1, suscriptor.getNombre());
		ps.setString(2, suscriptor.getPrimer_apellido());
		ps.setString(3, suscriptor.getSegundo_apellido());
		ps.setString(4, suscriptor.getEmail());
		ps.setString(5, suscriptor.getDni());
		ps.setString(6, suscriptor.getNacimiento());
		ps.setString(7, suscriptor.getTelefono());
		ps.setString(8, suscriptor.getCiudad());
		ps.setString(9, suscriptor.getDireccion());
		ps.setString(10, suscriptor.getFoto());
		ps.setString(11, suscriptor.getPassword());
		ps.setInt(12, suscriptor.getId_cliente());
		ps.setInt(13, suscriptor.getTipo_usuario());
		ps.setInt(14, suscriptor.getPago().getId());
		ps.setInt(15, suscriptor.getSuscripcion().getId());
		ps.setInt(16, id);

		ps.execute();
		ps.close();
	}

	public void deleteSuscriptor(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_SUSCRIPTOR);

		ps.setInt(1, id);
		ps.execute();
		ps.close();

	}

	// Endpoints adicionales:
	public Suscriptor getSuscriptorByIdUsuario(int id) throws SQLException, ClassNotFoundException {
		Suscriptor suscriptorObtenido = new Suscriptor();
		
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_SUSCRIPTOR_BY_USUARIO);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			suscriptorObtenido = getSuscriptor(rs.getInt("id"));
		}
		
		return suscriptorObtenido;		
	}
}
