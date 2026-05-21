package com.arthur.dinosaurpark.park;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.arthur.dinosaurpark.model.Dinosaur;
import com.arthur.dinosaurpark.model.Tourist;
import com.arthur.dinosaurpark.model.Worker;

public abstract class Zone {

    private UUID id;

    private String name;

    private int capacity;

    private boolean open;

    private List<Dinosaur> dinosaurs;

    private List<Tourist> tourists;

    private List<Worker> workers;

    public Zone(String name, int capacity) {

        this.id = UUID.randomUUID();

        this.name = name;

        this.capacity = capacity;

        this.open = true;

        this.dinosaurs = new ArrayList<>();

        this.tourists = new ArrayList<>();

        this.workers = new ArrayList<>();
    }

    public abstract boolean supportsDinosaur(Dinosaur dinosaur);

    public void addDinosaur(Dinosaur dinosaur) {

        validateZoneOpen();

        validateCapacity();

        validateDinosaurType(dinosaur);

        dinosaurs.add(dinosaur);
    }

    public void removeDinosaur(Dinosaur dinosaur) {

        dinosaurs.remove(dinosaur);
    }

    public void addTourist(Tourist tourist) {

        validateZoneOpen();

        tourists.add(tourist);
    }

    public void removeTourist(Tourist tourist) {

        tourists.remove(tourist);
    }

    public void addWorker(Worker worker) {

        validateZoneOpen();

        workers.add(worker);
    }

    public void removeWorker(Worker worker) {

        workers.remove(worker);
    }

    private void validateZoneOpen() {

        if (!open) {
            throw new IllegalStateException(
                    "La zona esta cerrada");
        }
    }

    private void validateCapacity() {

        if (dinosaurs.size() >= capacity) {
            throw new IllegalStateException(
                    "Capacidad de zona excedida");
        }
    }

    private void validateDinosaurType(
            Dinosaur dinosaur) {

        if (!supportsDinosaur(dinosaur)) {

            throw new IllegalArgumentException(
                    "Tipo de dinosaurio no valido");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Dinosaur> getDinosaurs() {

        return Collections.unmodifiableList(
                dinosaurs);
    }

    public List<Tourist> getTourists() {

        return Collections.unmodifiableList(
                tourists);
    }

    public List<Worker> getWorkers() {

        return Collections.unmodifiableList(
                workers);
    }

    @Override
    public String toString() {

        return "Zone{" +
                "id=" + id +
                ", Nombre='" + name + '\'' +
                ", Capacidad=" + capacity +
                ", Abierta=" + open +
                ", Dinosaurios=" + dinosaurs.size() +
                ", Turistas=" + tourists.size() +
                ", Trabajadores=" + workers.size() +
                '}';
    }
}