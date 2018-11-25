package pl.klim.tau.labthree.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.klim.tau.labone.domain.Scooter;
import pl.klim.tau.labone.service.ScooterService;
import pl.klim.tau.labone.service.ScooterServiceImpl;
import pl.klim.tau.labtwo.domain.TimeSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    }

    @Given("^there is a list of objects$")
    public void there_is_a_object(List<Scooter> scooters) throws Throwable {
        for (Scooter sc: scooters)
            scooterService.create(sc);
    }

    @When("^list is filtered by color regex (.+)$")
    public void list_filtered_by_color(String color) throws Throwable {
        result = scooterService.findColorByRegex(color);
    }
    @Then("^the result should be (\\d+)$")
    public void result(int size) throws Throwable {
        assertEquals(result.size(), size);
    }

    @And("^removing")
    public void iRemoveAAndB(List<Double> arg1) throws Throwable {
        scooterService.removeByList(arg1);
        result = scooterService.readAll();
    }

    @But("^the result should not be (\\d+)$")
    public void theResultShouldNotBeWrong(int x) throws Throwable {
        assertNotEquals(result.size(), x);
    }
}
