package com.arthur.dinosaurpark.model;

public class HerbivoreDinosaur extends Dinosaur {

    private boolean grazing;

    public HerbivoreDinosaur(String name, int age, double weight) {
        super(name, age, weight);
        this.grazing = true;
    }

    @Override
    public double calculateFoodConsumption() {
        return getWeight() * 0.05;
    }

    public boolean isGrazing() {
        return grazing;
    }

    public void setGrazing(boolean grazing) {
        this.grazing = grazing;
    }

    @Override
    public String toString() {
        return "HerbivoreDinosaur {" +
                "Pastoreo= " + grazing +
                "} " + super.toString();
    }
}