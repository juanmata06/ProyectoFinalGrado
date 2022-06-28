<?php
require_once('controlTOKENS.php');
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
header("Allow: GET, POST, OPTIONS, PUT, DELETE");
$data = json_decode(file_get_contents("php://input"),true);
//$data = {“nom”:”juan”,”pass”:”1234}
//echo '{"token":"'.$token.'"}';
//$email = $_POST["email"];
$email = $data["email"];
$password = $data["password"];
$token =jwtGetCodeJSON($email);
$resposta= array();
$resposta["token"]=$token;

echo json_encode($resposta);

?>
