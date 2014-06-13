
package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class Vehicle extends Model{
      static {
      validatePresenceOf("marca", "modelo","patente","tipo","color","id_dueno");
  }
    
}
