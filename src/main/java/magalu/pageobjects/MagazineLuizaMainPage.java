package magalu.pageobjects;

import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
  private final By productPrice = By.className("nm-price-container");

  public String searchProduct(String product){
    driver.findElement(searchField).clear();
    driver.findElement(searchField).sendKeys(product);
    driver.findElement(searchButton).click();

    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));

    return driver.findElement(productPrice).getText();
  }

  public boolean addProductToOrder(String product, boolean isAddWarrantProtect){

    searchProduct(product);

    boolean isProductAddToOrder = false;

    String totalProduct = driver.findElement(By.className("nm-total-results")).getText();

    System.out.println("Total of products founded: " + totalProduct);

    driver.findElement(productPrice).click();

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
