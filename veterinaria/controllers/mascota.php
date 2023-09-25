<?php
require_once '../models/Mascota.php';
$mascota = new Mascota();

//Registrar
if (isset($_POST['operacion'])){
  if($_POST['operacion'] == 'add'){
    $registro = [
      "nombre"      => $_POST['nombre'],
      "color"       => $_POST['color'],
      "especie"     => $_POST['especie'],
      "raza"        => $_POST['raza'],
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

  //Actualizar
  if($_POST['operacion'] == 'update'){
    $registro = [
      "idmascota" => $_POST['idmascota'],
      "nombre"    => $_POST['nombre'],
      "color"     => $_POST['color'],
      "especie"   => $_POST['especie'],
      "raza"      => $_POST['raza'],
      "genero"    => $_POST['genero']
    ];
  }

  //Eliminar
  if($_POST['operacion'] == 'delete'){
    $mascota->delete(
      [
        'idmascota' => $_POST['idmascota']
      ]
      );
  } 
  
  //Actualizar
  if($_POST['operacion'] == 'update'){
    $registro = [
      "idmascota" => $_POST['idmascota'],
      "nombre"    => $_POST['nombre'],
      "color"     => $_POST['color'],
      "especie"   => $_POST['especie'],
      "raza"      => $_POST['raza'],
      "genero"    => $_POST['genero']
    ];
    $mascota->update($registro);
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