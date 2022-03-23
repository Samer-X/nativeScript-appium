package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/FeatureFiles/"}
        , glue = {"StepDefinition", "Core", "Pages", "TestCases"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)


public class TestRunner {
    @BeforeClass
    public static void setup() {
    }
}

