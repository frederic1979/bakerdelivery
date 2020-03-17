package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper
public interface RestaurantMapper {

    /*@Mapping(source = "id" ,target ="restaurantId")*/
    RestaurantDto toDto(Optional<Restaurant> restaurant);
}
