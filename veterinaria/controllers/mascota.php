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

  //Eliminar
  if($_POST['operacion'] == 'delete'){
    $mascota->delete(
      [
        'idmascota' => $_POST['idmascota']
      ]
    );
  }
}

//Listar(solicitudes)
if(isset($_GET['operacion'])){
  if($_GET['operacion'] == 'list'){
    echo json_encode($mascota->list());
  }

  if ($_GET['operacion'] == 'getData'){
    echo json_encode($mascota->getData(["idmascota" => $_GET['idmascota']]));
  }
}