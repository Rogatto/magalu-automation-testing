package runners;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-report/cucumber.json", "html:target/cucumber-html-report.html"},
        features = {"src/test/java/features/FluxoEndToEndMagalu.feature"},
        glue = {"steps"}
)

public class TestRunnerMagalu {

}
