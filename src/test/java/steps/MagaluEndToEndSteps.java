package steps;

import static utils.PrintScreen.efetuaPrint;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.net.MalformedURLException;
import lombok.extern.slf4j.Slf4j;
import magalu.pageobjects.MagazineLuizaMainPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigureDriver;
import utils.PropertiesUtils;

@Slf4j
public class MagaluEndToEndSteps {

  public WebDriver driver;
  private final String absolutePath = System.getProperty("user.dir");
  public MagazineLuizaMainPage mainPageMagazine;
  public String productValue;
  private String product;
  boolean productAdded;

  @Before
  public void setUp() throws MalformedURLException {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

    ConfigureDriver configureDriver = new ConfigureDriver();
    driver = configureDriver.driverSetUp(PropertiesUtils.extractHostZalenium(),capabilities,this.getClass().getName().substring(8));

    mainPageMagazine = MagazineLuizaMainPage.builder()
        .driver(driver)
        .build();

    log.info("Browser name: " + capabilities.getBrowserName());
    log.info("Plataform: " + capabilities.getPlatform());
  }

  @Given("que estou no site da magazine luiza para efetuar nova compra")
  public void queEstouNoSiteDaMagazineLuizaParaEfetuarNovaCompra() {

    driver.get(PropertiesUtils.extractHostMagalu());
    log.info("Host application: " + driver.getCurrentUrl());

    Assert.assertEquals(driver.getCurrentUrl(), PropertiesUtils.extractHostMagalu());
  }

  @When("efetuo a busca pelo produto {string}")
  public void efetuoABuscaPeloProduto(String product) {
    productValue = mainPageMagazine.searchProduct(product);
    log.info("Product value: " + productValue);
  }

  @Then("devemos ter o retorno do produto sobre a busca efetuada com seu valor e descrição")
  public void devemosTerORetornoDoProdutoSobreABuscaEfetuadaComSeuValorEDescricao()
      throws IOException {
    efetuaPrint(absolutePath + "/evidences/productFounded.png", driver);

    Assert.assertNotNull(productValue);
  }

  @Given("que estamos na tela de consulta da magazine luiza")
  public void queEstamosNaTelaDeConsultaDaMagazineLuiza() {
    driver.get(PropertiesUtils.extractHostMagalu());
  }

  @When("selecionamos o produto {string} para adicionar ao carrinho com garantia clicando em continuar")
  public void selecionamosOProdutoParaAdicionarAoCarrinhoComGarantiaClicandoEmContinuar(
      String product) {
    productAdded = mainPageMagazine.addProductToOrder(product, true);
  }

  @Then("devemos ter as informações do produto vinculados da sacola para a compra")
  public void devemosTerAsInformacoesDoProdutoVinculadosDaSacolaParaACompra() throws IOException {
    log.info("Product added to wallet");

    if(productAdded){
      efetuaPrint(absolutePath + "/evidences/productAdded.png", driver);
      Assert.assertTrue(productAdded);
    } else {
      Assert.fail();
    }
  }

  @AfterClass
  public void tearsDown(){
    driver.quit();
  }
}
