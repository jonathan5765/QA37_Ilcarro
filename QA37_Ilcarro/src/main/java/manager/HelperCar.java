package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"4/25/2023", "4/28/2023"   25   28

        String[] from = dateFrom.split("/");
        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        //  pause(500);
        click(By.xpath(locatorFrom));
        String[] to = dateTo.split("/");
        String locatorTo = "//div[text()=' " + to[1] + " ']";
        //  pause(500);
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarAppeared() {

        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"6/30/2023", "10/28/2023"
        LocalDate now = LocalDate.now();
        System.out.println(now); //2023-04-20

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));
        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }


        //"//div[text()=' " + from[1] + " ']"
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());

        click(By.xpath(locator));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));

        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        int diffYear;
        int diffMonth;
        //****from
        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) {//2023=2023
            diffMonth = from.getMonthValue() - now.getMonthValue();//10-4=6
        } else {//2023!=2024 --> 12 - 4 + 2
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));
        //****to
        diffYear = to.getYear() - from.getYear();
        if(diffYear ==0){
            diffMonth = to.getMonthValue() - from.getMonthValue();
        }else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        type(By.id("dates"), dateFrom  + " - " + dateTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
     //   wd.findElement(By.id("dates")).sendKeys(dateFrom  + " - " + dateTo);

    }

    public boolean isErrorDisplayed(String message) {

        String text = wd.findElement(By.cssSelector("div.error")).getText();
        return text.equals(message);
    }
}
