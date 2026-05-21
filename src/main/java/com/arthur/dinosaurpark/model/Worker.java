package com.arthur.dinosaurpark.model;

import java.util.UUID;

import com.arthur.dinosaurpark.model.enums.WorkerStatus;

public abstract class Worker {

    private UUID id;
    private String name;
    private double salary;
    private WorkerStatus status;

    public Worker(String name, double salary) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
        this.status = WorkerStatus.AVAILABLE;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public WorkerStatus getStatus() {
        return status;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStatus(WorkerStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "Id= " + id +
                ", Nombre= '" + name + '\'' +
                ", Salario= " + salary +
                ", Estatus= " + status +
                '}';
    }
}