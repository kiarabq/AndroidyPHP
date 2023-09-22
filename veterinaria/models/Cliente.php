<?php
require_once 'Conexion.php';

class Cliente extends Conexion{
  private $access;

  public function __CONSTRUCTOR__(){
    $this->access = parent::getConexion();
  }

  public function add($data = []){
    try{
      $query = $this->access->prepare("CALL spu_clientes_registrar(?,?,?,?)");
      $query->execute(
        array(
          $data['apellidos'],
          $data['nombres'],
          $data['dni'],
          $data['claveacceso']
        )
        );
    }catch(Exception $e){
      die($e->getMessage()); 
    }
  }

  public function list(){
    try{
      $query = $this->access->prepare("CALL spu_clientes_listar(?,?,?,?)");
      $query->execute();
      return $query->fetchAll(PDO::FETCH_ASSOC);
    }catch(Exception $e){
      die($e->getMessage()); 
    }
  }
}