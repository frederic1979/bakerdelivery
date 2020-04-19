package co.simplon.bakerdelivery.dto;

import java.time.LocalDate;

public class MatrixDto {

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

    private Long restaurantId;

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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
