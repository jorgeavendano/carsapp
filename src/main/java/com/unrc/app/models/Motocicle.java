
package com.unrc.app.models;
import org.javalite.activejdbc.Model;

/**
 *
 * @author jorgea
 */
public class Vehicle extends Model{
      static {
    	  validatePresenceOf("patents","cc");
  }
    
}