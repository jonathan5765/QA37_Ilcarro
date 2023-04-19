package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.xpath("//input[@id='year']"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        //type(By.id("price"), String.valueOf(car.getPrice()));
        type(By.id("price"), car.getPrice() + "");// the same result: double/int --> String
        type(By.id("about"), car.getAbout());

    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //gas
//        select.deselectByIndex(5);
//        select.selectByValue("Gas");
//        select.deselectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        //"4/25/2023", "4/28/2023"   25   28

        String locatorFrom = "//div[text()=' " + dayFrom(dateFrom) + " ']";
      //  pause(500);
        click(By.xpath(locatorFrom));
        String locatorTo = "//div[text()=' " + dayTo(dateTo) + " ']";
      //  pause(500);
        click(By.xpath(locatorTo));

    }

    private String dayTo(String dateTo) {
        String[] x = dateTo.split("/");
        return x[1];
    }

    private String dayFrom(String dateFrom) {
        String[] x = dateFrom.split("/");
        return x[1];
    }


    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarAppeared() {
        boolean res = isElementPresent(By.cssSelector(".car-img-container"));
        WebElement element = wd.findElement(By.cssSelector(".car-img-container"));
      //  boolean result = element.isEnabled();
        return res; //&& !result;

    }
}
