package com.arthur.dinosaurpark.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.arthur.dinosaurpark.model.enums.TicketType;

public class Ticket {

    private UUID id;

    private Tourist tourist;

    private double price;

    private boolean valid;

    private LocalDateTime purchaseDate;

    private TicketType type;

    public Ticket(
            Tourist tourist,
            double price,
            TicketType type) {

        this.id = UUID.randomUUID();

        this.tourist = tourist;

        this.price = price;

        this.type = type;

        this.valid = true;

        this.purchaseDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public double getPrice() {
        return price;
    }

    public boolean isValid() {
        return valid;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public TicketType getType() {
        return type;
    }

    public void invalidate() {
        this.valid = false;
    }

    @Override
    public String toString() {

        return "Ticket{" +
                "id=" + id +
                ", Turista= " + tourist.getName() +
                ", Tipo= " + type +
                ", Precio= " + price +
                ", Valido= " + valid +
                ", FechaCompra= " + purchaseDate +
                '}';
    }
}