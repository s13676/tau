package pl.klim.tau.labone.service;

import org.junit.*;
import pl.klim.tau.labone.domain.Scooter;

import java.util.NoSuchElementException;

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

    @Test
    public void testRead() {
        int id = 1;

        Scooter scooter = new Scooter(id,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);

        Scooter returnedScooter = scooterService.read(id);

        Assert.assertEquals(scooter.getId(), returnedScooter.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void testReadObjectDoesNotExistsEmptyService() {
        scooterService.read(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testReadObjecDoesNotExists() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        Scooter scooterTwo = new Scooter(2,"Yamaha", "Aerox", 2005, "Black");
        Scooter scooterThree = new Scooter(3,"Yamaha", "Aerox", 2005, "Black");
        Scooter scooterFour = new Scooter(4,"Yamaha", "Aerox", 2005, "Black");

        scooterService.create(scooterOne);
        scooterService.create(scooterTwo);
        scooterService.create(scooterThree);
        scooterService.create(scooterFour);

        scooterService.read(5);
    }
}
