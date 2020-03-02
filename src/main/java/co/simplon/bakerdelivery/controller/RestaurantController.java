package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/bakerdelivery")
public class RestaurantController {

    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) {
        if (restaurantService.getRestaurantById(restaurantId).isPresent()) {
            return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId).get());
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long restaurantId) {
        try {
            restaurantService.updateRestaurant(restaurant, restaurantId);
            return ResponseEntity.ok().build();
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long restaurantId) {

        return restaurantService.deleteRestaurant(restaurantId);

    }
}
