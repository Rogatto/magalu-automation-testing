package magalu.pageobjects;

import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
@Builder
public class MagazineLuizaMainPage {

  public MagazineLuizaMainPage(WebDriver driver) {
    this.driver = driver;
  }

  public WebDriver driver;
  private final By searchField = By.id("inpHeaderSearch");
  private final By searchButton = By.id("btnHeaderSearch");
  private final By walletProductButton = By.xpath("/html/body/div[3]/div[5]/div[1]/div[4]/div[2]/button[2]");
  private final By warrantyButton = By.id("rf-warranty");
  private final By productName = By.className("nm-product-name");

  public String searchProduct(String product) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(searchField));

    driver.findElement(searchField).clear();
    driver.findElement(searchField).sendKeys(product);
    driver.findElement(searchButton).click();

    wait.until(ExpectedConditions.elementToBeClickable(productName));

    return driver.findElement(productName).getText();
  }

  public boolean addProductToOrder(String product, boolean isAddWarrantProtect)
      throws InterruptedException {

    searchProduct(product);

    driver.findElement(productName).click();

    boolean isProductAddToOrder = false;

    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(walletProductButton));

    if(driver.findElement(walletProductButton).isEnabled()) {
      driver.findElement(walletProductButton).click();

      wait.until(ExpectedConditions.elementToBeClickable(warrantyButton));
      if(driver.findElement(warrantyButton).isEnabled() && isAddWarrantProtect){
          driver.findElement(warrantyButton).click();
        }

         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0,1000)");

         driver.findElement(By.linkText("continuar")).click();

         isProductAddToOrder = true;
      }
    return isProductAddToOrder;
  }
}
