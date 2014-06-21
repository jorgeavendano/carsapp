package com.unrc.app.models;

import org.javalite.activejdbc.Model;


public class MessageGuest extends Model {
    static {
      validatePresenceOf("description");
    }
    
  public static MessageGuest findByMessageGuest(int id_messageGuest){
    return (MessageGuest.findFirst("id_messageGuest =?", id_messageGuest ));
  }

  public static void createMessageGuest(String descripcion){
        MessageGuest messageGuest = create("description", descripcion);
                messageGuest.saveIt();
  }

  public static Boolean messageGuestExistente(int id_messageGuest){
      return (MessageGuest.first("id_messageGuest =?", id_messageGuest)!=null);
  }

  public static void deleteMessageGuest(int id_messageGuest){
    MessageGuest messageGuest = MessageGuest.findByMessageGuest(id_messageGuest);
    if(messageGuestExistente(id_messageGuest))
        messageGuest.delete("id_messageGuest = ?", id_messageGuest);
  }
    
}
