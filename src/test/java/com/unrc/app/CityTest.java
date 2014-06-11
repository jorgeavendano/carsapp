package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.City;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class CityTest{
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
        City adress = new City();
        the(user).shouldNotBe("valid");
        the(adress).shouldNotBe("valid");
        the(adress.errors().get("provincia")).shouldBeEqual("value is missing");
        the(adress.errors().get("ciudad")).shouldBeEqual("value is missing");
        the(adress.errors().get("codigo_postal")).shouldBeEqual("value is missing");
        the(adress.errors().get("direccion")).shouldBeEqual("value is missing");
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        adress.set("provincia","cordoba","ciudad","rio cuarto","codigo_postal","9","direccion","buenosaires43");
       
        // Everything is good:
        the(user).shouldBe("valid");
        the(adress).shouldBe("valid");
    }
}
