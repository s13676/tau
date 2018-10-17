package pl.klim.tau.labone.service;

import pl.klim.tau.labone.domain.Scooter;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ScooterServiceImpl implements ScooterService {
    private ArrayList<Scooter> scooters = new ArrayList<Scooter>();

    public void create(Scooter scooter) throws IllegalArgumentException {
        for (Scooter s: scooters)
            if (s.getId() == (scooter.getId()))
                throw new IllegalArgumentException();
        this.scooters.add(scooter);
    }

    public ArrayList<Scooter> readAll() {
        return null;
    }

    public Scooter read(int id) throws NoSuchElementException {
        for (Scooter s: scooters)
            if (s.getId() == id) {
                Scooter newScooter = new Scooter(
                        s.getId(),
                        s.getModel(),
                        s.getBrand(),
                        s.getProductionYear(),
                        s.getColor()
                );
                return newScooter;
            }
         throw new NoSuchElementException();
    }

    public void update(Scooter scooter) {

    }

    public void delete(Scooter scooter) {

    }
}
