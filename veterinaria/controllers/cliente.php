<?php
require_once '../models/Cliente.php';  
$cliente = new Cliente();

//Registrar
if (isset($_POST['operacion'])){
  if($_POST['operacion'] =='add'){
    $registro = [
      "apellidos" => $_POST['apellidos'],
      "nombres" => $_POST['nombres'],
      "dni" => $_POST['dni'],
      "claveacceso" => $_POST['claveacceso']
    ];
    $cliente->add($registro);
  }
}
