package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;

    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        wd = new ChromeDriver(options);
        logger.info("All tests run in Chrome Browser");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wd.navigate().to("https://ilcarro.web.app/");
        logger.info("The link --->" + wd.getCurrentUrl());
      //  wd.navigate().back(); --> back
      //  wd.navigate().refresh(); --> refresh
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);


    }

    public HelperUser getHelperUser() {

        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public void stop(){

        wd.quit();

    }
}
