package com.unrc.app;

import com.unrc.app.models.*;

import com.unrc.app.models.Vehicle;
import static java.lang.Integer.parseInt;
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

    
    public static User currentUser = new User();
    public static Post currentPost = new Post();
    public static boolean connectionAdmin = false;
    public static boolean connectionUser = false;

    public static void main(String[] args) {
        System.out.println("hi!");
        Html html = new Html();
        Spark.before((request, response) -> {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "");

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
            if(connectionAdmin == true){
                resp.type("text/html");
                List<User> u = User.findAll();
                String users = "";
                for (int i = 0; i < u.size(); i++) {
                    users = users + u.get(i).getString("id_user") + " " + u.get(i).getString("first_name") + " " + u.get(i).getString("last_name") + ",";
                }
                return html.getAllUsers(users);
             }
            else
                return html.getFailLogin();
        });
        get("/user/:id", (req, resp) -> {
            if(connectionAdmin == true){
                resp.type("text/html");
                System.out.print(req.params(":id"));
                User user = User.findFirst("id_user = ?", req.params(":id"));
                String vl = user.get("first_name") + "," + user.get("last_name") + "," + user.get("email");

                return html.getUserBy(vl);
                         }
            else
                return html.getFailLogin();
        });

        get("/insertuser", (req, resp) -> {
             if(connectionAdmin == true){
                resp.type("text/html");
                return html.IngresarUsuario();
            }
            else
                return html.getFailLogin();
        });
        post("/insertuser", (req, resp) -> {
            if(connectionAdmin == true){
                resp.type("text/html");
                User.createUser(req.queryParams("first_name"),req.queryParams("last_name"),req.queryParams("email"),req.queryParams("contrasena"));
                return html.admin();
            }
            else
                return html.getFailLogin();
        });
        post("/getusers", (req, resp) -> {
            if(connectionAdmin == true){
                resp.type("text/html");
                User tmp = User.findFirst("id_user = ?", req.queryParams("id_user"));
                User.deleteUser(tmp.getInteger("id_user"));
                return html.admin();
            }
            else
                return html.getFailLogin();
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Post~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        get("/post/:id", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                Post post = Post.findFirst("id_post = ?", req.params(":id"));
                String own = "";
                User name = User.findFirst("id_user = ?", post.getString("id_user"));
                own = own + name.getString("first_name") + "}"  + post.getString("patent") + "}" + post.getString("description");
                return html.getPostBy(own);
                        }
            else
                return html.getFailLogin();
        });
        get("/question/answers", (req, resp) -> {
            
            
            return null;
        });
        get("/ownpost", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<Post> post = Post.find("id_user = ?", currentUser.get("id_user"));
                if (!post.isEmpty()) {
                    String vh = "";
                    for (int i = 0; i < post.size(); i++) {
                        vh = vh + post.get(i).getString("id_post") + "}" + post.get(i).getString("description") + "}" + currentUser.getString("first_name") + "}" + post.get(i).getString("patent") + ",";
                    }
                    return html.getPost(vh);
                } else 
                    return "no hay post registrados";
                }
             else
                return html.getFailLogin();
        });
        get("/insertpost", (req, resp) -> {
            if(connectionUser==true){
                resp.type("text/html");
                return html.IngresarPost(currentUser);
             }
             else
                return html.getFailLogin();
        });
        post("/insertpost", (req, resp) -> {
            if(connectionUser==true){
                resp.type("text/html");
                Post.createPost(currentUser,  req.queryParams("patente"), req.queryParams("descripcion"));
                return html.IngresarPost(currentUser);
            }
             else
                return html.getFailLogin();
        });
        post("/getpostbysearch", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                if (req.queryParams("coment") == null || req.queryParams("coment").equals("")) {
                    return " No escribio ningun comentario <br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\">";
                }
                System.out.println(req.queryParams("coment"));
                Question.createQuestion(currentUser,currentPost.getInteger("id_post"),req.queryParams("coment"));
                return " se envio la pregunta <br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\">";
             }
             else
                return html.getFailLogin();

        });
        post("/answerpost", (req, resp) -> {
        if(connectionAdmin == true || connectionUser==true){

            resp.type("text/html");
            Question q = Question.findFirst("id_question = ?", req.queryParams("question"));
            if (q.equals(null)||req.queryParams("responder") == null || req.queryParams("responder").equals("")) {
                return " no se guardo correctamente la respuesta <br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\">";
            }
            System.out.println(req.queryParams("coment"));
            Answer.createAnswer(currentUser, q.getInteger("id_question"), req.queryParams("responder"));
            return " se guardo la respuesta <br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\">";
        }
             else
                return html.getFailLogin();

        });
        get("/post", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<Post> post = Post.findAll();
                String vh = "";
                for (int i = 0; i < post.size(); i++) {
                    User name = User.findFirst("id_user = ?", post.get(i).getString("id_user"));
                    if (name != null) {
                        vh = vh + post.get(i).getString("id_post") + "}" + post.get(i).getString("description") + "}" + name.getString("first_name") + "}" + post.get(i).getString("patent") + ",";
                    }
                }
                return html.getPost(vh);
                        }
             else
                return html.getFailLogin();
        });
        
        post("/post", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){

                resp.type("text/html");
                Post post = Post.findFirst("id_post = ?", req.queryParams("id_post"));
                currentPost = post;
                String own = "";
                User name = User.findFirst("id_user = ?", post.getString("id_user"));
                own = own + name.getString("first_name") + "}"  + post.getString("patent") + "}" + post.getString("description");

                Vehicle tmp = Vehicle.findFirst("patent = ?", post.getString("patent"));

                String vl = tmp.get("patent") + "," + tmp.get("mark") + "," + tmp.get("model") + "," + tmp.get("color") + "," + tmp.get("tipo") + "," + tmp.get("isCoupe") + "," + tmp.get("cc") + "," + tmp.get("capacity");
                List<Question> ques = Question.findAll();
                String q = "";
                for (int i = 0; i < ques.size(); i++) {
                    q+= User.findFirst("id_user = ?", ques.get(i).getInteger("id_user")).getString("first_name")+"}" + ques.get(i).getString("description")+"}";
                    Answer ans = Answer.findFirst("id_question = ?", ques.get(i).get("id_question"));
                    if( ans!=null){
                       q+= ans.getString("description")+",";
                    }else q+="-"+",";
                            
                    
                }
                return html.getPostBySearch(own, vl,q);
             }
             else
                return html.getFailLogin();
        });

        post("/viewpost", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                Post post = Post.findFirst("id_post = ?", req.queryParams("id_post"));
                currentPost = post;
                String own = "";
                User name = User.findFirst("id_user = ?", post.getString("id_user"));
                own = own + name.getString("first_name") + "}"  + post.getString("patent") + "}" + post.getString("description");

                Vehicle tmp = Vehicle.findFirst("patent = ?", post.getString("patent"));

                String vl = tmp.get("patent") + "," + tmp.get("mark") + "," + tmp.get("model") + "," + tmp.get("color") + "," + tmp.get("tipo") + "," + tmp.get("isCoupe") + "," + tmp.get("cc") + "," + tmp.get("capacity");
                List<Question> ques = Question.findAll();
                String q = "";
                for (int i = 0; i < ques.size(); i++) {
                    q+= User.findFirst("id_user = ?", ques.get(i).getInteger("id_user")).getString("first_name")+"}"+ ques.get(i).getString("id_question")+"}" + ques.get(i).getString("description")+"}";
                    Answer ans = Answer.findFirst("id_question = ?", ques.get(i).get("id_question"));
                    if( ans!=null){
                       q+= ans.getString("description")+",";
                    }else q+="-"+",";        
                }
                return html.getOwnPostBySearch(own, vl,q);
                         }
             else
                return html.getFailLogin();
        });

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Questions~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/questions", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<User> u = User.findAll();
                String users = "";
                for (int i = 0; i < u.size(); i++) {
                    users = users + u.get(i).getString("first_name") + " " + u.get(i).getString("last_name") + ",";
                }

                return html.getAllUsers(users);
             }
             else
                return html.getFailLogin();
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~vehicles~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/vehicles", (req, resp) -> {
            if(connectionAdmin == true){
                resp.type("text/html");
                List<Vehicle> v = Vehicle.findAll();
                String vh = "";
                for (int i = 0; i < v.size(); i++) {
                    vh = vh + v.get(i).getString("Patent") + "}" + v.get(i).getString("Mark") + "}" + v.get(i).getString("Model") + "}" + v.get(i).getString("Color") + "}" + v.get(i).getString("Tipo") + "}" + v.get(i).getString("id_user") + "}" + v.get(i).getString("isCoupe") + "}" + v.get(i).getString("cc") + "}" + v.get(i).getString("capacity") + ",";
                }

                return html.getAutomoviles(vh);
                         }
             else
                return html.getFailLogin();
        });
        get("/vehicle/:patente", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                Vehicle v = Vehicle.findFirst("patent = ?", req.params(":patente"));
                String vh = "";

                vh = vh + v.getString("Patent") + "}" + v.getString("Mark") + "}" + v.getString("Model") + "}" + v.getString("Color") + "}" + v.getString("Tipo") + "}" + v.getString("id_user") + "}"  + v.getString("isCoupe") + "}" + v.getString("cc") + "}" + v.getString("capacity");

                return html.getVehicleBy(vh);
             }
             else
                return html.getFailLogin();
        });

        get("/insertvehicle", (req, resp) -> {
            if(connectionUser==true){
                resp.type("text/html");
                return html.IngresarAutomovil();
             }
             else
                return html.getFailLogin();
        });
        
        post("/insertvehicle", (req, resp) -> {
            if(connectionUser==true){
                resp.type("text/html");
                if (req.queryParams("tipo").equals("0")){
                    Vehicle.createVehicle(req.queryParams("marca"), req.queryParams("modelo"), req.queryParams("patente"), currentUser.getInteger("id_user"), req.queryParams("color"), req.queryParams("tipo"), 0, req.queryParams("isCoupe"), 0); 
                }
                else 
                    if (req.queryParams("tipo").equals("2")) {
                        Vehicle.createVehicle(req.queryParams("marca"), req.queryParams("modelo"), req.queryParams("patente"), currentUser.getInteger("id_user"), req.queryParams("color"), req.queryParams("tipo"), 0,"-", parseInt(req.queryParams("Capacity")));
                    } 
                    else  
                        if (req.queryParams("tipo").equals("1")) {
                            Vehicle.createVehicle(req.queryParams("marca"), req.queryParams("modelo"), req.queryParams("patente"), currentUser.getInteger("id_user"), req.queryParams("color"), req.queryParams("tipo"), parseInt(req.queryParams("CC")),"-", 0);
                    }

                return html.IngresarAutomovil();
                         }
             else
                return html.getFailLogin();
        });
        get("/ownvehicles", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<Vehicle> v = Vehicle.find("id_user = ?", currentUser.get("id_user"));
                if (!v.isEmpty()) {

                    String vh = "";
                    for (int i = 0; i < v.size(); i++) {
                        vh = vh + v.get(i).getString("patent") + "}" + v.get(i).getString("mark") + "}" + v.get(i).getString("model") + "}" + v.get(i).getString("color") + "}" + v.get(i).getString("tipo") + "}" + v.get(i).getString("id_user") + "}" + v.get(i).getString("isCoupe") + "}" + v.get(i).getString("cc") + "}" + v.get(i).getString("capacity") + ",";
                    }

                    return html.getAutomoviles(vh);
                }
                return "no posee vehiculos registrados";
                                     }
             else
                return html.getFailLogin();
        });

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Address~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/address", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<Address> v = Address.findAll();
                String vh = "";
                for (int i = 0; i < v.size(); i++) {
                    vh = vh + v.get(i).getString("province") + "}" + v.get(i).getString("city") + "}" + v.get(i).getString("postal_code") + "}" + v.get(i).getString("street") + "}" +v.get(i).getString("num")+ ",";
                }

                return html.getCities(vh);
            }
             else
                return html.getFailLogin();
        });
        get("/insertaddress", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                return html.IngresarCiudad();
            }
             else
                return html.getFailLogin();
        });
        post("/insertaddress", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                Address.createAddress(req.queryParams("direccion"),parseInt(req.queryParams("num")),req.queryParams("ciudad"),req.queryParams("provincia"),parseInt(req.queryParams("codigo_postal")));
                Address a = Address.findByAddress(req.queryParams("direccion"),parseInt(req.queryParams("num")),req.queryParams("ciudad"),req.queryParams("provincia"),parseInt(req.queryParams("codigo_postal")));
                int id_address= parseInt(a.getString("id_address"));
                UsersAddress.createUsersAddress(currentUser.getInteger("id_user"), id_address);
                return html.IngresarCiudad();
            }
            else
                return html.getFailLogin();
        });
        
        get("/ownaddress", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<UsersAddress> a = UsersAddress.find("id_user = ?", currentUser.get("id_user"));
                if (!a.isEmpty()) {

                    String vh = "";
                    
                    for (int i = 0; i < a.size(); i++) {
                        Address b = Address.findFirst("id_address = ?", a.get(i).getString("id_address"));
                        vh = vh + b.getString("province") + "}" + b.getString("city") + "}" + b.getString("postal_code") + "}" + b.getString("street") + "}" +b.getString("num")+ ",";
                        
                    }

                    return html.getOwnAddress(vh);
                }
                return "no posee ciudades ";
                                     }
             else
                return html.getFailLogin();
        });
        

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Answers~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        get("/answers", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                List<Answer> v = Answer.findAll();
                String vh = "";
                for (int i = 0; i < v.size(); i++) {
                    vh = vh + v.get(i).getString("id_post") + " " + v.get(i).getString("id_user") + " " + v.get(i).getString("description") + ",";
                }
                return html.getAnswers(vh);
            }
            else
                return html.getFailLogin();
        });
        get("/answers/:id", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){

                resp.type("text/html");
                Answer v = Answer.findFirst("id_answer", ":id");
                String vh = "";
                vh = vh + v.getString("id_post") + " " + v.getString("id_user") + " " + v.getString("description");
                return html.getAnswersByid(vh);
                        }
            else
                return html.getFailLogin();
        });

        post("/insertanswer", (req, resp) -> {
            if(connectionAdmin == true || connectionUser==true){
                resp.type("text/html");
                Answer.createAnswer(currentUser, parseInt(req.queryParams("id_pregunta")), req.queryParams("descripcion"));
                return html.IngresarRespuesta();
            }
            else
                return html.getFailLogin();
        });
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Contacto Admin~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        
        get("/admincontact", (req, resp) -> {
            if(connectionUser==true){
                resp.type("text/html");
                return html.contactAdmin();
                     }
            else
                return html.getFailLogin();
        });
        
        post("/admincontact", (req, resp) -> {
            if(connectionUser==true){
                resp.type("text/html");
                Message.createMessage(currentUser.getInteger("id_user"), req.queryParams("mensaje"));
                return html.contactAdmin();
            }
            else
                return html.getFailLogin();
        });
        
        
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Login~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        
        get("/loginuser", (req, resp) -> {
            resp.type("text/html");
            connectionAdmin =false;
            connectionUser =false;
            currentUser = null;
            currentPost = null;
            
            return html.loginUsuario();
        });
        post("/loginuser", (req, resp) -> {
            resp.type("text/html");
            //validar usuario exitente o  admin
            User tmp = User.findFirst("email = ?", req.queryParams("email"));

            if (req.queryParams("email").equals("admin") && req.queryParams("contrasena").equals("1234")) {
                    connectionAdmin =true;
                    connectionUser =false;
                    return html.admin();
                }
            else{
                if (tmp != null && tmp.get("contrasena").equals(req.queryParams("contrasena"))) {
                        connectionAdmin =false;
                        connectionUser =true;
                        currentUser = tmp;
                        return html.webpage();
                }
            }
            return "El usuario o la contrasena son incorrectos";
        });

    }
}
