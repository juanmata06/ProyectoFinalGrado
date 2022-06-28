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
$value=array();
if(!$db){
    echo json_encode("error");
}else{
    $query = $db->query("select * from mascota;");
    while($fila = mysqli_fetch_array($query)){
        $value[]=$fila;
    }
    echo json_encode($value);
}
?>