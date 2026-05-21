package com.arthur.dinosaurpark.model;

import java.util.UUID;

import com.arthur.dinosaurpark.model.enums.DinosaurStatus;

public abstract class Dinosaur {

    private UUID id;
    private String name;
    private int age;
    private double weight;
    private double health;
    private double energy;
    private DinosaurStatus status;

    public Dinosaur(String name, int age, double weight) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.health = 100.0;
        this.energy = 100.0;
        this.status = DinosaurStatus.CALM;
    }

    public abstract double calculateFoodConsumption();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHealth() {
        return health;
    }

    public double getEnergy() {
        return energy;
    }

    public DinosaurStatus getStatus() {
        return status;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setStatus(DinosaurStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Dinosaur {" +
                "Id= " + id +
                ", Nombre= '" + name + '\'' +
                ", Edad= " + age +
                ", Peso= " + weight +
                ", Salud= " + health +
                ", Energia= " + energy +
                ", Estatus= " + status +
                '}';
    }
}