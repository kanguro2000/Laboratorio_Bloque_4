package com.arthur.dinosaurpark.model;

public class Technician extends Worker {

    private int repairSkill;

    public Technician(String name, double salary, int repairSkill) {
        super(name, salary);
        this.repairSkill = repairSkill;
    }

    public int getRepairSkill() {
        return repairSkill;
    }

    public void setRepairSkill(int repairSkill) {
        this.repairSkill = repairSkill;
    }

    @Override
    public String toString() {
        return "Technician {" +
                "Habilidad de reparacion= " + repairSkill +
                "} " + super.toString();
    }
}