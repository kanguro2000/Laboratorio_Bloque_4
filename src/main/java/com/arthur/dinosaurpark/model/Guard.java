package com.arthur.dinosaurpark.model;

public class Guard extends Worker {

    private int patrolSkill;

    public Guard(String name, double salary, int patrolSkill) {
        super(name, salary);
        this.patrolSkill = patrolSkill;
    }

    public int getPatrolSkill() {
        return patrolSkill;
    }

    public void setPatrolSkill(int patrolSkill) {
        this.patrolSkill = patrolSkill;
    }

    @Override
    public String toString() {
        return "Guard {" +
                "Patrol Skill= " + patrolSkill +
                "} " + super.toString();
    }
}