package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDto map(Restaurant restaurant);
}
