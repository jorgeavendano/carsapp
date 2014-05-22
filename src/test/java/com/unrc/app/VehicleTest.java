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
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        System.out.println("VehicleTest setup");
        Base.openTransaction();
    }

    @After
    public void after() {
        System.out.println("Vehicle tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields() {
        Vehicle vehicle = new Vehicle();
        Car car =new Car();
        Truck truck = new Truck();
        User user = new User();
	the(user).shouldNotBe("valid");
	the(vehicle).shouldNotBe("valid");
        the(vehicle.errors().get("mark")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("model")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("patents")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("id_user")).shouldBeEqual("value is missing");

        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        vehicle.set("mark","bmw","model","2014","patents","kff911","id_user","1");
        vehicle.set("mark","scania","model","2012","patents","kas731","id_user","1");
        car.set("patents","kff911","isCoupe","false");
        truck.set("patents","kas731","capacity","1000");

        // Everything is good:
        the(user).shouldBe("valid");
        the(vehicle).shouldBe("valid");
    }
}
