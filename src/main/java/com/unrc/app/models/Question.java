/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unrc.app.models;
import org.javalite.activejdbc.Model;

/**
 *
 * @author jorgea
 */

public class Question extends Model{
    static {
        validatePresenceOf("descripcion","id_user","id_post");
    }
    
}
