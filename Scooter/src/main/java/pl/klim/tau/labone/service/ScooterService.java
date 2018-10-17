package pl.klim.tau.labone.service;

import pl.klim.tau.labone.domain.Scooter;

import java.util.ArrayList;

public interface ScooterService {
    void create(Scooter scooter) throws IllegalArgumentException;
    ArrayList<Scooter> readAll();
    Scooter read(int id);
    void update(Scooter scooter);
    void delete(Scooter scooter);
}
