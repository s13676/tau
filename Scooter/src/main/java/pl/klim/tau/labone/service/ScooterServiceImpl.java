package pl.klim.tau.labone.service;

import pl.klim.tau.labone.domain.Scooter;
import pl.klim.tau.labtwo.domain.TimeSource;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ScooterServiceImpl implements ScooterService {
    private ArrayList<Scooter> scooters = new ArrayList<Scooter>();

    private TimeSource timeSource;

    public ScooterServiceImpl() {

    }

    public ScooterServiceImpl(TimeSource timeSource) {

    }

    public void create(Scooter scooter) throws IllegalArgumentException {
        for (Scooter s : scooters)
            if (s.getId() == (scooter.getId()))
                throw new IllegalArgumentException();

        Scooter newScooter = new Scooter(
                scooter.getId(),
                scooter.getModel(),
                scooter.getBrand(),
                scooter.getProductionYear(),
                scooter.getColor()
        );
        this.scooters.add(newScooter);
    }

    public ArrayList<Scooter> readAll() {
        ArrayList<Scooter> localsSooters = new ArrayList<Scooter>();
        for (Scooter s : this.scooters) {
            Scooter newScooter = new Scooter(
                    s.getId(),
                    s.getModel(),
                    s.getBrand(),
                    s.getProductionYear(),
                    s.getColor()
            );
            localsSooters.add(newScooter);
        }
        return localsSooters;
    }

    public Scooter read(int id) throws NoSuchElementException {
        for (Scooter s : scooters)
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
        for (Scooter s : scooters)
            if (s.getId() == (scooter.getId())) {
                s.setProductionYear(scooter.getProductionYear());
                s.setColor(scooter.getColor());
                s.setBrand(scooter.getBrand());
                s.setModel(scooter.getModel());

                return;
            }
        throw new NoSuchElementException();
    }

    public void delete(Scooter scooter) {
        int index = -1;

        for (int i=0;i<this.scooters.size();i++) {
            if (this.scooters.get(i).getId() == scooter.getId())
                index = i;
        }

        if (index >= 0) {
            this.scooters.remove(index);
        } else {
            throw new NoSuchElementException();
        }

    }

    public TimeSource getTimeSource() {
        return timeSource;
    }

    public void setTimeSource(TimeSource timeSource) {
        this.timeSource = timeSource;
    }
}
