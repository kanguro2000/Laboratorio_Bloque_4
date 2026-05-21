package com.arthur.dinosaurpark.park;

import java.util.ArrayList;
import java.util.List;

import com.arthur.dinosaurpark.model.Dinosaur;
import com.arthur.dinosaurpark.model.Tourist;
import com.arthur.dinosaurpark.model.Worker;

public class Park {

    private String name;

    private List<Zone> zones;
    private List<Tourist> tourists;
    private List<Dinosaur> dinosaurs;
    private List<Worker> workers;

    public Park(String name) {

        this.name = name;

        this.zones = new ArrayList<>();
        this.tourists = new ArrayList<>();
        this.dinosaurs = new ArrayList<>();
        this.workers = new ArrayList<>();
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    public void addTourist(Tourist tourist) {
        tourists.add(tourist);
    }

    public void addDinosaur(Dinosaur dinosaur) {
        dinosaurs.add(dinosaur);
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public void addDinosaurToZone(Dinosaur dinosaur, Zone zone) {

        if (!zones.contains(zone)) {
            throw new IllegalArgumentException("Zona does not belong to park");
        }

        zone.addDinosaur(dinosaur);
    }

    public void addTouristToZone(Tourist tourist, Zone zone) {

        if (!zones.contains(zone)) {
            throw new IllegalArgumentException("Zona does not belong to park");
        }

        zone.addTourist(tourist);
    }

    public void addWorkerToZone(Worker worker, Zone zone) {

        if (!zones.contains(zone)) {
            throw new IllegalArgumentException("Zona does not belong to park");
        }

        zone.addWorker(worker);
    }

    public String getName() {
        return name;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    @Override
    public String toString() {

        return "Park{" +
                "Nombre= '" + name + '\'' +
                ", Zonas= " + zones.size() +
                ", Turistas= " + tourists.size() +
                ", Dinosaurios= " + dinosaurs.size() +
                ", Trabajadores=" + workers.size() +
                '}';
    }
}