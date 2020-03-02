package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.model.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> getRestaurants();

    Optional<Restaurant> getRestaurantById(Long restaurantId);

    ResponseEntity<Restaurant> createRestaurant(Restaurant restaurant);

    ResponseEntity<Restaurant> updateRestaurant(Restaurant restaurant, Long restaurantId);

    ResponseEntity<Restaurant> deleteRestaurant(Long restaurantId);

}
