package com.tau.scooter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import com.tau.scooter.domain.Person;
import com.tau.scooter.domain.Scooter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@Rollback
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ManagerDBUnitTest {
    @Autowired
    ServiceManager serviceManager;

    @Test
    @DatabaseSetup("/scooterData.xml")
    @ExpectedDatabase(value = "/addScooterData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void
    getScooterCheck() throws ParseException {
        assertEquals(11, serviceManager.getScooters().size());

        Scooter s = new Scooter();
        s.setBrand("Test");
        s.setModel("1a");
        s.setProductionYear(1992);
        s.setColor("darkred");

        serviceManager.addScooter(s);
        assertEquals(12, serviceManager.getScooters().size());
    }

    @Test
    @DatabaseSetup("/scooterData.xml")
    @ExpectedDatabase(value = "/deleteScooterData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void
    deleteScooter() throws ParseException {
        assertEquals(11, serviceManager.getScooters().size());
        Scooter scooter = serviceManager.findScooterById(new Long(110));
        serviceManager.deleteScooter(scooter);
        assertEquals(10, serviceManager.getScooters().size());
    }

    @Test
    @DatabaseSetup("/scooterData.xml")
    public void checkFindColor() throws ParseException {
        assertEquals(4, serviceManager.findScootersByColorLike("red").size());
    }

    @Test
    @DatabaseSetup("/scooterData.xml")
    @ExpectedDatabase(value = "/updatedScooterData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateScooter() throws ParseException {
        assertEquals(11, serviceManager.getScooters().size());
        Scooter scooter = serviceManager.findScooterById(new Long(100));
        scooter.setModel("updated");
        serviceManager.updateScooter(scooter);
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    public void getScooterOwner() throws ParseException {
        Scooter scooter = serviceManager.findScooterById(new Long(107));
        assertEquals(scooter.getPersons().size(), 2);
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    public void addScooterOwner() throws ParseException {
        Person person = serviceManager.findPersonById(new Long(5));
        serviceManager.addScooterPerson(person.getId(), new Long(107));

        assertEquals(serviceManager.findScooterById(new Long(107)).getPersons().size(), 3);
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    public void removeScooterOwner() throws ParseException {
        assertEquals(serviceManager.findScooterById(new Long(107)).getPersons().size(), 2);
        serviceManager.removeScooterPerson(new Long(3), new Long(107));
        assertEquals(serviceManager.findScooterById(new Long(107)).getPersons().size(), 1);
    }

    @Test
    @DatabaseSetup("/personData.xml")
    @ExpectedDatabase(value = "/addPersonData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void
    getPersonCheck() throws ParseException {
        assertEquals(4, serviceManager.getPersons().size());

        Person p = new Person();
        p.setFirstName("Lucjan");

        serviceManager.addPerson(p);
        assertEquals(5, serviceManager.getPersons().size());
    }

    @Test
    @DatabaseSetup("/personData.xml")
    @ExpectedDatabase(value = "/deletePersonData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void
    deletePerson() throws ParseException {
        assertEquals(4, serviceManager.getPersons().size());
        Person person = serviceManager.findPersonById(new Long(3));
        serviceManager.deletePerson(person);
        assertEquals(3, serviceManager.getPersons().size());
    }

    @Test
    @DatabaseSetup("/personData.xml")
    @ExpectedDatabase(value = "/updatePersonData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updatePerson() throws ParseException {
        assertEquals(4, serviceManager.getPersons().size());
        Person person = serviceManager.findPersonById(new Long(3));
        person.setFirstName("updated");
        serviceManager.updatePerson(person);
    }
}
