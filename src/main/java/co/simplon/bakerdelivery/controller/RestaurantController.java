package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.exception.EntityNotFoundException;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;

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

    @Autowired //Spring créé instance tout seul, pas la peine dans RestoMapper de faire :RestaurantMapper INSTANCE = Mappers.getMapper( RestaurantMapper.class );
    RestaurantMapper restaurantMapper;

    /*Autowired permet :
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }*/

    //ici j'ai déja implementé, grace à Spring, la methode maps pour transf list de restau en liste de restDto
    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return restaurantMapper.toDto(restaurantService.getRestaurants());
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
        try {
            RestaurantDto restaurant = restaurantService.getRestaurantById(restaurantId);
            return ResponseEntity.ok(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        try {
            return ResponseEntity.ok(restaurantService.createRestaurant(restaurantDto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable Long restaurantId) {
        try {

            return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantDto, restaurantId));
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
