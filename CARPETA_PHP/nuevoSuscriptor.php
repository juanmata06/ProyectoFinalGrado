<?php
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
header("Allow: GET, POST, OPTIONS, PUT, DELETE");
$parse = parse_ini_file("./db_config.ini");
$host = $parse['host'];
$port = $parse['port'];
$socket = $parse['socket'];
$user = $parse['user'];
$password = $parse['password'];
$dbname = $parse['bbdd'];

//Hacemos la conexion 
$db = new mysqli($host, $user, $password, $dbname, $port, $socket);


if(!$db){
    echo json_encode("ERROR");

}else{
    $id = $_POST["id"];
    $type = $_POST["type"];
    $payment = $_POST["payment"];

    $identificador = intval($id);
    $query = ("SELECT * FROM cliente where id = '" . $identificador . "'");
    $resultado = mysqli_query($db, $query);
    while ($linea = mysqli_fetch_array($resultado)) {
        $array[] = $linea;
    }
    $usr = $array[0];
    $create = ("INSERT INTO suscriptor (nombre,primer_apellido,segundo_apellido,email,dni,nacimiento,telefono,ciudad,direccion,password,id_cliente,tipo_usuario,metodo_de_pago,id_suscripcion) VALUES ('$usr[nombre]','$usr[primer_apellido]','$usr[nombre]','$usr[nombre]','$usr[nombre]','$usr[nombre]','$usr[nombre]','$usr[nombre]','$usr[nombre]','$usr[nombre]',)");
    //echo json_encode($usr["nombre"]);

}
?>