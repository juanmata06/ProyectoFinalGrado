<?php

//Recibimos el id en JSON
$idSuscriptor = $_GET["id"];

$parse = parse_ini_file("./db_config.ini");
$error = false;
$tipo_error = "";
$host = $parse['host'];
$port = $parse['port'];
$socket = $parse['socket'];
$user = $parse['user'];
$password = $parse['password'];
$dbname = $parse['bbdd'];

$db = new mysqli($host, $user, $password, $dbname, $port, $socket);

if (!$db) {
    $error = true;
    $tipo_error = "Error de conexion:" . mysqli_connect_error();
}

if (!$error) {
    $query = ("SELECT * FROM suscriptor where id = '" . $idSuscriptor . "'");
    $result = mysqli_query($db, $query);

    echo json_encode(mysqli_fetch_array($result));

    if (!$result) {
        $error = true;
        $tipo_error = "Error en la consulta:" . mysqli_error($db);
    }
    mysqli_close($db);
}