package com.tau.scooter.service;

import java.util.LinkedList;
import java.util.List;

import com.tau.scooter.domain.Person;
import com.tau.scooter.domain.Scooter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ServiceManagerImpl implements ServiceManager {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addScooter(Scooter scooter) {
        scooter.setId(null);
        sessionFactory.getCurrentSession().persist(scooter);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public List<Scooter> getScooters() {
        return sessionFactory.getCurrentSession().getNamedQuery("scooter.all").list();
    }

    @Override
    public Scooter findScooterById(Long id) {
        return sessionFactory.getCurrentSession().get(Scooter.class, id);
    }

    @Override
    public List<Scooter> findScootersByColorLike(String color) {
        return sessionFactory.getCurrentSession().createQuery("Select s from Scooter s where s.color like :color")
                .setParameter("color", "%" + color + "%").list();
    }

    @Override
    public void deleteScooter(Scooter scooter) {
        sessionFactory.getCurrentSession().delete(scooter);
    }

    @Override
    public void updateScooter(Scooter scooter) {
        sessionFactory.getCurrentSession().update(scooter);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addScooterPerson(Long personId, Long scooterId) {
        Person person = sessionFactory.getCurrentSession().get(Person.class, personId);
		Scooter scooter = sessionFactory.getCurrentSession().get(Scooter.class, scooterId);


		if (scooter.getPersons() == null) {
			scooter.setPersons(new LinkedList<Person>());
		}
		scooter.getPersons().add(person);
    }

    @Override
    public void removeScooterPerson(Long personId, Long scooterId) {
        Person person = sessionFactory.getCurrentSession().get(Person.class, personId);
        Scooter scooter = sessionFactory.getCurrentSession().get(Scooter.class, scooterId);

        Person toRemove = null;

        for (Person p : scooter.getPersons())
			if (p.getId().compareTo(person.getId()) == 0) {
				toRemove = p;
				break;
			}

		if (toRemove != null)
			scooter.getPersons().remove(toRemove);
    }

    @Override
    public void addPerson(Person person) {
        person.setId(null);
        sessionFactory.getCurrentSession().persist(person);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public List<Person> getPersons() {
        return sessionFactory.getCurrentSession().getNamedQuery("person.all").list();
    }

    @Override
    public Person findPersonById(Long id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Override
    public void deletePerson(Person Person) {
        sessionFactory.getCurrentSession().delete(Person);
    }

    @Override
    public void updatePerson(Person Person) {
        sessionFactory.getCurrentSession().update(Person);
        sessionFactory.getCurrentSession().flush();
    }
//
//	@Override
//	public void addClient(Person person) {
//		person.setId(null);
//		sessionFactory.getCurrentSession().persist(person);
//		sessionFactory.getCurrentSession().flush();
//	}
//    @Override
//    public void updateClient(Person person) {
//        sessionFactory.getCurrentSession().update(person);
//    }
//
//	@Override
//	public void deleteClient(Person person) {
//		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
//				person.getId());
//
//		for (Scooter car : person.getCars()) {
//			car.setSold(false);
//			sessionFactory.getCurrentSession().update(car);
//		}
//		sessionFactory.getCurrentSession().delete(person);
//	}
//
//	@Override
//	public List<Scooter> getOwnedCars(Person person) {
//		person = sessionFactory.getCurrentSession().get(Person.class,
//				person.getId());
//		List<Scooter> cars = new ArrayList<Scooter>(person.getCars());
//		return cars;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Person> getAllClients() {
//		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
//				.list();
//	}
//
//	@Override
//	public Person findClientByPin(String pin) {
//		return (Person) sessionFactory
//			.getCurrentSession()
//			.getNamedQuery("person.byPin")
//			.setParameter("pin", pin)
//			.uniqueResult();
//	}
//
//
//	@Override
//	public Long addNewCar(Scooter car) {
//		car.setId(null);
//		return (Long) sessionFactory.getCurrentSession().save(car);
//	}
//
//	@Override
//	public void sellCar(Long personId, Long carId) {
//		Person person = (Person) sessionFactory.getCurrentSession().get(
//				Person.class, personId);
//		Scooter car = (Scooter) sessionFactory.getCurrentSession()
//				.get(Scooter.class, carId);
//		car.setSold(true);
//		if (person.getCars() == null) {
//			person.setCars(new LinkedList<Scooter>());
//		}
//		person.getCars().add(car);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Scooter> getAvailableCars() {
//		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
//				.list();
//	}
//	@Override
//	public void disposeCar(Person person, Scooter car) {
//		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
//				person.getId());
//		car = (Scooter) sessionFactory.getCurrentSession().get(Scooter.class,
//				car.getId());
//
//		Scooter toRemove = null;
//		// lazy loading here (person.getCars)
//		for (Scooter aCar : person.getCars())
//			if (aCar.getId().compareTo(car.getId()) == 0) {
//				toRemove = aCar;
//				break;
//			}
//
//		if (toRemove != null)
//			person.getCars().remove(toRemove);
//
//		car.setSold(false);
//	}
//
//	@Override
//	public Scooter findCarById(Long id) {
//		return (Scooter) sessionFactory.getCurrentSession().get(Scooter.class, id);
//	}


}
