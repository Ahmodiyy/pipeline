package acceptance;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
/** Acceptance Test */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature", glue = "acceptance")
public class AcceptanceTest { }
