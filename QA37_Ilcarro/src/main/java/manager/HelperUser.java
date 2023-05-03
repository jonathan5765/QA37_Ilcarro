package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
//        WebElement LoginTab = wd.findElement(By.xpath("//a[text()='Log in']"));
//        //css a[href='/login?url=%2Fsearch']
//        LoginTab.click();
        //click(By.cssSelector("a[href ^='/login']"));
        click(By.xpath("//a[text()=' Log in ']"));

    }

    public void fillLoginForm(String email, String password) {
//        WebElement emailInput = wd.findElement(By.id("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.id("email"), email);

//        WebElement passwordInput = wd.findElement(By.xpath("//input[@id='password']"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
        type(By.xpath("//input[@id='password']"), password);
    }
        public void fillLoginForm(User user){

            type(By.id("email"),user.getEmail());
            type(By.xpath("//input[@id='password']"), user.getPassword());

        }


    public String getMessageWrongRegistration () {
        //wait
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));

        //pause(8000);
        return wd.findElement(By.cssSelector(".dialog-container")).getText();

    }
        public boolean isLogged () {
            return isElementPresent(By.xpath("//a[text()=' Logout ']"));
        }

        public void logOut () {
            click(By.xpath("//a[text()=' Logout ']"));
        }

        public void closeWindow () {
            if (isElementPresent(By.xpath("//button[text()='Ok']")))
                click(By.xpath("//button[text()='Ok']"));
        }

        public String getErrorText () {
            String text = wd.findElement(By.cssSelector("div.error")).getText();
            System.out.println(text);
            return wd.findElement(By.cssSelector("div.error")).getText();
        }


//******************Registration*************************************************************
    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());

    }

    public void checkPolicy() {
       // click(By.cssSelector("label[for='terms-of-use']"));
       // document.querySelector('#terms-of-use').click();
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click();");
    }
    public void checkPolicyXY() {
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            Dimension size = wd.manage().window().getSize();
            System.out.println("Wright screen -->");

            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));

            Dimension dimension = label.getSize();

            Rectangle rect = label.getRect();
            int w = rect.getWidth();

            int xOffSet = -w / 2;
            //int xOffSet = -(label.getRect().getWidth()/2);

            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }
    public void login(User user){
        openLoginForm();
        fillLoginForm(user);
        submit();
        closeWindow();
    }
}
