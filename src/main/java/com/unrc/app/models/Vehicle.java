
package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class Vehicle extends Model{
      static {
      validatePresenceOf("mark", "model","patents","id_user");
  }
    
}
