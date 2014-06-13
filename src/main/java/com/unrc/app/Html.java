/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app;

import com.unrc.app.models.User;

/**
 *
 * @author jorgea
 */
public class Html {

    public String getAllUsers(String users) {
        String s = "<br>USUARIOs <br><br><form action=\"/getusers\" method=\"post\">";
        s = s + "<br><br>";
        s = s + "<select name=\"Usuarios\" size=\"4\">";
        String[] tmp = users.split(",");
        for (int i = 0; i < tmp.length - 1; i++) {
            s = s + "<option value=" + "\"" + i + "\"" + ">" + tmp[i];

        }
        s+="</select>";
        s+="Eliminar usuario (id) : <input type=\"text\" name=\"id_user\" size=\"3\" maxlength=\"50\"> <input type=\"submit\"  value=\"delete\"> ";
        s = s + " <br><br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getUserBy(String user) {
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "Usuario<br><br>";
        s = s + "<tr> <td>Nombre</td><td>Apellido</td> <td>Email</td> </tr>";
        String[] tmp = user.split(",");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";

        }
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }
    public String getPostBySearch(String post,String v) {
        String s = "<br>POST <br><br><form action=\"/getpostbysearch\" method=\"post\">";
        s = s + " <br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\">";
         s+= "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">"; 
        s = s + "<tr> <td>DUENO</td><td>CIUDAD</td> <td>PATENTE</td><td>DESCRIPCION</td> </tr>";
        String[] tmp = post.split("}");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";

        }
        
        s = s+"</table><br><table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "VEHICULO <br>";
        s = s + "<tr> <td>Marca</td> <td>Modelo</td> <td>Color</td> <td>Tipo</td> <td>ID_Dueño</td> <td>ciudad</td><td>Pasajeros</td><td>km</td><td>carga</td><</tr>";
        tmp = v.split(",");
        for (int i = 1; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";

        }
        s+="</table><br><br><TEXTAREA COLS=20 ROWS =10 NAME = \"coment\"></TEXTAREA <BR><INPUT TYPE =\"submit\"><form>";
        
        return s;
    }
    public String getPostBy(String post) {
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "POST<br><br>";
        s = s + "<tr> <td>DUENO</td><td>CIUDAD</td> <td>PATENTE</td><td>DESCRIPCION</td> </tr>";
        String[] tmp = post.split("}");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";

        }
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getVehicleBy(String vehicle) {
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "VEHICULO<br><br>";
        s = s + "<tr> <td>Patente</td><td>Marca</td> <td>Modelo</td> <td>Color</td> <td>Tipo</td> <td>ID_Dueño</td> <td>ciudad</td><td>Pasajeros</td><td>km</td><td>carga</td><</tr>";
        String[] tmp = vehicle.split("}");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";

        }
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String IngresarUsuario() {
        String s = "<br>INGRESAR UN NUEVO USUARIO <br><br><form action=\"/insertuser\" method=\"post\">";
        s = s + "Nombre: <input type=\"text\" name=\"first_name\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Apellido: <input type=\"text\" name=\"last_name\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Email: <input type=\"text\" name=\"email\" size=\"25\" maxlength=\"50\"><br><br>";
         s = s + "Contrasena: <input type=\"text\" name=\"contrasena\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String IngresarCiudad() {
        String s = "<br>INGRESAR UNA CIUDAD <br><br><form action=\"/insertcity\" method=\"post\">";
        s = s + "Provincia: <input type=\"text\" name=\"provincia\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Ciudad: <input type=\"text\" name=\"ciudad\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Cod_Postal: <input type=\"text\" name=\"codigo_postal\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Direccion: <input type=\"text\" name=\"direccion\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String IngresarPost(User user) {
        String s = "<br>INGRESAR UN NUEVO POST <br><br><form action=\"/insertpost\" method=\"post\">";
        s = s + "Id Usuario: <input type=\"text\" name=\"id_user\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Descripcion: <input type=\"text\" name=\"descripcion\" size=\"25\" maxlength=\"50\"><br><br>";
        
        
        s = s + "Patente Vehiculo: <input type=\"text\" name=\"patente\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String IngresarRespuesta() {
        String s = "<br>INGRESAR UN NUEVA RESPUESTA <br><br><form action=\"/insertanswer\" method=\"post\" enctype=\"text/plain\">";
        s = s + "Id Pregunta: <input type=\"text\" name=\"id_askdd\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Descripcion: <input type=\"text\" name=\"descripcion\" size=\"255\" maxlength=\"200\"><br><br>";
        s = s + "id usuario: <input type=\"text\" name=\"id_user\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String IngresarPregunta() {
        String s = "<br>INGRESAR UN NUEVA PREGUNTA <br><br><form action=\"/insertanswer\" method=\"post\">";
        s = s + "Id post: <input type=\"text\" name=\"id_post\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Descripcion: <input type=\"text\" name=\"descripcion\" size=\"255\" maxlength=\"200\"><br><br>";
        s = s + "id usuario: <input type=\"text\" name=\"id_user\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String IngresarAutomovil() {
        String s = "<br>INGRESAR UN NUEVO VEHICULO <br><br><form action=\"/insertvehicle\" method=\"post\">";
        s = s + "Marca: <input type=\"text\" name=\"marca\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Modelo: <input type=\"text\" name=\"modelo\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Patente: <input type=\"text\" name=\"patente\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Color: <input type=\"text\" name=\"color\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Tipo: <input type=\"text\" name=\"tipo\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Pasajeros(carro): <input type=\"text\" name=\"pasajeros\" size=\"25\" maxlength=\"50\"><br><br>";
         s = s + "Km(moto): <input type=\"text\" name=\"km\" size=\"25\" maxlength=\"50\"><br><br>";
          s = s +"Carga(camion):                <input type=\"text\" name=\"carga\" size=\"25\" maxlength=\"50\"><br><br>";
       s = s + "ciudad(donde esta el vehiculo): <input type=\"text\" name=\"ciudad\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";


        return s;

    }

    public String getAutomoviles(String autos) {
        String[] tmp = autos.split(",");
        System.out.println(tmp[0]);
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 5px solid;\">";
        s = s + "VEHICULOS REGISTRADOS<br><br>";
        s = s + "<tr> <td>Patente</td><td>Marca</td> <td>Modelo</td> <td>Color</td> <td>Tipo</td> <td>ID_Dueño</td><td>ciudad</td><td>Pasajeros</td><td>km</td><td>carga</td> </tr>";
        for (int i = 0; i < tmp.length ; i++) {
            s = s + "<tr>";
            String[] tm = tmp[i].split("}");
            for (int j = 0; j < tm.length; j++) {
                s = s + "<td>" + tm[j] + "</td>";

            }
            s = s + "</tr>";
//            
        }
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getCities(String cities) {
        String[] tmp = cities.split(",");
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 5px solid;\">";
        s = s + "CIUDADES REGISTRADOS<br><br>";
        s = s + "<tr> <td>Provincia</td><td>ciudad</td> <td>cod_postal</td> <td>Direccion</td> </tr>";
        for (int i = 0; i < tmp.length - 1; i++) {
            s = s + "<tr>";
            String[] tm = tmp[i].split(" ");
            for (int j = 0; j < 4; j++) {
                s = s + "<td>" + tm[j] + "</td>";

            }
            s = s + "</tr>";
//            
        }
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getAnswers(String answers) {
        String[] tmp = answers.split(",");
        System.out.println(tmp[0]);
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "     RESPUESTAS<br><br>";
        s = s + "<tr> <td>id_post</td><td>id_user</td> <td>Descripcion</td> </tr>";
        for (int i = 0; i < tmp.length - 1; i++) {
            s = s + "<tr>";
            String[] tm = tmp[i].split(" ");
            for (int j = 0; j < 3; j++) {
                s = s + "<td>" + tm[j] + "</td>";

            }
            s = s + "</tr>";
//            
        }
        s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getAnswersByid(String answers) {
        String[] tmp = answers.split(" ");
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "     RESPUESTAS<br><br>";
        s = s + "<tr> <td>id_post</td><td>id_user</td> <td>Descripcion</td> </tr>";

        s = s + "<tr>";
        for (int j = 0; j < 3; j++) {
            s = s + "<td>" + tmp[j] + "</td>";

        }
        s = s + "</tr>";
//            
s = s + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getPost(String post) {
        String[] tmp = post.split(",");
        System.out.println(tmp[0]);
        String s = "<br>POST<br><br><form action=\"/post\" method=\"post\">";
         s+= "<table border=\"1\"style=\"border-collapse: separate; border: blue 2px solid;\">";
        s = s + "<tr> <td>ID</td><td>DESCRIPCION</td><td>DUENO</td> <td>PATENTE</td> </tr>";
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<tr>";
            String[] tm = tmp[i].split("}");
            for (int j = 0; j < tm.length; j++) {
                s = s + "<td>" + tm[j] + "</td>";

            }
            s = s + "</tr>";
//            
        }
        s+="</table>";
        s = s + " <br><br><div align=\"left\"><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"> Ver Post: <input type=\"text\" name=\"id_post\" size=\"3\" maxlength=\"50\"> <input type=\"submit\"  value=\"ok\"> ";
        s+="</form>";
        return s;
    }

    public String loginUsuario() {
        String s = "<br> <br><br><form action=\"/loginuser\" method=\"post\"> <br><br><div align=\"center\">CARSAPP <br><br>";
        s = s + "Email: <input type=\" type=\"text\" name=\"email\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Contraseña: <input type=\"text\" name=\"contrasena\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "<br><br><input type=\"submit\"  value=\"ingresar\">";
        s = s + "</form>";
        return s;
    }

    public String webpage() {
        String page = "<form action=\"Crear Vehiculo\" method=\"post\" >";

        page = page + "<br><br><div align=\"center\">CARS APP<br><br>";

        page += "<a href=\"http://localhost:4567/insertpost\" onclick=\"myJsFunc();\"> * Crear Post </a><br>";
        page += "<a href=\"http://localhost:4567/insertvehicle\" onclick=\"myJsFunc();\">* Agregar Vehículos </a><br>";
        page += "<a href=\"http://localhost:4567/insertcity\" onclick=\"myJsFunc();\">* Agregar Direccion </a><br>";
        page += "<a href=\"http://localhost:4567/ownvehicles\" onclick=\"myJsFunc();\">* Ver mis Vehiculos </a><br>";
        page += "<a href=\"http://localhost:4567/post\" onclick=\"myJsFunc();\">* Ver Todos </a><br>";
        page += "<a href=\"http://localhost:4567/ownpost\" onclick=\"myJsFunc();\">* Ver mis Posts </a><br>";
        page += "<a href=\"http://localhost:4567/admincontact\" onclick=\"myJsFunc();\">* Contactar con Admin </a></form>";
page = page + " <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return page;
    }

   public String  admin() {
       String s ="<form action=\"Admin Control Panel\" method=\"post\">";

s = s +"<br><br><div align=\"center\"> ADMIN CARSAPP<br><br>";

s = s +"*<a href=\"http://localhost:4567/user\" onclick=\"myJsFunc();\"> Ver Usuarios </a><br><br>";
s = s +"*<a href=\"http://localhost:4567/admincp/deletepost\" onclick=\"myJsFunc();\"> Eliminar Post </a><br><br>";
s = s +"*<a href=\"http://localhost:4567/insertuser\" onclick=\"myJsFunc();\"> Crear Usuario </a><br><br>";
s = s +"*<a href=\"http://localhost:4567/admincp/blockuser\" onclick=\"myJsFunc();\"> Bloquear Usuario </a><br><br>";
s = s +"*<a href=\"http://localhost:4567/admincp/eraseuserquestion\" onclick=\"myJsFunc();\"> Borrar pregunta de Usuario </a><br><br>";
s = s +"*<a href=\"http://localhost:4567/admincp/eraseuseranswer\" onclick=\"myJsFunc();\"> Borrar respuesta de Usuario </a><br><br>";
s = s +"*<a href=\"http://localhost:4567/admincp/inbox\" onclick=\"myJsFunc();\"> Bandeja de Mensajes </a>";
s+=" <br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";
return s;
    }
}
