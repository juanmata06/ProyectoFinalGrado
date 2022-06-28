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
import service.ServiceMascota;
import service.ServiceServicio;
import model.ComentarioMascota;
import model.Mascota;
//ECEPCIONES
import java.sql.SQLException;

//ENDPOINTS
@Path("/mascota")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MascotaApi {
	private ServiceMascota service;

	public MascotaApi() {
		service = new ServiceMascota();
	}

	@GET
	@Path("/")
	public Response getMascotas() throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		return Response.ok(service.getMascotas(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	public Response getMascota(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		return Response.ok(service.getMascota(id), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/")
	public Response postMascota(Mascota mascota) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		service.postMascota(mascota);
		return Response.ok("Se ha creado un nuevo Mascota", MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("{id}")
	public Response updateMascota(Mascota mascota, @PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		service.updateMascota(id, mascota);
		return Response.ok("Se ha actualizado Mascota", MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteMascota(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		service.deleteMascota(id);
		return Response.ok("Se ha eliminado el Mascota", MediaType.APPLICATION_JSON).build();
	}

	// Endpoints adicionales:
	@GET
	@Path("/suscriptor/{id}")
	public Response getMascotasAdquiridasBySuscriptor(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		return Response.ok(service.getMascotasAdquiridasBySuscriptor(id), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/comentarios/{id}")
	public Response getComentariosMascota(@PathParam("id") int id)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		return Response.ok(service.getComentariosMascota(id), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/comentarios")
	public Response postComentarioMascota(ComentarioMascota comentario)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		service.postComentarioMascota(comentario);
		return Response.ok("Se ha creado el comentario", MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/top5")
	public Response getTop5() throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		return Response.ok(service.getTop5(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/tipo={tipo}")
	public Response getMascotasAdquiridasBySuscriptor(@PathParam("tipo") String tipo)
			throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceMascota();
		return Response.ok(service.getMascotasByTipo(tipo), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/cliente={id}")
	public Response getMascotasByClientId(@PathParam("id") int id)throws ClassNotFoundException, SQLException, NullPointerException {
		return Response.ok(service.getMascotasByClientId(id), MediaType.APPLICATION_JSON).build();
	}
}
