<?php
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
header("Allow: GET, POST, OPTIONS, PUT, DELETE");
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

if(!$db){
    echo json_encode("ERROR");
}else{
$nom = $_POST["nom"];
$ape1 = $_POST["ape1"];
$ape2 = $_POST["ape2"];
$correo= $_POST["cor"];
$contraseña= $_POST["con"];
$DNI= $_POST["DNI"];
$fecha_nacimiento= $_POST["nac"];
$telefono= $_POST["tel"];
$ciudad= $_POST["ciu"];
$direccion= $_POST["dir"];
/*var_dump($_FILES);
foreach ($_FILES as $file) {
    $nomImg=$file["tmp_name"];
    $SoloUrl = $file["name"];
    $UrlImg = $folderUrl.$file["name"]; 
    move_uploaded_file($nomImg,$UrlImg);
}
$SoloUrl= "http://localhost/Proyecto/CARPETA_PHP/imagenes/".$SoloUrl;*/

$update = "INSERT INTO cliente (nombre,primer_apellido,segundo_apellido,email,password,dni,nacimiento,telefono,ciudad,direccion) VALUES ('$nom','$ape1','$ape2','$correo','$contraseña','$DNI','$fecha_nacimiento','$telefono','$ciudad','$direccion')";
if(mysqli_query($db,$update)){
    echo json_encode("Se ha creado el usuario: ".$nom);
}else{
    echo json_encode("Error",mysqli_error($db));
}
}




?>