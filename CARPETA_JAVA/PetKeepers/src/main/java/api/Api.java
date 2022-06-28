package api;

//CLASES
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//DEPENDENCIAS
//OBJETOS
//ENDPOINTS
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class Api {
	@GET
	@Path("/barra")
	public Response hello() {
		return Response.ok("Esto es una prueba", MediaType.APPLICATION_JSON).build();
	}

}
