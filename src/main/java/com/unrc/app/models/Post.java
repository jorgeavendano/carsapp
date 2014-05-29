package com.unrc.app.models;

import java.util.List;
import org.javalite.activejdbc.Model;

public class Post extends Model {
  static {
      validatePresenceOf("user_id", "vehicle_id", "texto", "precio");
  }
  
  public Post texto(String s){
      this.set("texto", s);
      return this;
  }
  
  public String texto(){
      return this.getString("texto");
  }
  
  public Post precio(int i) {
      this.set("precio", i);
      return this;
  }