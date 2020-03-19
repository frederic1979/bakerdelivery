package co.simplon.bakerdelivery.dto;

import co.simplon.bakerdelivery.model.Restaurant;

import javax.persistence.*;
import java.time.LocalDate;

public class CommandDto {


    private Long id;

    private LocalDate date;

    private Long quantity;

    private Long restaurantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
