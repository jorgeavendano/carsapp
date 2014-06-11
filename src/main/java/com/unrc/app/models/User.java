package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class User extends Model {
  static {
      validatePresenceOf("first_name", "last_name");
  }
  public String name(){
      return this.getString("first_name")+"    "+this.getString("last_name");
  }
}
