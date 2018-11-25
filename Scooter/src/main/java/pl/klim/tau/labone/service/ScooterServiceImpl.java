package pl.klim.tau.labone.service;

import pl.klim.tau.labone.domain.Scooter;
import pl.klim.tau.labtwo.domain.TimeSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScooterServiceImpl implements ScooterService {
    private ArrayList<Scooter> scooters = new ArrayList<Scooter>();

    private boolean disableReadedTimeStamp = false;
    private boolean disableCreatedTimeStamp = false;
    private boolean disableUpdatedTimeStamp = false;

    private TimeSource timeSource;

    public ScooterServiceImpl(TimeSource timeSource) {
        this.timeSource = timeSource;
    }

    public boolean isDisableReadedTimeStamp() {
        return disableReadedTimeStamp;
    }

    public void setDisableReadedTimeStamp(boolean disableReadedTimeStamp) {
        this.disableReadedTimeStamp = disableReadedTimeStamp;
    }

    public boolean isDisableCreatedTimeStamp() {
        return disableCreatedTimeStamp;
    }

    public void setDisableCreatedTimeStamp(boolean disableCreatedTimeStamp) {
        this.disableCreatedTimeStamp = disableCreatedTimeStamp;
    }

    public boolean isDisableUpdatedTimeStamp() {
        return disableUpdatedTimeStamp;
    }

    public void setDisableUpdatedTimeStamp(boolean disableUpdatedTimeStamp) {
        this.disableUpdatedTimeStamp = disableUpdatedTimeStamp;
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
        if (!this.disableCreatedTimeStamp)
            newScooter.setCreated(timeSource.getCurrentTime());
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
            newScooter.setCreated(s.getCreated());
            newScooter.setUpdated(s.getUpdated());

            if (!this.disableReadedTimeStamp)
                newScooter.setReaded(timeSource.getCurrentTime());

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
                newScooter.setCreated(s.getCreated());
                newScooter.setUpdated(s.getUpdated());

                if (!this.disableReadedTimeStamp)
                    newScooter.setReaded(timeSource.getCurrentTime());

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
                s.setReaded(scooter.getReaded());
                s.setCreated(scooter.getCreated());

                if (!this.disableUpdatedTimeStamp)
                    s.setUpdated(timeSource.getCurrentTime());

                return;
            }
        throw new NoSuchElementException();
    }

    public void delete(Scooter scooter) {
        int index = -1;

        for (int i = 0; i < this.scooters.size(); i++) {
            if (this.scooters.get(i).getId() == scooter.getId())
                index = i;
        }

        if (index >= 0) {
            this.scooters.remove(index);
        } else {
            throw new NoSuchElementException();
        }

    }

    public ArrayList<Scooter> findColorByRegex(String pattern) {
        Pattern p = Pattern.compile(pattern);

        ArrayList<Scooter> localsScooters = new ArrayList<Scooter>();
        for (Scooter sc : this.scooters) {
            Matcher m = p.matcher(sc.getColor());
            if (m.matches()) {
                Scooter newScooter = new Scooter(
                        sc.getId(),
                        sc.getModel(),
                        sc.getBrand(),
                        sc.getProductionYear(),
                        sc.getColor()
                );
                newScooter.setCreated(sc.getCreated());
                newScooter.setUpdated(sc.getUpdated());

                if (!this.disableReadedTimeStamp)
                    newScooter.setReaded(timeSource.getCurrentTime());

                localsScooters.add(newScooter);
            }
        }
        return localsScooters;
    }

    public void removeByList(List<Double> ids) {
        ArrayList<Scooter> localsScooters = new ArrayList<Scooter>();

        localsScooters.addAll(this.scooters);

        for (double id : ids) {
            for (Scooter sc : localsScooters) {

                if (sc.getId() == id) {
                    this.scooters.remove(sc);
                }
            }
        }
    }
}
