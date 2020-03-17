package co.simplon.bakerdelivery.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RestaurantDto {


    private Long restaurantId;
    private String name;
    private String adresse;
    private String email;


}
