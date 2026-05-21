package com.arthur.dinosaurpark.model;

public class CarnivoreDinosaur extends Dinosaur {

    private double aggressionLevel;

    public CarnivoreDinosaur(String name, int age, double weight, double aggressionLevel) {
        super(name, age, weight);
        this.aggressionLevel = aggressionLevel;
    }

    @Override
    public double calculateFoodConsumption() {
        return getWeight() * 0.08;
    }

    public double getAggressionLevel() {
        return aggressionLevel;
    }

    public void setAggressionLevel(double aggressionLevel) {
        this.aggressionLevel = aggressionLevel;
    }

    @Override
    public String toString() {
        return "CarnivoreDinosaur{" +
                "Nivel de Agresividad= " + aggressionLevel +
                "} " + super.toString();
    }
}