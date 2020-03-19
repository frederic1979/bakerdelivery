package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    @Mapping(source = "id", target = "id")
    RestaurantDto map(Restaurant restaurant);

    List<RestaurantDto> maps(List<Restaurant> restaurants);

    // transf DTO ---> EntityManager
    Restaurant unmap(RestaurantDto restaurantDto);
}
