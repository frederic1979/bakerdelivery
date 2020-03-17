package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.RestaurantDTO;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.mappers.RestaurantMapper;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/bakerdelivery/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;


     RestaurantMapper restaurantMapper;

    /*public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }*/

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    //J'ai tenté le DTO ici, j'attends ta valid avant de poursuivre
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long restaurantId) {
        if (restaurantService.getRestaurantById(restaurantId).isPresent()) {
            Optional<Restaurant> restaurant=restaurantService.getRestaurantById(restaurantId);
            return ResponseEntity.ok(restaurantMapper.toDto(restaurant));
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.ok(restaurantService.createRestaurant(restaurant));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long restaurantId) {
        try {

            return ResponseEntity.ok(restaurantService.updateRestaurant(restaurant, restaurantId));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long restaurantId) {

        if (restaurantService.deleteRestaurant(restaurantId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();

    }
}
