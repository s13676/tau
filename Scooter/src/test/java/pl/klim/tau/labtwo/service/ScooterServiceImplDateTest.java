package pl.klim.tau.labtwo.service;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.klim.tau.labone.domain.Scooter;
import pl.klim.tau.labtwo.domain.TimeSource;
import pl.klim.tau.labone.service.ScooterService;
import pl.klim.tau.labone.service.ScooterServiceImpl;

import java.util.Date;

import static org.mockito.Mockito.when;

public class ScooterServiceImplDateTest {
    ScooterService scooterService;
    Date testDate;

    @Mock
    TimeSource timeSource;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        testDate = new Date(2018, 11, 5, 17, 15);
        when(timeSource.getCurrentTime()).thenReturn(testDate);
        scooterService = new ScooterServiceImpl(this.timeSource);
    }

    @Test
    public void testReadedDate() {
        Scooter scooter = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);
        Scooter returnedScooter = scooterService.read(1);
        Assert.assertTrue(returnedScooter.getReaded().equals(this.testDate));
    }

}
