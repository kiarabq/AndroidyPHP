<?php
require_once 'Conexion.php';

class Cliente extends Conexion{
  private $access;

  public function __CONSTRUCTOR__(){
    $this->access = parent::getConexion();
  }

  public function add($data = []){
    try{
      $query = $this->access->prepare("CALL spu_clientes_registrar(?,?,?)");
      $query->execute(
        array(
          $data['apellidos'],
          $data['nombres'],
          $data['dni']
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

  public function delete($data = []){
    try{
      $query = $this->access->prepare("CALL spu_clientes_eliminar(?)")
      $query->execute(
        array(
          $data['idcliente']
        )
      );
    }catch(Exception $e){
      die($e->getMessage());
    }
  }//delete()

  public function getData($data = []){
    try{
      $query = $this->access->prepare("CALL spu_clientes_getdata(?)")
      $query->execute(
        array(
          $data['idcliente']
        )
      );
      return $query->fetchAll(PDO::FETCH_ASSOC);
    }catch(Exception $e){
      die($e->getCode());
    }
  }//getdata
}