package com.arthur.dinosaurpark.simulation;

import java.time.LocalDateTime;
import java.util.UUID;

import com.arthur.dinosaurpark.model.enums.EventType;

public class SimulationEvent {

    private UUID id;

    private EventType type;

    private String description;

    private LocalDateTime timestamp;

    public SimulationEvent(
            EventType type,
            String description) {

        this.id = UUID.randomUUID();

        this.type = type;

        this.description = description;

        this.timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public EventType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {

        return "SimulationEvent{" +
                "Tipo=" + type +
                ", Descripción='" + description + '\'' +
                ", Fecha=" + timestamp +
                '}';
    }
}