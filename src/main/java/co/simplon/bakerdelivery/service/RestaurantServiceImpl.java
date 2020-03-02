package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.controller.RestaurantController;
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
    public ResponseEntity<Restaurant> getRestaurantById(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get()); //Pensez au .get() une methode de l'optional

        } else {
            return ResponseEntity.notFound().build();
        } //explication .notFound().build()?*/

    }

    @Override
    public ResponseEntity<Restaurant> createRestaurant(Restaurant restaurant) { //à quoi sert le ResponseEntity ici ?
        return ResponseEntity.ok(restaurantRepository.save(restaurant));
    }


    @Override
    public ResponseEntity<Restaurant> updateRestaurant(Restaurant restaurant, Long restaurantId) {
        if (restaurantRepository.existsById(restaurantId)) {
            //Set l'id du resto'--> on lui dit que l'id du resto à sauv est celui qui est dans l'url
            restaurant.setId(restaurantId);
            return ResponseEntity.ok(restaurantRepository.save(restaurant));
        } else {
            return ResponseEntity.notFound().build();
        }
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