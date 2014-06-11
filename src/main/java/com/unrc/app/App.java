package com.unrc.app;

import com.unrc.app.models.City;
import com.unrc.app.models.Answer;
import com.unrc.app.models.Post;
import com.unrc.app.models.User;
import com.unrc.app.models.UsersAddresses;
import com.unrc.app.models.Vehicle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javalite.activejdbc.Base;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Hello world!
 *
 */
public class App {

        public static  User currentUser = new User();
    public static void main(String[] args) {
        System.out.println("hi!");
        Html html = new Html();
        Spark.before((request, response) -> {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "root");

        });
        Spark.after((request, response) -> {
            Base.close();
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~users~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        get("/users", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            List<User> users = User.findAll();
            attributes.put("users_count", users.size());
            attributes.put("users", users);
            return new ModelAndView(attributes, "users.mustache");
        },
                new MustacheTemplateEngine()
        );

        get("/user", (req, resp) -> {

            resp.type("text/html");
            List<User> u = User.findAll();
            String users = "";
            for (int i = 0; i < u.size(); i++) {
                users = users + u.get(i).getString("first_name") + " " + u.get(i).getString("last_name") + ",";
            }
            

            return html.getAllUsers(users);
        });
         get("/user/:id", (req, resp) -> {

            resp.type("text/html");
            User user = User.findFirst(req.params(":id"));

            String vl = user.get("first_name") + "," + user.get("last_name") + "," + user.get("last_name");

            return html.getUserBy(vl);
        });
 
        
        get("/insertuser", (req, resp) -> {
            resp.type("text/html");

            return html.IngresarUsuario();
        });
        post("/insertuser", (req, resp) -> {

            resp.type("text/html");
            User tmp = new User();
            tmp.set("first_name", req.queryParams("first_name"));
            tmp.set("last_name", req.queryParams("last_name"));
            tmp.set("email", req.queryParams("email"));
            tmp.saveIt();

            return html.IngresarUsuario();
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Post~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        get("/post/:id", (req, resp) -> {

            resp.type("text/html");
            Post post = Post.findFirst("id_post = ?", req.params(":id"));
            String own = "";
            User name = User.findFirst("id = ?", post.getString("id_dueno"));
                own = own +name.getString("first_name") + "}" + post.getString("ciudad")+ "}" + post.getString("patente_vehiculo")+ "}" + post.getString("descripcion");
            
            

            return html.getPostBy(own);
        });
        get("/ownpost", (req, resp) -> {

            resp.type("text/html");
            currentUser = User.findById("1");
            System.out.println(currentUser.get("first_name"));
            List<Post> post = Post.find("id_dueno = ?", currentUser.get("id"));
            String vh = ""; 
            for (int i = 0; i < post.size(); i++) {
               
                vh = vh + post.get(i).getString("descripcion") + "}" +currentUser.getString("first_name") + "}" + post.get(i).getString("patente_vehiculo") + ",";

            }
            

            return html.getPost(vh);
        });
                get("/insertpost", (req, resp) -> {

            resp.type("text/html");

            return html.IngresarPost();
        });
        post("/insertpost", (req, resp) -> {

            resp.type("text/html");
            Post tmp = new Post();
            System.out.println(req.queryParams("id_user"));
            tmp.set("id_dueno", req.queryParams("id_user"));
            tmp.set("descripcion", req.queryParams("descripcion"));
            tmp.set("patente_vehiculo", req.queryParams("patente"));
            tmp.saveIt();

            return html.IngresarPost();
        });
        get("/post", (req, resp) -> {

            resp.type("text/html");
            List<Post> post = Post.findAll();
            String vh = "";
            for (int i = 0; i < post.size(); i++) {
               User name = User.findFirst("id = ?", post.get(i).getString("id_dueno"));
               
                vh = vh + post.get(i).getString("descripcion") + "}" +name.getString("first_name") + "}" + post.get(i).getString("patente_vehiculo") + ",";

            }

            return html.getPost(vh);
        });
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Questions~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/questions", (req, resp) -> {

            resp.type("text/html");
            List<User> u = User.findAll();
            String users = "";
            for (int i = 0; i < u.size(); i++) {
                users = users + u.get(i).getString("first_name") + " " + u.get(i).getString("last_name") + ",";
            }
            

            return html.getAllUsers(users);
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~vehicles~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/vehicles", (req, resp) -> {

            resp.type("text/html");
            List<Vehicle> v = Vehicle.findAll();
            String vh = "";
            for (int i = 0; i < v.size(); i++) {
                vh = vh + v.get(i).getString("Patente") + " " + v.get(i).getString("Marca") + " " + v.get(i).getString("Modelo") + " " + v.get(i).getString("Color") + " " + v.get(i).getString("Tipo") + " " + v.get(i).getString("id_dueno") + ",";
            }
            

            return html.getAutomoviles(vh);
        });
        get("/vehicle/:patente", (req, resp) -> {

            Vehicle v = Vehicle.findFirst(req.params(":patente"));
            String vh = "";

            vh = vh + v.getString("Patente") + "," + v.getString("Marca") + "," + v.getString("Modelo") + "," + v.getString("Color") + "," + v.getString("Tipo") + "," + v.getString("id_dueno");

            return html.getVehicleBy(vh);
        });
        
        get("/insertvehicle", (req, resp) -> {

            resp.type("text/html");
            return html.IngresarAutomovil();

        });
        get("/ownvehicles", (req, resp) -> {

            resp.type("text/html");
            List<Vehicle> v = Vehicle.find("id_dueno = ?", currentUser.get("id"));
            String vh = "";
            for (int i = 0; i < v.size(); i++) {
                vh = vh + v.get(i).getString("Patente") + " " + v.get(i).getString("Marca") + " " + v.get(i).getString("Modelo") + " " + v.get(i).getString("Color") + " " + v.get(i).getString("Tipo") + " " + v.get(i).getString("id_dueno") + ",";
            }
            

            return html.getAutomoviles(vh);
        });
        get("/vehicle/:patente", (req, resp) -> {

            resp.type("text/html");
            Vehicle tmp = Vehicle.findFirst(req.params(":patente"));

            String vl = tmp.get("patente") + "," + tmp.get("marca") + "," + tmp.get("modelo")+ "," + tmp.get("color")+ "," + tmp.get("tipo")+ "," + tmp.get("id_dueno");

            return html.getVehicleBy(vl);
        });
        
        post("/insertvehicle", (req, resp) -> {
            resp.type("text/html");
            Vehicle tmpv = new Vehicle();
            tmpv.set("marca", req.queryParams("marca"));
            tmpv.set("modelo", req.queryParams("modelo"));
            tmpv.set("patente", req.queryParams("patente"));
            tmpv.set("color", req.queryParams("color"));
            tmpv.set("tipo", req.queryParams("tipo"));
            tmpv.set("id_dueno", req.queryParams("id_dueno"));
            tmpv.saveIt();

            return html.IngresarAutomovil();
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~cities~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/cities", (req, resp) -> {

            resp.type("text/html");
            List<City> v = City.findAll();
            String vh = "";
            for (int i = 0; i < v.size(); i++) {
                vh = vh + v.get(i).getString("provincia") + " " + v.get(i).getString("ciudad") + " " + v.get(i).getString("codigo_postal") + " " + v.get(i).getString("direccion") + ",";
            }
            

            return html.getCities(vh);
        });
                get("/insertcity", (req, resp) -> {

            resp.type("text/html");

            return html.IngresarCiudad();
        });
        post("/insertcity", (req, resp) -> {

            resp.type("text/html");
            City tmp = new City();
            System.out.println(req.queryParams("provincia"));
            tmp.set("provincia", req.queryParams("provincia"));
            tmp.set("ciudad", req.queryParams("ciudad"));
            tmp.set("codigo_postal", req.queryParams("codigo_postal"));
            tmp.set("direccion", req.queryParams("direccion"));
            tmp.saveIt();

            return html.IngresarCiudad();
        });
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Answers~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/answers", (req, resp) -> {

            resp.type("text/html");
            List<Answer> v = Answer.findAll();
            String vh = "";
            for (int i = 0; i < v.size(); i++) {
                vh = vh + v.get(i).getString("id_post") + " " + v.get(i).getString("id_user") + " " + v.get(i).getString("descripcion")+"," ;
            }

            return html.getAnswers(vh);
        });
               get("/answers/:id", (req, resp) -> {

            resp.type("text/html");
            Answer v = Answer.findFirst("id_answer", ":id");
            String vh = "";
            
                vh = vh + v.getString("id_post") + " " + v.getString("id_user") + " " + v.getString("descripcion") ;
            

            return html.getAnswersByid(vh);
        });
        
        
       
                


        post("/insertanswer", (req, resp) -> {

            resp.type("text/html");
            Answer tmp = new Answer();
            tmp.set("id_post", req.queryParams("id_post"));
            tmp.set("descripcion", req.queryParams("descripcion"));
            tmp.set("id_user", req.queryParams("id_post"));
            tmp.saveIt();

            return html.IngresarRespuesta();
        });


    }
}
