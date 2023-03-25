package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logOut();
        }
    }
    @Test
    public void loginSuccess1(){
        User user = new User().setEmail("tirex@gmail.com").setPassword("Rr12345$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        //Assert
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeWindow();
    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirex@gmail.com", "Rr12345$");
        app.getHelperUser().submit();

        //Assert
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeWindow();
    }
    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirex@gmail.com", "Rr12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeWindow();


        //Assert if element with text "Logged in success" is present
        //app.getHelperUser().closeWindow();
        //Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirexgmail.com", "Rr12345$");
        app.getHelperUser().submit();
        //Assert if element with text "Is`not look like email" is present
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());


    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirex@gmail.com", "Rr12345");
        app.getHelperUser().submit();
        //Assert if element with text "Login or Password incorrect" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }

//    @Test
//    public void loginUnregistered(){
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("tire@gmail.com", "Rr12345$");
//        app.getHelperUser().submitLogin();
//        //Assert if element with text "Login or Password incorrect" is present
//        Assert.assertEquals(app.getHelperUser().getMessage(), "Login or Password incorrect/");

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
}