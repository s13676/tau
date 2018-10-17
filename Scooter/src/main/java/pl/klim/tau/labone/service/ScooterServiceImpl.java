package pl.klim.tau.labone.service;

import pl.klim.tau.labone.domain.Scooter;

import java.util.ArrayList;

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

    public Scooter read(int id) {
        return null;
    }

    public void update(Scooter scooter) {

    }

    public void delete(Scooter scooter) {

    }
}
