package com.unrc.app.models;
import org.javalite.activejdbc.Model;

/**
 *
 * @author jorgea
 */
public class Truck extends Model{
      static {
    	  validatePresenceOf("patents","capacity");
  }
    
}