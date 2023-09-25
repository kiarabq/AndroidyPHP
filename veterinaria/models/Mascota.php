<?php
require_once 'Conexion.php';

class Mascota extends Conexion{
  private $access;

  public function __CONSTRUCT(){
    $this->access = parent::getConexion();
  }

  public function add($data = []){
    try{
      $query = $this->access->prepare("CALL spu_mascotas_registrar(?,?,?,?,?)");
      $query->execute(
        array(
          $data['nombre'],
          $data['color'],
          $data['especie'],
          $data['raza'],
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

  public function delete($data = []){
    try{
      $query = $this->access->prepare("CALL spu_mascotas_eliminar(?)");
      $query->execute(
        array(
          $data['idmascota']
        )
        );
    }catch(Exception $e){
      die($e->getMessage());
    }
  }

  public function getData($data = []){
    try{
      $query = $this->access->prepare("CALL spu_mascotas_getdata(?)");
      $query->execute(
        array(
          $data['idmascota']
        )
        );
        return $query->fetchAll(PDO::FETCH_ASSOC);
    }catch(Exception $e){
      die($e->getCode());
    }
  }

  public function update($data = []){
    try{
      $query = $this->access->prepare("CALL spu_mascotas_actualizar(?,?,?,?,?,?)");
      $query->execute(
        array(
          $data['idmascota'],
          $data['nombre'],
          $data['color'],
          $data['especie'],
          $data['raza'],
          $data['genero']
        )
        );
    }catch(Exception $e){
      die($e->getMessage());
    }
  }
}