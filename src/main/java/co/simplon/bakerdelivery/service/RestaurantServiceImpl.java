package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.controller.RestaurantController;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override //à quoi ça sert ?
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        return restaurant;

    }

    @Override
    public ResponseEntity<Restaurant> createRestaurant(Restaurant restaurant) { //à quoi sert le ResponseEntity ici ?
        return ResponseEntity.ok(restaurantRepository.save(restaurant));
    }


    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, Long restaurantId) throws RestaurantNotFoundException {
        if (restaurantRepository.existsById(restaurantId)) {
            restaurant.setId(restaurantId);
            return restaurantRepository.save(restaurant);
        } else
            throw new RestaurantNotFoundException();

    }


    @Override
    public ResponseEntity<Restaurant> deleteRestaurant(Long restaurantId) {
        if (restaurantRepository.existsById(restaurantId)) {
            restaurantRepository.deleteById(restaurantId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}