package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;

import co.simplon.bakerdelivery.mappers.RestaurantMapper;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/bakerdelivery/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired //Spring créé instance tout seul, pas la peine dans RestoMapper de faire :RestaurantMapper INSTANCE = Mappers.getMapper( RestaurantMapper.class );
    RestaurantMapper restaurantMapper;

    /*Autowired permet :
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }*/

    //ici j'ai déja implementé, grace à Spring, la methode maps pour transf list de restau en liste de restDto
    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return restaurantMapper.maps(restaurantService.getRestaurants());
    }


   /*
   Ou on traite ici le cas de la liste
   @GetMapping
   public List<RestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>( restaurants.size());
        for (Restaurant restaurant : restaurants) {
            RestaurantDto restaurantDto = restaurantMapper.map(restaurant);
            restaurantDtos.add(restaurantDto);
        }
        System.out.println("liste des resto : "+restaurantDtos);
        return restaurantDtos;
    }*/


    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        //System.out.println("le nom du resto est" + restaurantMapper.map(restaurant.get()).getName()); //ici on doit préciser qu'on affiche le nom
        if (restaurant.isPresent()) {

            return ResponseEntity.ok(restaurantMapper.map(restaurant.get())); //coté front pas besoin, avec {{}} on recoit un objet restau et on decide d'afficher le nom
        } else return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.ok(restaurantMapper.map(restaurantService.createRestaurant(restaurant)));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long restaurantId) {
        try {

            return ResponseEntity.ok(restaurantMapper.map(restaurantService.updateRestaurant(restaurant, restaurantId)));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> deleteRestaurant(@PathVariable Long restaurantId) {

        if (restaurantService.deleteRestaurant(restaurantId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();

    }
}
