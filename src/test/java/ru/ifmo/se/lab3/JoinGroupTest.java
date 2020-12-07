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

import java.io.IOException;

import static ru.ifmo.se.lab3.ScreenshotUtils.*;
import static ru.ifmo.se.lab3.MeetupUtils.*;


public class JoinGroupTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String PATH = "./src/test/java/ru/ifmo/se/lab3/screenshots/joinGroup/";

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
    public static void after() {
        driver.quit();
    }

    @Test
    public void joinGroup() throws IOException, InterruptedException {
        deleteAllFilesFolder(PATH);
        getURLWithCookie("http://www.meetup.com", driver);

        createScreenshot(PATH, driver);

        fillSearchForm("Санкт-Петербург", driver, wait);

        createScreenshot(PATH, driver);

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='css-123pp85']")));

        String myNewGroup = null;

        if(message.getText().contains("Events near Санкт-Петербург")){

            WebElement event = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("//h1[contains(text(),'Events near Санкт-Петербург')]/following-sibling::div[1]/div/div[1]")));
            event.click();

            WebElement group = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("//div[@class='chunk event-group-chunk']")));
            createScreenshot(PATH, driver);
            group.click();

            WebElement groupName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("//h1/a")));
            myNewGroup = groupName.getText();

            WebElement joinButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("//a[text()='Вступить в эту группу']")));
            createScreenshot(PATH, driver);
            joinButton.click();

        }

        driver.get("https://www.meetup.com/ru-RU/");

        WebElement onlyMyGroups = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='simple-event-filter']/li[4]")));
        createScreenshot(PATH, driver);
        onlyMyGroups.click();

        Thread.sleep(2000);

        WebElement myGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-view='list']/li[2]/ul/li/div[2]/div/div/a/span")));

        createScreenshot(PATH, driver);

        Assert.assertEquals(myGroup.getText(), myNewGroup);
    }
}
