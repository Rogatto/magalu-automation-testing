package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElementHelp {

    private static final int timeForTimeout = 40;

    public static void byElementToBeClickable (WebDriver driver, By byElement) {

        WebDriverWait wait = new WebDriverWait(driver,timeForTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }

    public static void byTextToBePresentInElement (WebDriver driver, By byElement, String texto) {

        WebDriverWait wait = new WebDriverWait(driver, timeForTimeout);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(byElement, texto));
    }
}
