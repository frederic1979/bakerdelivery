package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command,Long> {

     List<Command> findCommandsByRestaurantId(Long restaurantId); // Spring va interpreter tt seul et sait qu 'il doit chercher via le restaurantId et pas restaurant !!


     List<Command> findCommandsByDate(LocalDate date); // idem spring écrit tt seul la methode

     List<Command> findCommandsByDateAndRestaurantId(LocalDate date, Long restaurantId); //idem spring implémente cette methode findCommandsByDateAndRestaurant
}
