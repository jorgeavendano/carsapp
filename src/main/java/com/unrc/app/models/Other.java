package com.unrc.app.models;
import org.javalite.activejdbc.Model;

/**
 *
 * @author jorgea
 */
public class Other extends Model{
      static {
    	  validatePresenceOf("patents","transmission");
  }
    
}