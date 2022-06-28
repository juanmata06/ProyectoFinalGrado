<?php
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
header("Allow: GET, POST, OPTIONS, PUT, DELETE");
if ($_FILES["file"]["error"] > 0)
{
echo json_encode("Error");
}else{
$dir = "../IMAGENES/servicios";
$tmp_name = $_FILES["file"]["tmp_name"];
$imgName = $_FILES["file"]["name"];

$SoloUrl= "http://localhost/Proyecto/IMAGENES/servicios/".$imgName;
move_uploaded_file($tmp_name, "$dir/$imgName");
echo json_encode($SoloUrl);
}

?>