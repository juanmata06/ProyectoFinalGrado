package api;

//CLASES
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Suscriptor;
//DEPENDENCIAS
//OBJETOS
import model.Suscriptor;
import service.ServiceSuscriptor;
//ECEPCIONES
import java.sql.SQLException;
//ENDPOINTS
@Path("/suscriptor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SuscriptorApi {
	private ServiceSuscriptor service;

	public SuscriptorApi() {
		service = new ServiceSuscriptor();
	}
	
	@GET
	@Path("/")
	public Response getSuscriptores() throws SQLException, ClassNotFoundException {
		service = new ServiceSuscriptor();
		return Response.ok(service.getSuscriptores(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	public Response getSuscriptor(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
		service = new ServiceSuscriptor();
		return Response.ok(service.getSuscriptor(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/")
	public Response postSuscriptor(Suscriptor suscriptor) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscriptor();
		service.postSuscriptor(suscriptor);
		return Response.ok("Se ha creado un nuevo Suscriptor", MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("{id}")
	public Response updateSuscriptor(Suscriptor suscriptor, @PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscriptor();
		service.updateSuscriptor(id, suscriptor);
		return Response.ok("Se ha actualizado el Suscriptor", MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSuscriptor(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscriptor();
		service.deleteSuscriptor(id);
		return Response.ok("Se ha eliminado el Suscriptor", MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/usuario={id}")
	public Response getSuscriptorByIdUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
		service = new ServiceSuscriptor();
		return Response.ok(service.getSuscriptorByIdUsuario(id), MediaType.APPLICATION_JSON).build();
	}
}
