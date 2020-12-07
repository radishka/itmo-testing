package ru.ifmo.se.lab3;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }

    public static void createScreenshot(String path, WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(path + screenshot.getName()));
    }


}
