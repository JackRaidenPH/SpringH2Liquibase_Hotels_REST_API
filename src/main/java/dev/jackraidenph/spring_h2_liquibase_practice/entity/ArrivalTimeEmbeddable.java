package dev.jackraidenph.spring_h2_liquibase_practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class ArrivalTimeEmbeddable {

    @Column(nullable = false)
    @NotBlank(message = "Check-In is required")
    private String checkIn;

    @Column
    private String checkOut;

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
