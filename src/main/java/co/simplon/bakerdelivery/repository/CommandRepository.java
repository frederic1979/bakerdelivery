package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command,Long> {

     List<Command> findCommandsByRestaurantId(Long restaurantId); //Derriere il y a du code SQL Spring va interpreter tt seul et sait qu 'il doit chercher via le restaurantId et pas restaurant !!

     //@Query pas nécessaire, juste si je veux implémenter avec du sql ensuite
     List<Command> findCommandsByDate(LocalDate date); // idem spring écrit tt seul la methode

     List<Command> findCommandsByDateAndRestaurantId(LocalDate date, Long restaurantId); //idem spring implémente cette methode findCommandsByDateAndRestaurant

     @Query("select command from Command command join command.restaurant restaurant where restaurant.id = :restaurantId and command.date >= :start and command.date <= :end")
     List<Command> findCommandBetweenDatesAndAndRestaurantId(Long restaurantId, LocalDate start, LocalDate end);



}
