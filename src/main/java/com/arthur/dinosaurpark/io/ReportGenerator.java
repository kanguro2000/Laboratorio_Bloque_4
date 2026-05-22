package com.arthur.dinosaurpark.io;

import java.io.FileWriter;
import java.io.IOException;

import com.arthur.dinosaurpark.park.Park;
import com.arthur.dinosaurpark.simulation.SimulationEvent;

import java.util.List;

public class ReportGenerator {

    public void generateSimulationReport(Park park,List<SimulationEvent> events) {

        try (FileWriter writer = new FileWriter("simulation-report.txt")) {

            writer.write("=== DINOSAUR PARK REPORT ===\n\n");

            writer.write(park.toString() + "\n\n");

            writer.write("=== EVENTOS ===\n");

            for (SimulationEvent event : events) {
                writer.write(event.toString() + "\n");
            }

            writer.write("\nReporte generado correctamente.");

            System.out.println("\nReporte generado: simulation-report.txt");

        } catch (IOException e) {

            System.out.println("Error generando reporte: "+ e.getMessage());
        }
    }
}