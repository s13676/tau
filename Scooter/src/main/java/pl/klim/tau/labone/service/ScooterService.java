package pl.klim.tau.labone.service;

import pl.klim.tau.labone.domain.Scooter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public interface ScooterService {
    void create(Scooter scooter) throws IllegalArgumentException;
    ArrayList<Scooter> readAll();
    Scooter read(int id) throws NoSuchElementException;
    void update(Scooter scooter);
    void delete(Scooter scooter);

    public boolean isDisableReadedTimeStamp();
    public void setDisableReadedTimeStamp(boolean disableReadedTimeStamp);
    public boolean isDisableCreatedTimeStamp();
    public void setDisableCreatedTimeStamp(boolean disableCreatedTimeStamp);
    public boolean isDisableUpdatedTimeStamp();
    public void setDisableUpdatedTimeStamp(boolean disableUpdatedTimeStamp);
    ArrayList<Scooter> findColorByRegex(String pattern);
    public void removeByList(List<Double> ids);
}
