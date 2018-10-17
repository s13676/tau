package pl.klim.tau.labone.service;

import org.junit.*;
import pl.klim.tau.labone.domain.Scooter;

public class ScooterServiceImplTest {
    ScooterService scooterService;

    @Before
    public void initialize() {
        scooterService = new ScooterServiceImpl();
    }

    @Test
    public void testCreate() {
        Scooter scooter = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateObjectAlreadyExists() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        Scooter scooterTwo = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");

        scooterService.create(scooterOne);
        scooterService.create(scooterTwo);
    }
}
