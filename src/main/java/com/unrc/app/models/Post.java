package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class Post extends Model {
  static {
      validatePresenceOf("id_user", "patent","description");
  }
  
    public static Post findByPost(int id_user, String patente){
	return (findFirst("id_user = ? and patent = ?", id_user, patente));
    }

    public static Post createPost(User user, String patente, String descripcion){
        int user2 = user.getInteger("id_user");
        Post post =create("id_user",user2, "description",descripcion,"patent",patente);
        post.saveIt();
        return findByPost(user2,patente);
    }

    public static Boolean existPost(int id_user,String patente){
        return (Answer.first("id_user = ? and patent = ? ", id_user,patente) != null);
    }
   
    public static void deletePost(int id_post,int id_user,String patente){
        if(existPost(id_user,patente)){
            Question.delete("id_post = ?", id_post );
            Post.delete("id_user = ? and pantet = ? ",id_user, patente);
        }
    } 
}

