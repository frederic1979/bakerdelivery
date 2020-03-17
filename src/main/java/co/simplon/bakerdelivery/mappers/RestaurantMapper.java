package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.RestaurantDTO;
import co.simplon.bakerdelivery.model.Restaurant;

import java.util.Optional;


public interface RestaurantMapper {
    RestaurantDTO toDto(Optional<Restaurant> restaurant);
}
