package com.arthur.dinosaurpark.model;

import java.util.UUID;

import com.arthur.dinosaurpark.model.enums.TouristStatus;

public class Tourist {

    private UUID id;
    private String name;
    private int age;
    private double money;
    private double satisfaction;
    private TouristStatus status;

    public Tourist(String name, int age, double money) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.money = money;
        this.satisfaction = 100.0;
        this.status = TouristStatus.ACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMoney() {
        return money;
    }

    public double getSatisfaction() {
        return satisfaction;
    }

    public TouristStatus getStatus() {
        return status;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setSatisfaction(double satisfaction) {
        this.satisfaction = satisfaction;
    }

    public void setStatus(TouristStatus status) {
        this.status = status;
    }

    public void spendMoney(double amount) {

        if (amount > money) {
            throw new IllegalArgumentException(
                    "Dinero insuficiente");
        }

        this.money -= amount;
    }

    @Override
    public String toString() {
        return "Tourist {" +
                "id= " + id +
                ", Nombre= '" + name + '\'' +
                ", Edad= " + age +
                ", Dinero= " + money +
                ", Satisfaccion= " + satisfaction +
                ", Estatus= " + status +
                '}';
    }
}