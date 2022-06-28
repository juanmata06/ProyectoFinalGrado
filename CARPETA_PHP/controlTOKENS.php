<?php 
function jwtGetCodeJSON($contenido){
$header = base64_encode(json_encode(array('alg' => 'HS256', 'typ' => 'JWT')) );
$payload = base64_encode($contenido);
$secret_key = 'Secret Key';
$signature = base64_encode(hash_hmac('sha256', $header . '.' . $payload, $secret_key, true));
$jwt_token = $header . '.' . $payload . '.' . $signature;
return $jwt_token;
}

function jwtCheckCodeJSON($jwt_token){
$secret_key = 'Secret Key';
$jwt_values = explode('.', $jwt_token);
$header =$jwt_values[0] ;
$payload = $jwt_values[1];
$signature = $jwt_values[2];
$resultedsignature = base64_encode(hash_hmac('sha256', $header . '.' . $payload , $secret_key, true));
if($resultedsignature == $signature) { 
    return true; 
} else { 
    return false; 
}
}

function getUsr($jwt_token){
    $secret_key = 'Secret Key';
    $jwt_values = explode('.', $jwt_token);
    $header =$jwt_values[0] ;
    $payload = $jwt_values[1];
    $signature = $jwt_values[2];
    $resultedsignature = base64_encode(hash_hmac('sha256', $header . '.' . $payload , $secret_key, true));
    return base64_decode($payload); 

}

?>