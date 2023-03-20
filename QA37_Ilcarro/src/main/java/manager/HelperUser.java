package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm(){
//        WebElement LoginTab = wd.findElement(By.xpath("//a[text()='Log in']"));
//        //css a[href='/login?url=%2Fsearch']
//        LoginTab.click();
        click(By.cssSelector("a[href='/login?url=%2Fsearch']"));

    }
    public void fillLoginForm(String email, String password){
//        WebElement emailInput = wd.findElement(By.id("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.id("email"),email);

//        WebElement passwordInput = wd.findElement(By.xpath("//input[@id='password']"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
        type(By.xpath("//input[@id='password']"), password);

    }
    public void submitLogin(){
        click(By.xpath("//*[@type='submit']"));
    }
}
