package ru.ifmo.se.lab3;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.ifmo.se.lab3.ScreenshotUtils.*;

public class SearchingEventsTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String place = "Санкт-Петербург";
    private static final String PATH = "./src/test/java/ru/ifmo/se/lab3/screenshots/searchingEvents/";

    @BeforeClass
    public static void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, 15);

    }

    @AfterClass
    public static void after() {
        driver.quit();
    }

    public void fillSearchForm() {
        WebElement placeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//input[@aria-label='Search for location by city or zip code']")));
        placeInput.sendKeys(place);

        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
        searchButton.click();
    }

    @Test
    public void searchByPlace() throws IOException {
        driver.get("http://www.meetup.com");
        String path = PATH + "searchByPlace/";
        deleteAllFilesFolder(path);

        createScreenshot(path, driver);

        fillSearchForm();
        createScreenshot(path, driver);

        WebElement group, placeName, event;

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        if(message.getText().contains("Events near " + place)){

            for (int i = 1; i < 3; i++){
                event = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                        xpath("//h1[contains(text(),'Events near " + place
                                + "')]/following-sibling::div[1]/div/div["+i+"]")));
                event.click();

                group = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                        xpath("//div[@class='chunk event-group-chunk']")));
                group.click();

                placeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                        xpath("//ul[@class='organizer-city']/li/a/span")));

                assertThat(placeName.getText(), containsString(place));

                createScreenshot(path, driver);

                driver.navigate().back();
                driver.navigate().back();
            }
        }

    }

    @Test
    public void searchByDateToday() throws IOException {
        driver.get("https://www.meetup.com/find/");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d", Locale.ENGLISH);
        Calendar calendar = new GregorianCalendar();
        String currentDate = simpleDateFormat.format(calendar.getTime()).toUpperCase();

        String path = PATH + "searchByDate/today/";
        deleteAllFilesFolder(path);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@aria-label='Search for events by date range']")));

        createScreenshot(path, driver);

        Actions action = new Actions(driver);
        action.click(dateButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        createScreenshot(path, driver);

        if(message.getText().contains("Events near " + place)){

            WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//time[1]")));

            createScreenshot(path, driver);

            assertThat(dateElement.getText(), containsString(currentDate));
        }
    }

    @Test
    public void searchByDateTomorrow() throws IOException {
        driver.get("https://www.meetup.com/find/");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d", Locale.ENGLISH);
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        String tomorrow = simpleDateFormat.format(calendar.getTime()).toUpperCase();

        String path = PATH + "searchByDate/thisWeek/";
        deleteAllFilesFolder(path);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@aria-label='Search for events by date range']")));

        createScreenshot(path, driver);

        Actions action = new Actions(driver);
        action.click(dateButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN).build().perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        createScreenshot(path, driver);

        if(message.getText().contains("Events near " + place)){

            WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//time[1]")));

            createScreenshot(path, driver);

            assertThat(dateElement.getText(), containsString(tomorrow));
        }
    }

    @Test
    public void searchByDateThisWeek() throws IOException {
        driver.get("https://www.meetup.com/find/");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d", Locale.ENGLISH);
        Calendar now = Calendar.getInstance();

        while (now.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
            now.add(Calendar.DAY_OF_YEAR, -1);
        }

        String monday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String tuesday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String wednesday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String thursday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String friday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String saturday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String sunday = simpleDateFormat.format(now.getTime()).toUpperCase();

        String path = PATH + "searchByDate/thisWeek/";
        deleteAllFilesFolder(path);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@aria-label='Search for events by date range']")));

        createScreenshot(path, driver);

        Actions action = new Actions(driver);
        action.click(dateButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN).build()
                .perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        createScreenshot(path, driver);

        if(message.getText().contains("Events near " + place)){

            WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//time[1]")));

            createScreenshot(path, driver);

            assertTrue(dateElement.getText().contains(monday) || dateElement.getText().contains(tuesday) ||
                    dateElement.getText().contains(wednesday) || dateElement.getText().contains(thursday) ||
                    dateElement.getText().contains(friday) || dateElement.getText().contains(saturday) ||
                    dateElement.getText().contains(sunday));
        }

    }

    @Test
    public void searchByDateThisWeekend() throws IOException {
        driver.get("https://www.meetup.com/find/");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d", Locale.ENGLISH);
        Calendar now = Calendar.getInstance();

        while (now.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
            now.add(Calendar.DAY_OF_YEAR, 1);
        }

        String sunday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, -1);
        String saturday = simpleDateFormat.format(now.getTime()).toUpperCase();

        String path = PATH + "searchByDate/thisWeekend/";
        deleteAllFilesFolder(path);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@aria-label='Search for events by date range']")));

        createScreenshot(path, driver);

        Actions action = new Actions(driver);
        action.click(dateButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        createScreenshot(path, driver);

        if(message.getText().contains("Events near " + place)){

            WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//time[1]")));

            createScreenshot(path, driver);

            assertTrue(dateElement.getText().contains(saturday) ||
                    dateElement.getText().contains(sunday));
        }
    }

    @Test
    public void searchByDateNextWeek() throws IOException {
        driver.get("https://www.meetup.com/find/");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d", Locale.ENGLISH);
        Calendar now = Calendar.getInstance();

        if(now.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            now.add(Calendar.DAY_OF_YEAR, 7);
        } else while (now.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
            now.add(Calendar.DAY_OF_YEAR, 1);
        }

        String monday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String tuesday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String wednesday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String thursday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String friday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String saturday = simpleDateFormat.format(now.getTime()).toUpperCase();

        now.add(Calendar.DAY_OF_YEAR, 1);
        String sunday = simpleDateFormat.format(now.getTime()).toUpperCase();

        String path = PATH + "searchByDate/nextWeek/";
        deleteAllFilesFolder(path);

        WebElement dateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//button[@aria-label='Search for events by date range']")));

        createScreenshot(path, driver);

        Actions action = new Actions(driver);
        action.click(dateButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        createScreenshot(path, driver);

        if(message.getText().contains("Events near " + place)){

            WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//time[1]")));

            createScreenshot(path, driver);

            assertTrue(dateElement.getText().contains(monday) || dateElement.getText().contains(tuesday) ||
                    dateElement.getText().contains(wednesday) || dateElement.getText().contains(thursday) ||
                    dateElement.getText().contains(friday) || dateElement.getText().contains(saturday) ||
                    dateElement.getText().contains(sunday));
        }
    }
}
