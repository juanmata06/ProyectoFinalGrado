package config;

public class ConstantsApi {
	public static final String USER_CONNECTION = "root";
	/*JUAN:*/
	public static final String PASS_CONNECTION = "12341234";
	public static final String CONNECTION = "jdbc:mysql://localhost:3306/" + "petkeepers"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	
	
	/*DIEGO:
	public static final String PASS_CONNECTION = "";
	public static final String CONNECTION = "jdbc:mysql://localhost:3306/" + "petkeepers"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	*/

	/*CRISTIAN:
	public static final String PASS_CONNECTION = "root";
	public static final String CONNECTION = "jdbc:mysql://localhost:8889/" + "petkeepers"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	*/
	
	// ADQUISISICION DE MASCOTAS Y SERVICIOS
	public static final String GET_SERVICIOS_BY_CLIENT_ID = "SELECT id_servicio FROM petkeepers.adquirir_servicio WHERE id_cliente = ?";
	public static final String GET_MASCOTAS_BY_SUSCRIPTOR_ID = "SELECT id_mascota FROM petkeepers.adquirir_mascota WHERE id_suscriptor = ?";

	// COMENTARIOS MASCOTA
	public static final String GET_COMENTARIOS_MASCOTA = "SELECT * FROM petkeepers.comentar_mascota WHERE id_mascota = ?";
	public static final String GET_COMENTARIO_MASCOTA = "SELECT * FROM petkeepers.comentar_mascota WHERE id = ?";
	public static final String POST_COMENTARIO_MASCOTA = "INSERT INTO petkeepers.comentar_mascota (comentario, id_mascota, id_suscriptor, fecha) VALUES (?,?,?,?)";

	// COMENTARIOS SERVICIOS
	public static final String GET_COMENTARIOS_SERVICIO = "SELECT * FROM petkeepers.comentar_servicio WHERE id_servicio = ?";
	public static final String GET_COMENTARIO_SERVICIO = "SELECT * FROM petkeepers.comentar_servicio WHERE id = ?";
	public static final String POST_COMENTARIO_SERVICIO = "INSERT INTO petkeepers.comentar_servicio (comentario, id_servicio, id_cliente, fecha) VALUES (?,?,?,?)";

	// SERVICIO
	public static final String GET_SERVICIOS_BY_SUSCRIPTOR_ID = "SELECT id FROM petkeepers.servicio WHERE id_suscriptor = ?";
	public static final String GET_SERVICIOS = "SELECT * FROM petkeepers.servicio";
	public static final String GET_SERVICIO = "SELECT * FROM petkeepers.servicio WHERE id = ?";
	public static final String GET_SERVICIOS_BY_NOMBRE = "SELECT * FROM petkeepers.servicio WHERE nombre = ?";
	public static final String GET_TOP_5_SERVICIOS = "SELECT * FROM petkeepers.servicio ORDER BY puntuacion DESC LIMIT 5";
	public static final String POST_SERVICIO = "INSERT INTO petkeepers.servicio (nombre, descripcion, precio, puntuacion, activo, imagenes, id_suscriptor) VALUES (?,?,?,?,?,?,?)";
	public static final String UPDATE_SERVICIO = "UPDATE petkeepers.servicio SET nombre = ?, descripcion = ?, precio = ?, puntuacion = ?, activo = ?, imagenes = ?, id_suscriptor = ? WHERE (id = ?)";
	public static final String DELETE_SERVICIO = "DELETE FROM petkeepers.servicio WHERE (id = ?)";

	// MASCOTAS
	public static final String GET_MASCOTAS_BY_CLIENT_ID = "SELECT id FROM petkeepers.mascota WHERE id_cliente = ?";
	public static final String GET_MASCOTAS_BY_TIPO = "SELECT id FROM petkeepers.mascota WHERE tipo = ?";
	public static final String GET_MASCOTAS = "SELECT * FROM petkeepers.mascota";
	public static final String GET_MASCOTA = "SELECT * FROM petkeepers.mascota WHERE id = ?";
	public static final String GET_TOP_5_MASCOTAS = "SELECT * FROM petkeepers.mascota ORDER BY puntuacion DESC LIMIT 5";
	public static final String POST_MASCOTA = "INSERT INTO petkeepers.mascota (nombre, tipo, nombre_mascota, descripcion, precio, puntuacion, activo, imagenes, id_cliente) VALUES (?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_MASCOTA = "UPDATE petkeepers.mascota SET nombre = ?, tipo = ?,  nombre_mascota = ?, descripcion = ?, precio = ?, puntuacion = ?, activo = ?, imagenes = ?, id_cliente = ? WHERE (id = ?)";
	public static final String DELETE_MASCOTA = "DELETE FROM petkeepers.mascota WHERE (id = ?)";

