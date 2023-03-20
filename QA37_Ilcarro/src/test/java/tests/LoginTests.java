package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tirex@gmail.com", "Rr12345$");
        app.getHelperUser().submitLogin();

        //Assert

    }
}
