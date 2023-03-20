package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ApplicationManager {
    WebDriver wd;
    HelperUser helperUser;

    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        wd = new ChromeDriver(options);
        wd.navigate().to("https://ilcarro.web.app/");
        helperUser = new HelperUser(wd);

    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop(){

        wd.quit();

    }
}
