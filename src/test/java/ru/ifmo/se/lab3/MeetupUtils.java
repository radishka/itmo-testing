package ru.ifmo.se.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MeetupUtils {
    public static void getURLWithCookie(String url, WebDriver driver){
        driver.get(url);
        driver.manage().addCookie(new Cookie.Builder("MEETUP_LANGUAGE", "***").build());
        driver.manage().addCookie(new Cookie.Builder("MEETUP_MEMBER", "***").build());
        driver.manage().addCookie(new Cookie.Builder("MEETUP_BROWSER_ID", "***").build());
    }

    public static void fillSearchForm(String place, WebDriver driver, WebDriverWait wait) {
        WebElement placeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//input[@aria-label='Search for location by city or zip code']")));
        placeInput.sendKeys(place);

        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
        searchButton.click();
    }
}
