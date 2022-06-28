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
$des = $_POST["des"];
$pre = $_POST["pre"];
$nm = $_POST["nm"];
$id = $_POST["id"];
/*var_dump($_FILES);
foreach ($_FILES as $file) {
    $nomImg=$file["tmp_name"];
    $SoloUrl = $file["name"];
    $UrlImg = $folderUrl.$file["name"]; 
    move_uploaded_file($nomImg,$UrlImg);
}
$SoloUrl= "http://localhost/Proyecto/CARPETA_PHP/imagenes/".$SoloUrl;*/

$precio = intval($pre);
$identificador = intval($id);
$update = "INSERT INTO mascota (nombre,nombre_mascota,descripcion,precio,activo,id_cliente) VALUES ('$nom','$nm','$des','$precio',1,'$identificador')";
if(mysqli_query($db,$update)){
    echo json_encode("Se ha creado la mascota correctamente");
}else{
    echo json_encode("Error",mysqli_error($db));
}
}




?>