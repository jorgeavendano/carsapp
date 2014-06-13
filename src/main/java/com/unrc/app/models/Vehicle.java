
package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class Vehicle extends Model{
      static {
      validatePresenceOf("mark", "model","patent","id_user", "color","tipo","cc","isCoupe","capacity");
  }
      
    public static Vehicle findByPatent(String patente){
	return (findFirst("patent = ?", patente));
    }
	
    public static Boolean existVehicle(String patente){
          return (Vehicle.first("patent = ? ", patente ) == null);
    }
   
    public static Vehicle createVehicle(String patente, String marca, String modelo,int usuario,String color,String tipo,int cc,String isCoupe,int capacity){
        Vehicle vehiculo =create("patent", patente, "model", modelo, "mark", marca,"id_user",usuario,"color",color,"tipo",tipo,"cc",cc,"isCoupe",isCoupe,"Capacity",capacity);

        vehiculo.saveIt();
        
        return findByPatent(patente);
    }

    public static void deleteVehicle(String patente){
        if(existVehicle(patente)){
            Vehicle.delete("patent = ?", patente );
        }
    }        
    
}
