package api;

//CLASES
import javax.ws.rs.Consumes;
import model.Token;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Servicio;
import service.ServiceServicio;
//DEPENDENCIAS
//OBJETOS
import service.ServiceToken;
//ECEPCIONES
import java.sql.SQLException;
//ENDPOINTS
@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
	
public class TokenApi {
	private ServiceToken service;

	public TokenApi() {
		service = new ServiceToken();
	}
	
	@GET
	@Path("/usuario={id}")
	public Response crearToken(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
		service = new ServiceToken();
		return Response.ok(service.crearToken(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/")
	public Response verificarToken(Token token) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceToken();
		return Response.ok(service.verificarToken(token.getValor()), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/usuario")
	public Response obtenerUsuarioByToken(Token token) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceToken();
		return Response.ok(service.obtenerUsuarioByToken(token.getValor()), MediaType.APPLICATION_JSON).build();
	}
}
