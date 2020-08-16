package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class PrintScreen {

    public static void efetuaPrint(String diretorio_nome_arq, WebDriver driver) throws IOException {

        File scrnsht1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrnsht1, new File(diretorio_nome_arq));
    }
}
