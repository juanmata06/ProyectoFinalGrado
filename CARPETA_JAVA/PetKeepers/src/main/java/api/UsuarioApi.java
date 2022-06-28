package api;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Suscriptor;
import model.Usuario;
import service.ServiceUsuario;
import model.CredencialesLogin;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UsuarioApi {
	private ServiceUsuario service;

	public UsuarioApi() {
		service = new ServiceUsuario();
	}
	
	@GET
	@Path("/")
	public Response getUsuarios() throws SQLException, ClassNotFoundException {
		service = new ServiceUsuario();
		return Response.ok(service.getUsuarios(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	public Response getUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
		service = new ServiceUsuario();
		return Response.ok(service.getUsuario(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/")
	public Response postUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceUsuario();
		service.postUsuario(usuario);
		return Response.ok("Se ha creado un nuevo usuario", MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("{id}")
	public Response updateUsuario(Usuario usuario, @PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceUsuario();
		service.updateUsuario(id, usuario);
		return Response.ok("Se ha actualizado el usuario", MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteUsuario(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceUsuario();
		service.deleteUsuario(id);
		return Response.ok("Se ha eliminado el usuario", MediaType.APPLICATION_JSON).build();
	}
	
	// Endpoints adicionales:
	@POST
	@Path("/login")
	public Response getUsarioIdByLogin(CredencialesLogin credenciales) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceUsuario();
		return Response.ok(service.getUsarioIdByLogin(credenciales), MediaType.APPLICATION_JSON).build();
	}
}
