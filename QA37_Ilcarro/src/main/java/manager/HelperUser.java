package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm(){
//        WebElement LoginTab = wd.findElement(By.xpath("//a[text()='Log in']"));
//        //css a[href='/login?url=%2Fsearch']
//        LoginTab.click();
        //click(By.cssSelector("a[href ^='/login']"));
        click(By.xpath("//a[text()=' Log in ']"));

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
    public String getMessage(){
        //wait
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));

        //pause(8000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
        //WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
        //String text = element.getText();
        //return text;
    }
    public boolean isLogged(){
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

  public void logOut(){
    click(By.xpath("//a[text()=' Logout ']"));
  }

    public void closeWindow() {
        click(By.xpath("//button[text()='Ok']"));
    }
}
