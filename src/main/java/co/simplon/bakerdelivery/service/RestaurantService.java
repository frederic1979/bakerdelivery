package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> getRestaurants();

    Optional<Restaurant> getRestaurantById(Long restaurantId);

    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Restaurant restaurant, Long restaurantId);

    Boolean deleteRestaurant(Long restaurantId);

}
