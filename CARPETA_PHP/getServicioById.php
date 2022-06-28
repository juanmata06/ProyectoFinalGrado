<?php
//Buscamos las vars de conexion de bbdd en el archivo .ini
$parse = parse_ini_file("./db_config.ini");
$error = false;
$tipo_error = "";
$host = $parse['host'];
$port = $parse['port'];
$socket = $parse['socket'];
$user = $parse['user'];
$password = $parse['password'];
$dbname = $parse['bbdd'];

//Hacemos la conexion 
$db = new mysqli($host, $user, $password, $dbname, $port, $socket);
$errorConexion = false;
if (!$db) {
    $errorConexion = true;
    $tipo_error = "Error de conexion:" . mysqli_connect_error();
}

//Recibimos el id en JSON
$idServicio = $_GET["id"];

//Hacemos la Query
if (!$db) {
    $error = true;
    $tipo_error = "Error de conexion:" . mysqli_connect_error();
}

if (!$error) {
    $query = ("SELECT * FROM servicio where id = '" . $idServicio . "'");
    $result = mysqli_query($db, $query);

    echo json_encode(mysqli_fetch_array($result));

    if (!$result) {
        $error = true;
        $tipo_error = "Error en la consulta:" . mysqli_error($db);
    }
    mysqli_close($db);
}