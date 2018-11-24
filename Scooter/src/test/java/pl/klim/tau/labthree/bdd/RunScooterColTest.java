package pl.klim.tau.labthree.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        features = "classpath:scooter/scooter.feature"
)
public class RunScooterColTest {
}
