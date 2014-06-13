package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Vehicle;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class VehicleTest {

    @Before
    public void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "root");
        System.out.println("VehicleTest setup");
        Base.openTransaction();
    }

    @After
    public void after() {
        System.out.println("Vehicle tearDown");
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields() {
        Vehicle vehicle = new Vehicle();
        User user = new User();
	the(user).shouldNotBe("valid");
	the(vehicle).shouldNotBe("valid");
        the(vehicle.errors().get("marca")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("modelo")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("patente")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("id_user")).shouldBeEqual("value is missing");

        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        vehicle.set("marca","bmw","modelo","2014","patente",54343,"color","negro","tipo","carro","id_dueno","1");

        // Everything is good:
        
        the(user).shouldBe("valid");
        the(vehicle).shouldBe("valid");
        
    }
}
