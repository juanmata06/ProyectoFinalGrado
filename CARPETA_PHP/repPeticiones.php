<?php
require_once('controlTOKENS.php');
//Comprova si s’ha rebut un header “authorization” amb un token válid
checkAuthToken();
function checkAuthToken(){
$headers = apache_request_headers();
//var_dump($headers);
$resposta =array();
if(isset($headers["authorization"]) && $headers["authorization"] !=""){
$token_recibido=$headers["authorization"];
//SI TE UN TOKEN

if(jwtCheckCodeJSON($token_recibido)) { 
    $resposta["correcte"]=true;
    echo json_encode( $resposta);
}else{
    $resposta["token"]=$token_recibido;
    $resposta["correcte"]=false;
    echo json_encode($resposta);
}
}else{
    echo json_encode("a");
}
}






?>