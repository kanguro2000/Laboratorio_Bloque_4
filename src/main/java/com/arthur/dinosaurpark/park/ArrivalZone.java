package com.arthur.dinosaurpark.park;

import java.util.ArrayList;
import java.util.List;

import com.arthur.dinosaurpark.model.Dinosaur;
import com.arthur.dinosaurpark.model.Ticket;

public class ArrivalZone extends Zone {

    private List<Ticket> soldTickets;

    private double totalRevenue;

    public ArrivalZone(String name, int capacity) {

        super(name, capacity);
        this.soldTickets = new ArrayList<>();
        this.totalRevenue = 0;
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {
        return false;
    }

    public void sellTicket(Ticket ticket) {
        validateNullTicket(ticket);
        validateDuplicateTicket(ticket);
        validateTouristMoney(ticket);
        ticket.getTourist().spendMoney(ticket.getPrice());
        soldTickets.add(ticket);
        totalRevenue += ticket.getPrice();
    }

    public boolean validateTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }

        if (!ticket.isValid()) {
            return false;
        }

        ticket.invalidate();
        return true;
    }

    private void validateTouristMoney(Ticket ticket) {
        if (ticket.getTourist().getMoney()< ticket.getPrice()) {
            throw new IllegalArgumentException("Dinero insuficiente");
        }
    }

    private void validateDuplicateTicket(Ticket ticket) {

        boolean exists = soldTickets.stream().anyMatch(t ->t.getTourist().equals(ticket.getTourist())&& t.isValid());
        if (exists) {
            throw new IllegalArgumentException("El turista ya tiene ticket activo");
        }
    }

    private void validateNullTicket(Ticket ticket) {

        if (ticket == null) {
            throw new IllegalArgumentException("El ticket no puede ser null");
        }
    }

    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public String toString() {

        return "ArrivalZone{" +
                "Nombre='" + getName() + '\'' +
                ", Tickets vendidos=" + soldTickets.size() +
                ", Ingresos=" + totalRevenue +
                '}';
    }
}