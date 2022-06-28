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
import model.Pago;
import service.ServicePago;
//ECEPCIONES
import java.sql.SQLException;
//ENDPOINTS
@Path("/pago")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PagoApi {
	private ServicePago service;

	public PagoApi() {
		service = new ServicePago();
	}
	
	@GET
	@Path("/")
	public Response getPagos() throws SQLException, ClassNotFoundException {
		service = new ServicePago();
		return Response.ok(service.getPagos(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	public Response getPago(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServicePago();
		System.out.print(id);
		return Response.ok(service.getPago(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/")
	public Response postSuscripcion(Pago pago) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServicePago();
		service.postPago(pago);
		return Response.ok("Se ha creado una nuevo metodo de Pago", MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("{id}")
	public Response updateSuscripcion(Pago pago, @PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServicePago();
		service.updatePago(id, pago);
		return Response.ok("Se ha actualizado el metodo de Pago", MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePago(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServicePago();
		service.deletePago(id);
		return Response.ok("Se ha eliminado el metodo de Pago", MediaType.APPLICATION_JSON).build();
	}
}