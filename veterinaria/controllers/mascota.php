<?php
require_once '../models/Mascota.php';
$mascota = new Mascota();

//Registrar
if (isset($_POST['operacion'])){
  if($_POST['operacion'] == 'add'){
    $registro = [
      "idcliente"   => $_POST['idcliente'],
      "idraza"      => $_POST['idraza'],
      "nombre"      => $_POST['nombre'],
      "fotografia"  => $_POST['fotografia'],
      "color"       => $_POST['color'],
      "genero"      => $_POST['genero']
    ];
    $mascota->add($registro);
  }
}