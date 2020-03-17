package co.simplon.bakerdelivery.model;

import lombok.Value;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Value
public class Command {

    @Id
    @SequenceGenerator(name = "command_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "command_seq_id")
    private Long id;

    @Column (nullable = false)
    private LocalDate date;

    @Column
    private Long quantity;

    @ManyToOne
    private Restaurant restaurant;


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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
