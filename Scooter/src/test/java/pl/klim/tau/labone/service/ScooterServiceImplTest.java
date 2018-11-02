package pl.klim.tau.labone.service;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.klim.tau.labone.domain.Scooter;
import pl.klim.tau.labtwo.domain.TimeSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

public class ScooterServiceImplTest {
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
    public void testReadObjectDoesNotExists() {
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

    @Test
    public void testReadAllEmpty() {
        ArrayList<Scooter> scooters = scooterService.readAll();
        Assert.assertEquals(scooters.size(), 0);
    }

    @Test
    public void testReadAll() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");

        scooterService.create(scooterOne);

        ArrayList<Scooter> scooters = scooterService.readAll();

        Assert.assertEquals(scooters.size(), 1);
    }

    @Test
    public void testUpdate() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooterOne);

        Scooter returnedScooter = scooterService.read(1);

        returnedScooter.setColor("Red");
        returnedScooter.setProductionYear(1992);

        scooterService.update(returnedScooter);

        Scooter returnedEditedScooter = scooterService.read(1);

        Assert.assertEquals(returnedScooter.getId(), returnedEditedScooter.getId());

        Assert.assertNotEquals(scooterOne.getColor(), returnedEditedScooter.getColor());
        Assert.assertNotEquals(scooterOne.getProductionYear(), returnedEditedScooter.getProductionYear());

        Assert.assertEquals(returnedScooter.getColor(), returnedEditedScooter.getColor());
        Assert.assertEquals(returnedScooter.getProductionYear(), returnedEditedScooter.getProductionYear());
    }

    @Test(expected = NoSuchElementException.class)
    public void testObjectNotFound() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.update(scooterOne);
    }

    @Test
    public void testDelete() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooterOne);
        Assert.assertEquals(scooterService.readAll().size(), 1);
        scooterService.delete(scooterOne);
        Assert.assertEquals(scooterService.readAll().size(), 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void  testNotExistObject() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.delete(scooterOne);
    }

    @Test
    public void testReadedDate() {
        Scooter scooter = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);
        Scooter returnedScooter = scooterService.read(1);
        Assert.assertTrue(returnedScooter.getReaded().equals(this.testDate));
    }

    @Test
    public void testReadedDateReadAll() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooterOne);
        ArrayList<Scooter> scooters = scooterService.readAll();

        Assert.assertTrue(scooters.get(0).getReaded().equals(this.testDate));
    }

    @Test
    public void testAddCreatedDate() {
        Scooter scooter = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);
        Scooter returnedScooter = scooterService.read(1);
        Assert.assertTrue(returnedScooter.getCreated().equals(this.testDate));
    }

    @Test
    public void testUpdatedDate() {
        Scooter scooterOne = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooterOne);

        Scooter returnedScooter = scooterService.read(1);

        returnedScooter.setColor("Red");
        returnedScooter.setProductionYear(1992);

        scooterService.update(returnedScooter);

        Scooter returnedEditedScooter = scooterService.read(1);

        Assert.assertTrue(returnedEditedScooter.getUpdated().equals(this.testDate));
    }

    @Test
    public void testDisableReadedDate() {
        scooterService.setDisableReadedTimeStamp(true);
        Scooter scooter = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);
        Scooter returnedScooter = scooterService.read(1);
        Assert.assertNull(returnedScooter.getReaded());
    }

    @Test
    public void testDisableAddCreatedDate() {
        scooterService.setDisableCreatedTimeStamp(true);
        Scooter scooter = new Scooter(1,"Yamaha", "Aerox", 2005, "Black");
        scooterService.create(scooter);
        Scooter returnedScooter = scooterService.read(1);
        Assert.assertNull(returnedScooter.getCreated());
    }
}
