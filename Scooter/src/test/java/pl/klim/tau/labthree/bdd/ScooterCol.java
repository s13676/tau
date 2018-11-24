package pl.klim.tau.labthree.bdd;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.klim.tau.labone.domain.Scooter;
import pl.klim.tau.labone.service.ScooterService;
import pl.klim.tau.labone.service.ScooterServiceImpl;
import pl.klim.tau.labtwo.domain.TimeSource;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ScooterCol {
    ScooterService scooterService;
    Date testDate;
    ArrayList<Scooter> result;


    @Mock
    TimeSource timeSource;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testDate = new Date(2018, 11, 5, 17, 15);
        when(timeSource.getCurrentTime()).thenReturn(testDate);
        scooterService = new ScooterServiceImpl(this.timeSource);

        scooterService.create(new Scooter(1,"Yamaha", "Aerox", 2005, "Black"));
        scooterService.create(new Scooter(2,"Yamaha", "Aerox", 2005, "Black"));
    }

    @Given("^there is a list of objects$")
    public void there_is_a_object() throws Throwable {
        assertNotNull(scooterService);
    }

    @When("^list is filtered by color regex (\\w+)$")
    public void list_filtered_by_color(String color) throws Throwable {
        result = scooterService.findColorByRegex(color);
    }
    @Then("^the result should be (\\w+)$")
    public void result(int size) throws Throwable {
        assertEquals(result.size(), size);
    }
}
