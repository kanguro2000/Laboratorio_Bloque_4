package com.arthur.dinosaurpark;

import com.arthur.dinosaurpark.config.ParkConfig;

public class Main {

    public static void main(String[] args) {

        System.out.println("Dinosaur Park Simulation the Arthur !!!");

        ParkConfig config = ParkConfig.getInstance();

        System.out.println("getSeed: ");
        System.out.println(config.getSeed());
        System.out.println("getTotalSteps: ");
        System.out.println(config.getTotalSteps());
    }

}