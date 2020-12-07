package ru.ifmo.se.lab3;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static ru.ifmo.se.lab3.ScreenshotUtils.*;
import static ru.ifmo.se.lab3.MeetupUtils.*;

import java.io.IOException;

public class JoinEventTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String PATH = "./src/test/java/ru/ifmo/se/lab3/screenshots/joinEvent/";

    @BeforeClass
    public static void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("start-maximized");
        options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public static void after() throws IOException, InterruptedException {
        String path = PATH + "afterClass/";
        deleteAllFilesFolder(path);

        unsubscribeEvent(path);

        driver.quit();
    }

    public static void unsubscribeEvent(String path) throws IOException, InterruptedException {

        WebElement myEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//ul[@data-view='list']/li[2]/ul/li/div[2]/div/a/span")));
        myEvent.click();

        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//button[@data-e2e='event-footer--edit-rsvp-btn']")));

        createScreenshot(path, driver);

        editButton.click();
        Thread.sleep(2000);
        createScreenshot(path, driver);

        WebElement notGoingButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-e2e='rsvp-modal--not-going-btn']")));
        notGoingButton.click();

        Thread.sleep(2000);
        createScreenshot(path, driver);
    }

    @Test
    public void joinEvent() throws IOException, InterruptedException {
        deleteAllFilesFolder(PATH);
        getURLWithCookie("http://www.meetup.com", driver);

        createScreenshot(PATH, driver);

        fillSearchForm("Санкт-Петербург", driver, wait);

        createScreenshot(PATH, driver);

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        String myNewEventName = null;

        if(message.getText().contains("Events near Санкт-Петербург")){

            WebElement event = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("//h1[contains(text(),'Events near Санкт-Петербург')]/following-sibling::div[1]/div/div[1]")));
            createScreenshot(PATH, driver);
            event.click();

            WebElement myNewEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
            myNewEventName = myNewEvent.getText();

            WebElement joinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                    .xpath("//button[@class='gtmEventFooter--attend-btn']")));
            joinButton.click();
            Thread.sleep(2000);
            createScreenshot(PATH, driver);

        }

        driver.get("https://www.meetup.com/ru-RU/");

        WebElement onlyMyEvents = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='simple-event-filter']/li[5]")));
        createScreenshot(PATH, driver);
        onlyMyEvents.click();

        Thread.sleep(2000);

        WebElement myEvent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-view='list']/li[2]/ul/li/div[2]/div/a/span")));

        createScreenshot(PATH, driver);

        Assert.assertEquals(myEvent.getText(), myNewEventName);
    }
}
