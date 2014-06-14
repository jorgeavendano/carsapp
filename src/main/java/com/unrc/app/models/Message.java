package com.unrc.app.models;

import org.javalite.activejdbc.Model;


public class Message extends Model {
    static {
      validatePresenceOf("id_user","description");
    }
    
  public static Message findByMessage(int id_message ){
    return (Message.findFirst("id_message =?", id_message ));
  }

  public static Message createMessage(int id_user, String descripcion){
        Message message = create("id_user", id_user,"description", descripcion);
        message.saveIt();
        Message f = Message.findFirst("id_user =? and description=?", id_user,descripcion );
        return findByMessage(f.getInteger("id_message"));
  }

  public static Boolean messageExistente(int id_message){
      return (Message.first("id_message =?", id_message)!=null);
  }

  public static void deleteMessage(int id_message){
    Message message = Message.findByMessage(id_message);
    if(messageExistente(id_message))
        message.delete();
  }
    
}
