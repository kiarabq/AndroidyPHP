<?php
require_once '../models/Cliente.php';  
$cliente = new Cliente();

//Registrar
if (isset($_POST['operacion'])){
  if($_POST['operacion'] =='add'){
    $registro = [
      "apellidos" => $_POST['apellidos'],
      "nombres"   => $_POST['nombres'],
      "dni"       => $_POST['dni']
    ];
    $cliente->add($registro);
  }

  //Eliminar
  if($_POST['operacion'] == 'delete'){
    $cliente->delete(
      [
        'idcliente' => $_POST['idcliente']
      ]
    );
  }

  //Actualizar
  if($_POST['operacion'] == 'update'){
    $registro = [
      "idcliente" => $_POST['idcliente'],
      "apellidos" => $_POST['apellidos'],
      "nombres"   => $_POST['nombres'],
      "dni"       => $_POST['dni']
    ];
    $cliente->update($registro);
  }
}

//Listar(solicitudes)
if(isset($_GET['operacion'])){
  if($_GET['operacion'] == 'list'){
    echo json_encode($cliente->list());
  }

  if ($_GET['operacion'] == 'getData'){
    echo json_encode($cliente->getData(["idcliente" => $_GET['idcliente']]));
  }
}
