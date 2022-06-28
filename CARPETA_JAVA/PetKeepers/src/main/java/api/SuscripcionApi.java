package api;

//CLASES
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//DEPENDENCIAS
//OBJETOS
import model.Suscripcion;
import service.ServiceSuscripcion;
//ECEPCIONES
import java.sql.SQLException;
//ENDPOINTS
@Path("/suscripcion")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SuscripcionApi {
	private ServiceSuscripcion service;

	public SuscripcionApi() {
		service = new ServiceSuscripcion();
	}
	
	@GET
	@Path("/")
	public Response getSuscripciones() throws SQLException, ClassNotFoundException {
		service = new ServiceSuscripcion();
		return Response.ok(service.getSuscripciones(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	public Response getSuscripcion(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscripcion();
		return Response.ok(service.getSuscripcion(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/")
	public Response postSuscripcion(Suscripcion suscripcion) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscripcion();
		service.postSuscripcion(suscripcion);
		return Response.ok("Se ha creado una nueva suscripcion", MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("{id}")
	public Response updateSuscripcion(Suscripcion suscripcion, @PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscripcion();
		service.updateSuscripcion(id, suscripcion);
		return Response.ok("Se ha actualizado la suscripcion", MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteSuscripcion(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceSuscripcion();
		service.deleteSuscripcion(id);
		return Response.ok("Se ha eliminado la suscripcion", MediaType.APPLICATION_JSON).build();
	}
}
