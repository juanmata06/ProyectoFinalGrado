package dao;

//CLASES
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//DEPENDENCIAS
//OBJETOS
import config.ConstantsApi;
import model.Pago;
//ECEPCIONES
import java.sql.SQLException;

public class PagoDao {
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

	public ArrayList<Pago> getPagos() throws SQLException, ClassNotFoundException {
		ArrayList<Pago> pagos = new ArrayList<>();

		String select = ConstantsApi.GET_PAGOS;

		Statement st = bbddConnection.createStatement();
		ResultSet rs = st.executeQuery(select);

		while (rs.next()) {

			Pago pagoObtenido = new Pago(rs.getInt("id"), rs.getString("tipo"));
			pagos.add(pagoObtenido);

		}

		return pagos;
	}

	public Pago getPago(int id) throws SQLException, ClassNotFoundException {
		Pago pagoObtenido = new Pago();

		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.GET_PAGO_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			pagoObtenido = new Pago(rs.getInt("id"), rs.getString("tipo"));
		}

		return pagoObtenido;
	}

	public void postPago(Pago pago) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.POST_PAGO);

		ps.setString(1, pago.getTipo());

		ps.execute();
		ps.close();
	}

	public void updatePago(int id, Pago pago) throws SQLException, NullPointerException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.UPDATE_PAGO);

		ps.setString(1, pago.getTipo());
		ps.setInt(2, id);

		ps.execute();
		ps.close();
	}

	public void deletePago(int id) throws SQLException, NullPointerException, ClassNotFoundException {
		PreparedStatement ps = bbddConnection.prepareStatement(ConstantsApi.DELETE_PAGO);

		ps.setInt(1, id);

		ps.execute();
		ps.close();

	}

}