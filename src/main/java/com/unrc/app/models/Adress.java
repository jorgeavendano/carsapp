package com.unrc.app.models;

import org.javalite.activejdbc.Model;
import java.util.*;

public class Address extends Model {
  static {

      validatePresenceOf("calle","numero");
  }

  //Retorna el modelo Address en la base a partir de la dirección de un usuario
  public static Address findByAddress(String direc, int num){
  return (Address.findFirst("calle = ? and numero = ? ", direc, num));
  }


  //crea un user nuevo si no esta en la Base de datos a partir de la direccion
  public static Address createAddress(String direc, int num, User usuario){
    Address address = create("calle", dir,"numero", num);
    if(!existAddress(direc,num)){
          address.saveIt();
          usuario.add(address);
        }
    return findByAddress(dir,num);
  }

//miro si existe alguna
  public static Boolean direcExistente(String direc, int num){
    Boolean estado=true;
     if( Address.first("calle = ? and numero = ?",direc, num)==null){
        estado = false;
     }
    return estado;
  }


//le faltaria el eliminar direc, todavia no se me ocurre bien