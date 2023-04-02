package tests;

import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {
    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("tirex@gmail.com").setPassword("Rr12345$"));
        }
    }

    @Test
    public void addNewCarSuccessAll() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv,Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(5)
                .carClass("C")
                .carRegNumber("678-900-" + i)
                .price(50)
                .about("Very bice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        app.getHelperCar().attachPhoto("C:\\QA_Automation\\QA37_Ilcarro\\QA37_Ilcarro\\2021-bmw-m5.jpg");

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture() + " " + car.getModel() + " added successful");
    }
    @Test
    public void addNewCarSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Car car = Car.builder()
                .location("Tel Aviv,Israel")
                .manufacture("BMW")
                .model("M5")
                .year("2020")
                .fuel("Petrol")
                .seats(5)
                .carClass("C")
                .carRegNumber("678-555-" + i)
                .price(50)
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture() + " " + car.getModel() + " added successful");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHome();

    }
}
