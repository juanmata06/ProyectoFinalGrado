<?php
require_once('controlTOKENS.php');
showUsr();
function showUsr(){
    $headers = apache_request_headers();
if(isset($headers["authorization"]) && $headers["authorization"] !=""){
$token_recibido=$headers["authorization"];
if(getUsr($token_recibido)) { 
    echo json_encode(getUsr($token_recibido));
}
}
}





?>
