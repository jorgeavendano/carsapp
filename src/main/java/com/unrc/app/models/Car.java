package com.unrc.app.models;
import org.javalite.activejdbc.Model;

/**
 *
 * @author jorgea
 */
public class Car extends Model{
      static {
      validatePresenceOf("patents","isCoupe");
  }
    
}
