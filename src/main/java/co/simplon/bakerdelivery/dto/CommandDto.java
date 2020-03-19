package co.simplon.bakerdelivery.dto;

import co.simplon.bakerdelivery.model.Restaurant;

import javax.persistence.*;
import java.time.LocalDate;

public class CommandDto {


    private Long id;

    private LocalDate date;

    private Long quantity;

    private Restaurant restaurant;

}
