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
$usr = json_decode(file_get_contents("php://input"),true);
$mail = $usr["email"];
$pas= $usr["password"];
$value=array();
$resposta= array();

$query = $db->query("select * from cliente WHERE email='$mail';");
while($fila = mysqli_fetch_array($query)){
    $value=$fila;
}

if($value[4] == $mail && $value[5] == $pas){
    $resposta["login"]=true;
}else{
    $resposta["login"]=false;

}
echo json_encode($resposta);

}



?>