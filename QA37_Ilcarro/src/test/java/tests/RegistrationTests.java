package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestBase;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(10000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User().setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + i + "@gmail.com")
                .setPassword("Snow12345$");
        logger.info("Tests run with data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        //You are logged in success
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }

    @Test
    public void registrationEmptyName() {

        User user = new User().setFirstName(" ")
                .setLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("Snow12345$");
        logger.info("Tests run with data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        app.getHelperUser().pause(2000);

        Assert.assertEquals(app.getHelperUser().getMessageWrongRegistration(),"Registration failed\n" +
                "{\"firstName\":\"must not be blank\"}\n" +
                "Ok");

    }
    @Test
    public void registrationEmptyLastName() {

        User user = new User().setFirstName("Lisa")
                .setLastName(" ")
                .setEmail("snow@gmail.com")
                .setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        //app.getHelperUser().pause(2000);

        Assert.assertEquals(app.getHelperUser().getMessageWrongRegistration(),"Registration failed\n" +
                "{\"lastName\":\"must not be blank\"}\n" +
                "Ok");
    }
    @Test
    public void registrationWrongEmail() {

        User user = new User().setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snowgmail.com")
                .setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format\n" +
                "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongPassword() {

        User user = new User().setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("Snow123");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test(enabled = false)
    public void registrationUserExists() {

        User user = new User().setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("Snow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        app.getHelperUser().pause(2000);

        Assert.assertEquals(app.getHelperUser().getMessageWrongRegistration(),"Registration failed\n" +
                "\"User already exists\"\n" +
                "Ok");

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeWindow();
    }

}
