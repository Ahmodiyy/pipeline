package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


/** Steps definitions for calculator.feature */
public class StepDefinitions {
    private String server = System.getProperty("url");
    //private String server = "http://localhost:7070";
    private RestTemplate restTemplate = new RestTemplate();
    private String a;
    private String b;
    private String result;
    @Given("^I have two numbers: (.*) and (.*)$")
    public void i_have_two_numbers(String a, String b) throws
            Throwable {
        this.a = a;
        this.b = b;
    }
    @When("^the calculator sums them$")
    public void the_calculator_sums_them() throws Throwable {
        String url = String.format("%s/sum?a=%s&b=%s", server,
                a, b);
        result = restTemplate.getForObject(url, String.class);
    }
    @Then("^I receive (.*) as a result$")
    public void i_receive_as_a_result(String expectedResult)
            throws Throwable {
        assertEquals(expectedResult, result);
    }
}