	// USUARIO
	public static final String GET_USUARIOS = "SELECT * FROM petkeepers.cliente";
	public static final String GET_USUARIO = "SELECT * FROM petkeepers.cliente WHERE id = ?";
	public static final String POST_USUARIO = "INSERT INTO petkeepers.cliente (nombre, primer_apellido, segundo_apellido, email, password, dni, nacimiento, telefono, ciudad, direccion, foto, tipo_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_USUARIO = "UPDATE petkeepers.cliente SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, email = ?, password = ?, dni = ?, nacimiento = ?, telefono = ?, ciudad = ?, direccion = ?, foto = ?, tipo_usuario = ? WHERE (id = ?)";
	public static final String DELETE_USUARIO = "DELETE FROM petkeepers.cliente WHERE (id = ?)";
	public static final String GET_USUARIO_BY_LOGIN = "SELECT * FROM petkeepers.cliente WHERE email = ? AND password = ?";

	// SUSCRIPTOR
	public static final String GET_SUSCRIPTORES = "SELECT * FROM petkeepers.suscriptor";
	public static final String GET_SUSCRIPTOR = "SELECT * FROM petkeepers.suscriptor WHERE id = ?";
	public static final String GET_SUSCRIPTOR_BY_USUARIO = "SELECT id FROM petkeepers.suscriptor WHERE id_cliente = ?";
	public static final String POST_SUSCRIPTOR = "INSERT INTO petkeepers.suscriptor (nombre, primer_apellido, segundo_apellido, email, dni, nacimiento, telefono, ciudad, direccion, foto, password, id_cliente, tipo_usuario, metodo_de_pago, id_suscripcion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_SUSCRIPTOR = "UPDATE petkeepers.suscriptor SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, email = ?, dni = ?, nacimiento = ?, telefono = ?, ciudad = ?, direccion = ?, foto = ?, password = ?, id_cliente = ?, tipo_usuario = ?, metodo_de_pago = ?, id_suscripcion = ? WHERE (id = ?)";
	public static final String DELETE_SUSCRIPTOR = "DELETE FROM petkeepers.suscriptor WHERE (id = ?)";

	// SUSCRIPCION
	public static final String GET_SUSCRIPCIONES = "SELECT * FROM petkeepers.suscripcion";
	public static final String GET_SUSCRIPCION_BY_ID = "SELECT * FROM petkeepers.suscripcion WHERE id = ?";
	public static final String POST_SUSCRIPCION = "INSERT INTO petkeepers.suscripcion (tiempo, precio, descripcion) VALUES (?,?,?)";
	public static final String UPDATE_SUSCRIPCION = "UPDATE petkeepers.suscripcion SET tiempo = ?, precio = ?, descripcion = ? WHERE (id = ?)";
	public static final String DELETE_SUSCRIPCION = "DELETE FROM petkeepers.suscripcion WHERE (id = ?)";

	// PROVEEDOR
	public static final String GET_PROVEEDORES = "SELECT * FROM petkeepers.proveedor";
	public static final String GET_PROVEEDOR_BY_ID = "SELECT * FROM petkeepers.proveedor WHERE id = ?";
	public static final String POST_PROVEEDOR = "INSERT INTO petkeepers.proveedor (nombre, cif, email, telefono, ciudad, direccion) VALUES (?,?,?,?,?,?)";
	public static final String UPDATE_PROVEEDOR = "UPDATE petkeepers.proveedor SET nombre = ?, cif = ?, email = ?, telefono = ?, ciudad = ?, direccion = ? WHERE (id = ?)";
	public static final String DELETE_PROVEEDOR = "DELETE FROM petkeepers.proveedor WHERE (id = ?)";

	// PRODUCTO
	public static final String GET_PRODUCTOS = "SELECT * FROM petkeepers.producto";
	public static final String GET_PRODUCTO_BY_ID = "SELECT * FROM petkeepers.producto WHERE id = ?";
	public static final String GET_PRODUCTOS_BY_NOMBRE = "SELECT * FROM petkeepers.producto WHERE nombre = ?";
	public static final String GET_TOP_5_PRODUCTOS = "SELECT * FROM petkeepers.producto ORDER BY puntuacion DESC LIMIT 5";
	public static final String POST_PRODUCTO = "INSERT INTO petkeepers.producto (nombre, descripcion, precio, puntuacion, activo, imagenes, id_proveedor) VALUES (?,?,?,?,?,?,?)";
	public static final String UPDATE_PRODUCTO = "UPDATE petkeepers.producto SET nombre = ?, descripcion = ?, precio = ?, puntuacion = ?, activo = ?, imagenes = ?, id_proveedor = ? WHERE (id = ?)";
	public static final String DELETE_PRODUCTO = "DELETE FROM petkeepers.producto WHERE (id = ?)";

	// PAGO
	public static final String GET_PAGOS = "SELECT * FROM petkeepers.metodo_de_pago";
	public static final String GET_PAGO_BY_ID = "SELECT * FROM petkeepers.metodo_de_pago WHERE id = ?";
	public static final String POST_PAGO = "INSERT INTO petkeepers.metodo_de_pago (tipo) VALUES (?)";
	public static final String UPDATE_PAGO = "UPDATE petkeepers.metodo_de_pago SET tipo = ? WHERE (id = ?)";
	public static final String DELETE_PAGO = "DELETE FROM petkeepers.metodo_de_pago WHERE (id = ?)";
}