package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;

import co.simplon.bakerdelivery.mappers.RestaurantMapper;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/bakerdelivery/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired //Spring créé instance tout seul
    RestaurantMapper restaurantMapper;

    /*public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }*/

    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        List<RestaurantDto> restaurantDtos = null;
        for (Restaurant restaurant : restaurants) {
            RestaurantDto restaurantDto = restaurantMapper.map(restaurant);
            restaurantDtos.add(restaurantDto);
        }

        return restaurantDtos;
    }


    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        System.out.println("le nom du resto est" + restaurantMapper.map(restaurant.get()).getName()); //ici on doit préciser qu'on affiche le nom
        if (restaurant.isPresent()) {

            return ResponseEntity.ok(restaurantMapper.map(restaurant.get())); //coté front pas besoin, avec {{}} on recoit un objet restau et on decide d'afficher le nom
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
