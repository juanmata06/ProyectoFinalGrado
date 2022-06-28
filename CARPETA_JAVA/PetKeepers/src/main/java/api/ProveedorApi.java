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
//DEPENDENCIAS
//OBJETOS
import model.Proveedor;
import service.ServiceProveedor;
//ECEPCIONES
import java.sql.SQLException;
//ENDPOINTS
@Path("/proveedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProveedorApi {
	private ServiceProveedor service;

	public ProveedorApi() {
		service = new ServiceProveedor();
	}
	
	@GET
	@Path("/")
	public Response getProveedores() throws SQLException, ClassNotFoundException {
		service = new ServiceProveedor();
		return Response.ok(service.getProveedores(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	public Response getProveedor(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
		service = new ServiceProveedor();
		return Response.ok(service.getProveedor(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/")
	public Response postProveedor(Proveedor proveedor) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceProveedor();
		service.postProveedor(proveedor);
		return Response.ok("Se ha creado un nuevo proveedor", MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("{id}")
	public Response updateProveedor(Proveedor proveedor, @PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceProveedor();
		service.updateProveedor(id, proveedor);
		return Response.ok("Se ha actualizado el proveedor", MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteProveedor(@PathParam("id") int id) throws ClassNotFoundException, SQLException, NullPointerException {
		service = new ServiceProveedor();
		service.deleteProveedor(id);
		return Response.ok("Se ha eliminado el proveedor", MediaType.APPLICATION_JSON).build();
	}
}
