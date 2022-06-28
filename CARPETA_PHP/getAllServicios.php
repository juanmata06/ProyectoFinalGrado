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

//Hacemos la Query
$array = array();
if (!$errorConexion) {
    $query = ("SELECT * FROM servicio");

    $resultado = mysqli_query($db, $query);
    while ($linea = mysqli_fetch_array($resultado)) {
        $array[] = $linea;
    }

    if (!$resultado) {
        $tipo_error = "Error en la consulta:" . mysqli_error($db);
    } else {
        echo json_encode($array);
    }
    mysqli_close($db);
}