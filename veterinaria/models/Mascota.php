<?php
require_once 'Conexion.php';

class Mascota extends Conexion{
  private $access;

  public function __CONSTRUCT(){
    $this->access = parent::getConexion();
  }

  public function add($data = []){
    try{
      $query = $this->access->prepare("CALL spu_mascotas_registrar(?,?;?;?;?;?;)");
      $query->execute(
        array(
          $data['idcliente'],
          $data['idraza'],
          $data['nombre'],
          $data['fotografia'],
          $data['color'],
          $data['genero']
        )
      );
    }catch(Exception $e){
      die($e->getMessage());
    }
  }

  public function list(){
    try{
      $query = $this->access->prepare("CALL spu_mascotas_listar()");
      $query->execute();
      return $query->fetchAll(PDO::FETCH_ASSOC);
    }catch(Exception $e){
      die($e->getMessage());
    }
  }
}