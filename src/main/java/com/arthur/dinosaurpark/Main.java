package com.arthur.dinosaurpark;

import com.arthur.dinosaurpark.model.CarnivoreDinosaur;
import com.arthur.dinosaurpark.model.Guard;
import com.arthur.dinosaurpark.model.HerbivoreDinosaur;
import com.arthur.dinosaurpark.model.Technician;
import com.arthur.dinosaurpark.model.Ticket;
import com.arthur.dinosaurpark.model.Tourist;

import com.arthur.dinosaurpark.park.ArrivalZone;
import com.arthur.dinosaurpark.park.BathroomZone;
import com.arthur.dinosaurpark.park.CarnivoreZone;
import com.arthur.dinosaurpark.park.CentralHub;
import com.arthur.dinosaurpark.park.HerbivoreZone;
import com.arthur.dinosaurpark.park.ObservationEnclosure;
import com.arthur.dinosaurpark.park.Park;
import com.arthur.dinosaurpark.park.PowerPlant;
import com.arthur.dinosaurpark.park.VisitorZone;
import com.arthur.dinosaurpark.simulation.SimulationEngine;
import com.arthur.dinosaurpark.simulation.StatisticsManager;
import com.arthur.dinosaurpark.model.enums.TicketType;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== DINOSAUR PARK SIMULATION ===");

        Park park = new Park("Jurassic Arthur Park");

        CarnivoreZone carnivoreZone = new CarnivoreZone("Carnivore Area", 10);
        HerbivoreZone herbivoreZone = new HerbivoreZone("Herbivore Valley", 15);
        VisitorZone visitorZone = new VisitorZone("Main Visitor Center", 50);
        ArrivalZone arrivalZone = new ArrivalZone("Arrival Area", 100);
        CentralHub centralHub = new CentralHub("Central Hub", 200);
        BathroomZone bathroomZone = new BathroomZone("Bathrooms", 30, 15);
        PowerPlant powerPlant = new PowerPlant("Main Power Plant", 10, 100);
        ObservationEnclosure observationZone = new ObservationEnclosure("Observation Area", 20, 90);

        park.addZone(carnivoreZone);
        park.addZone(herbivoreZone);
        park.addZone(visitorZone);

        park.addZone(arrivalZone);
        park.addZone(centralHub);
        park.addZone(bathroomZone);
        park.addZone(powerPlant);
        park.addZone(observationZone);

        Tourist tourist1 = new Tourist("Arthur 1", 34, 5000);
        Tourist tourist2 = new Tourist("Arthur 2", 45, 6000);
        Tourist tourist3 = new Tourist("Arthur 3", 10, 7000);

        Ticket ticket1 = new Ticket(tourist1, 1500, TicketType.VIP);
        Ticket ticket2 = new Ticket(tourist2, 1100, TicketType.GENERAL);
        Ticket ticket3 = new Ticket(tourist3, 800, TicketType.CHILD);

        arrivalZone.sellTicket(ticket1);
        arrivalZone.sellTicket(ticket2);
        arrivalZone.sellTicket(ticket3);

        CarnivoreDinosaur trex = new CarnivoreDinosaur("T-Rex", 15, 8000, 99);
        CarnivoreDinosaur trex2 = new CarnivoreDinosaur("T-Rex2", 16, 9000, 99);

        HerbivoreDinosaur triceratops = new HerbivoreDinosaur("Triceratops", 12, 3000);
        HerbivoreDinosaur triceratops2 = new HerbivoreDinosaur("Triceratops2", 13, 3500);

        Guard guard = new Guard("Juan", 1500, 80);
        Guard guard2 = new Guard("Pedro", 1600, 85);

        Technician technician = new Technician("Miguel", 1800, 90);
        Technician technician2 = new Technician("Santiago", 1800, 90);

        park.addTourist(tourist1);
        park.addTourist(tourist2);
        park.addTourist(tourist3);

        park.addDinosaur(trex);
        park.addDinosaur(trex2);
        park.addDinosaur(triceratops);
        park.addDinosaur(triceratops2);
        park.addWorker(guard);
        park.addWorker(technician);
        park.addWorker(guard2);
        park.addWorker(technician2);

        park.addDinosaurToZone(trex, carnivoreZone);
        park.addDinosaurToZone(trex2, observationZone);
        park.addDinosaurToZone(triceratops, herbivoreZone);
        park.addDinosaurToZone(triceratops2, herbivoreZone);

        park.addTouristToZone(tourist1, visitorZone);
        park.addTouristToZone(tourist2, centralHub);
        park.addTouristToZone(tourist3, centralHub);

        park.addWorkerToZone(guard, carnivoreZone);
        park.addWorkerToZone(guard2, carnivoreZone);
        park.addWorkerToZone(technician, powerPlant);

        System.out.println("\n=== INFORMACIÓN DEL PARQUE ===");
        System.out.println(park);

        System.out.println("\n=== ZONAS ===");
        System.out.println(carnivoreZone);
        System.out.println(herbivoreZone);
        System.out.println(visitorZone);
        System.out.println(arrivalZone);
        System.out.println(centralHub);
        System.out.println(bathroomZone);
        System.out.println(powerPlant);
        System.out.println(observationZone);

        System.out.println("\n=== TICKETS ===");
        System.out.println(ticket1);
        System.out.println(ticket2);
        System.out.println(ticket3);

        boolean access1 = arrivalZone.validateTicket(ticket1);

        boolean access2 = arrivalZone.validateTicket(ticket2);

        boolean access3 = arrivalZone.validateTicket(ticket3);

        System.out.println("\n=== VALIDACIÓN DE ACCESO ===");
        System.out.println("Ticket Arthur 1 válido: " + access1);
        System.out.println("Ticket Arthur 2 válido: " + access2);
        System.out.println("Ticket Arthur 3 válido: " + access3);

        System.out.println("\n=== DINERO RESTANTE ===");
        System.out.println(tourist1.getName() + " dinero restante: " + tourist1.getMoney());
        System.out.println(tourist2.getName() + " dinero restante: " + tourist2.getMoney());
        System.out.println(tourist3.getName() + " dinero restante: " + tourist3.getMoney());

        SimulationEngine engine = new SimulationEngine(park, 20);
        engine.startSimulation();
        StatisticsManager statistics = new StatisticsManager(park);
        statistics.printStatistics();
    }
}