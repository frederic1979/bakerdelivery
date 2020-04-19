package co.simplon.bakerdelivery.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Matrix {
    @Id
    @SequenceGenerator(name = "matrix_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matrix_seq_id")
    private Long id;

    private Long mondayQuantity;

    private Long tuesdayQuantity;

    private Long wednesdayQuantity;

    private Long thursdayQuantity;

    private Long fridayQuantity;

    private Long saturdayQuantity;

    private Long sundayQuantity;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMondayQuantity() {
        return mondayQuantity;
    }

    public void setMondayQuantity(Long mondayQuantity) {
        this.mondayQuantity = mondayQuantity;
    }

    public Long getTuesdayQuantity() {
        return tuesdayQuantity;
    }

    public void setTuesdayQuantity(Long tuesdayQuantity) {
        this.tuesdayQuantity = tuesdayQuantity;
    }

    public Long getWednesdayQuantity() {
        return wednesdayQuantity;
    }

    public void setWednesdayQuantity(Long wednesdayQuantity) {
        this.wednesdayQuantity = wednesdayQuantity;
    }

    public Long getThursdayQuantity() {
        return thursdayQuantity;
    }

    public void setThursdayQuantity(Long thursdayQuantity) {
        this.thursdayQuantity = thursdayQuantity;
    }

    public Long getFridayQuantity() {
        return fridayQuantity;
    }

    public void setFridayQuantity(Long fridayQuantity) {
        this.fridayQuantity = fridayQuantity;
    }

    public Long getSaturdayQuantity() {
        return saturdayQuantity;
    }

    public void setSaturdayQuantity(Long saturdayQuantity) {
        this.saturdayQuantity = saturdayQuantity;
    }

    public Long getSundayQuantity() {
        return sundayQuantity;
    }

    public void setSundayQuantity(Long sundayQuantity) {
        this.sundayQuantity = sundayQuantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
