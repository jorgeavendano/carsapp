package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Post;
import com.unrc.app.models.Question;
import com.unrc.app.models.Vehicle;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class QuestionTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        System.out.println("UserTest setup");
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("UserTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
        User user = new User();
        User user2 = new User();
        Vehicle vehicle = new Vehicle();
        Post post = new Post();
        Question question = new Question();
        the(question).shouldNotBe("valid");
        the(user).shouldNotBe("valid");
        the(post).shouldNotBe("valid");
        the(vehicle).shouldNotBe("valid");
        the(post.errors().get("descripcion")).shouldBeEqual("value is missing");
        the(post.errors().get("id_dueno")).shouldBeEqual("value is missing");
        the(post.errors().get("patente_vehiculo")).shouldBeEqual("value is missing");
        post.set("descripcion","-------","id_dueno","1","patente_vehiculo","kff911","ciudad","rio cuarto");
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user2.set("first_name", "Juan", "last_name", "hulk", "email", "example2@email.com");
        vehicle.set("marca","bmw","modelo","2014","patente","kff911","color","negro","tipo","carro","id_dueno","1");
        question.set("descripcion","cual es el precio","id_user","2","id_post","1");
        
        

        // Everything is good:
        the(user).shouldBe("valid");
        the(post).shouldBe("valid");
        the(vehicle).shouldBe("valid");
        the(question).shouldBe("valid");
    }
}
