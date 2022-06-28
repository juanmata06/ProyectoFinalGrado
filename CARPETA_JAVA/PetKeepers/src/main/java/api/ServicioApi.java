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
import service.ServiceServicio;
import model.ComentarioServicio;
import model.Servicio;
//ECEPCIONES
import java.sql.SQLException;

//ENDPOINTS
@Path("/servicio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ServicioApi {
	private ServiceServicio service;

	public ServicioApi() {
		service = new ServiceServicio();
	}

	@GET
	@Path("/")
	public Response getServicios() throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		return Response.ok(service.getServicios(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	public Response getServicio(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		return Response.ok(service.getServicio(id), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/")
	public Response postServicio(Servicio Servicio) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		service.postServicio(Servicio);
		return Response.ok("Se ha creado un nuevo Servicio", MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("{id}")
	public Response postServicio(Servicio Servicio, @PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		service.updateServicio(id, Servicio);
		return Response.ok("Se ha actualizado Servicio", MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteServicio(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		service.deleteServicio(id);
		return Response.ok("Se ha eliminado el Servicio", MediaType.APPLICATION_JSON).build();
	}

	// Endpoints adicionales:
	@GET
	@Path("/cliente/{id}")
	public Response getServiciosAdquiridosByCliente(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		return Response.ok(service.getServiciosAdquiridosByCliente(id), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/comentarios/{id}")
	public Response getComentariosServicio(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		return Response.ok(service.getComentariosServicio(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/comentarios")
	public Response postComentarioServicio(ComentarioServicio comentario) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceServicio();
		service.postComentarioServicio(comentario);
		return Response.ok("Se ha creado el comentario", MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/nombre={nombre}")
	public Response getServiciosByNombre(@PathParam("nombre") String nombre)throws ClassNotFoundException, SQLException, NullPointerException {
		System.out.println(nombre);
		return Response.ok(service.getServiciosByNombre(nombre), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/top5")
	public Response getTop5()throws ClassNotFoundException, SQLException, NullPointerException {
		return Response.ok(service.getTop5(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/suscriptor={id}")
	public Response getServiciosBySuscriptorId(@PathParam("id") int id)throws ClassNotFoundException, SQLException, NullPointerException {
		return Response.ok(service.getServiciosBySuscriptorId(id), MediaType.APPLICATION_JSON).build();
	}
}
