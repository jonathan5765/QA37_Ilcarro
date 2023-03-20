package tests;

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
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirex@gmail.com", "Rr12345$");
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeWindow();
    }
    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirex@gmail.com", "Rr12345$");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().closeWindow();


        //Assert if element with text "Logged in success" is present
        //app.getHelperUser().closeWindow();
        //Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeWindow();
    }
    @Test
    public void loginWrongEmail(){

    }
    @Test
    public void loginWrongPassword(){

    }
}
