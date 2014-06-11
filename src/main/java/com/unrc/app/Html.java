/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app;

/**
 *
 * @author jorgea
 */
public class Html {

    public String getAllUsers(String users) {
        String s = "<br><br> Usuarios registrados en la base de datos";
        s = s + "<br><br>";
        s = s + "<select name=\"Usuarios\" size=\"4\">";
        String[] tmp = users.split(",");
        for (int i = 0; i < tmp.length - 1; i++) {
            s = s + "<option value=" + "\"" + i + "\"" + ">" + tmp[i];

        }

        s = s + "</select> <br><br><div align=\"left\"><form><input type=\"button\" onclick=\"javascript: history.back()\" value=\"Volver\"></form>";

        return s;
    }

    public String getUserBy(String user) {
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "Usuario<br><br>";
        s = s + "<tr> <td>Nombre</td><td>Apellido</td> <td>Email</td> </tr>";
        String[]tmp = user.split(",");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";
            
        }
        return s;
    }
    public String getPostBy(String post) {
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "POST<br><br>";
        s = s + "<tr> <td>DUENO</td><td>CIUDAD</td> <td>PATENTE</td><td>DESCRIPCION</td> </tr>";
        String[]tmp = post.split("}");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";
            
        }
        return s;
    }
        public String getVehicleBy(String vehicle) {
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 2px solid;\">";
        s = s + "VEHICULO<br><br>";
        s = s + "<tr> <td>Patente</td><td>Marca</td> <td>Modelo</td> <td>Color</td> <td>Tipo</td> <td>ID_Dueño</td> </tr>";
        String[]tmp = vehicle.split(",");
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<td>" + tmp[i] + "</td>";
            
        }
        return s;
    }

    public String IngresarUsuario() {
        String s = "<br>INGRESAR UN NUEVO USUARIO <br><br><form action=\"/insertuser\" method=\"post\">";
        s = s + "Nombre: <input type=\"text\" name=\"first_name\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Apellido: <input type=\"text\" name=\"last_name\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Email: <input type=\"text\" name=\"email\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s+ "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        return s;
    }
    public String IngresarCiudad() {
        String s = "<br>INGRESAR UNA CIUDAD <br><br><form action=\"/insertcity\" method=\"post\">";
        s = s + "Provincia: <input type=\"text\" name=\"provincia\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Ciudad: <input type=\"text\" name=\"ciudad\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Cod_Postal: <input type=\"text\" name=\"codigo_postal\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Direccion: <input type=\"text\" name=\"direccion\" size=\"25\" maxlength=\"50\"><br><br>";
        s=s+"<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        return s;    
    }
    public String IngresarPost() {
        String s = "<br>INGRESAR UN NUEVO POST <br><br><form action=\"/insertpost\" method=\"post\">";
        s = s + "Id Usuario: <input type=\"text\" name=\"id_user\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Descripcion: <input type=\"text\" name=\"descripcion\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Patente Vehiculo: <input type=\"text\" name=\"patente\" size=\"25\" maxlength=\"50\"><br><br>";
        s=s+"<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        return s;
    }
    public String IngresarRespuesta() {
        String s = "<br>INGRESAR UN NUEVA RESPUESTA <br><br><form action=\"/insertanswer\" method=\"post\" enctype=\"text/plain\">";
        s = s + "Id Pregunta: <input type=\"text\" name=\"id_askdd\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Descripcion: <input type=\"text\" name=\"descripcion\" size=\"255\" maxlength=\"200\"><br><br>";
        s = s + "id usuario: <input type=\"text\" name=\"id_user\" size=\"25\" maxlength=\"50\"><br><br>";
        s=s+"<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        return s;
    }
        public String IngresarPregunta() {
        String s = "<br>INGRESAR UN NUEVA PREGUNTA <br><br><form action=\"/insertanswer\" method=\"post\">";
        s = s + "Id post: <input type=\"text\" name=\"id_post\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Descripcion: <input type=\"text\" name=\"descripcion\" size=\"255\" maxlength=\"200\"><br><br>";
        s = s + "id usuario: <input type=\"text\" name=\"id_user\" size=\"25\" maxlength=\"50\"><br><br>";
        s=s+"<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";
        return s;
    }

    public String IngresarAutomovil() {
        String s = "<br>INGRESAR UN NUEVO VEHICULO <br><br><form action=\"/insertvehicle\" method=\"post\">";
        s = s + "Marca: <input type=\"text\" name=\"marca\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Modelo: <input type=\"text\" name=\"modelo\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Patente: <input type=\"text\" name=\"patente\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Color: <input type=\"text\" name=\"color\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "Tipo: <input type=\"text\" name=\"tipo\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s + "id_dueno: <input type=\"text\" name=\"id_dueno\" size=\"25\" maxlength=\"50\"><br><br>";
        s = s+ "<br><br><div align=\"left\"><input type=\"submit\"  value=\"ingresar\"><input type=\"reset\"  value=\"Reset\">";
        s = s + "</form>";

        s = s + "</form>";
        return s;

    }
    public String getAutomoviles(String autos) {
        String[] tmp = autos.split(",");
        System.out.println(tmp[0]);
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: red 5px solid;\">";
        s = s + "VEHICULOS REGISTRADOS<br><br>";
        s = s + "<tr> <td>Patente</td><td>Marca</td> <td>Modelo</td> <td>Color</td> <td>Tipo</td> <td>ID_Dueño</td> </tr>";
        for (int i = 0; i < tmp.length - 1; i++) {
            s = s + "<tr>";
            String[] tm = tmp[i].split(" ");
            for (int j = 0; j < 6; j++) {
                s = s + "<td>" + tm[j] + "</td>";

            }
            s = s + "</tr>";
//            
        }
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
        
        return s;
    }
        public String getPost(String post) {
        String[] tmp = post.split(",");
        System.out.println(tmp[0]);
        String s = "<table border=\"1\"style=\"border-collapse: separate; border: blue 2px solid;\">";
        s = s + "           POST DE VEHICULOS<br><br>";
        s = s + "<tr> <td>DESCRIPCION</td><td>DUENO</td> <td>PATENTE</td> </tr>";
        for (int i = 0; i < tmp.length; i++) {
            s = s + "<tr>";
            String[] tm = tmp[i].split("}");
            for (int j = 0; j < 3; j++) {
                s = s + "<td>" + tm[j] + "</td>";

            }
            s = s + "</tr>";
//            
        }
        return s;
    }



}
