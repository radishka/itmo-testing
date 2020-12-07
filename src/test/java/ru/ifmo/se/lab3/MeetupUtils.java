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
        driver.manage().addCookie(new Cookie.Builder("MEETUP_LANGUAGE", "language=ru&country=RU").build());
        driver.manage().addCookie(new Cookie.Builder("MEETUP_MEMBER", "id=321185883&status=1&timestamp=1607283944&bs=0&tz=Europe%2FMoscow&zip=meetup2&country=ru&city=St.+Petersburg&state=&lat=59.93&lon=30.32&ql=false&s=c2efd4c48407d85ca76251f51bbb480f0aa24443&scope=ALL").build());
        driver.manage().addCookie(new Cookie.Builder("MEETUP_BROWSER_ID", "\"id=f9a8a91e-b49b-46e3-aa29-5d970f2e80f2\"").build());
    }

    public static void fillSearchForm(String place, WebDriver driver, WebDriverWait wait) {
        WebElement placeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//input[@aria-label='Search for location by city or zip code']")));
        placeInput.sendKeys(place);

        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
        searchButton.click();
    }
}
