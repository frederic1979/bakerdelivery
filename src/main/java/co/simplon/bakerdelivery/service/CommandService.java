package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.repository.CommandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface CommandService {

    List<Command> getCommands();

    ResponseEntity<Command> getCommandById(Long commandId);

    Command createCommand(Command command);

    ResponseEntity<Command> updateCommand(Command command, Long commandId);

    ResponseEntity<Command> deleteCommand(Long commandId);

    List<Command> getCommandsByRestaurant(Long restaurantId);

    List<Command> getCommandsByDate(LocalDate date);

    List<Command> getCommandsByDateAndRestaurantId(LocalDate date, Long restaurantId);

}
