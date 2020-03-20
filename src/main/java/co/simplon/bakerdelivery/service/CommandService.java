package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.repository.CommandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface CommandService {

    List<Command> getCommands();

    Optional<Command> getCommandById(Long commandId);

    CommandDto createCommand(CommandDto command);

    CommandDto updateCommand(CommandDto command, Long commandId) throws CommandNotFoundException;

    Boolean deleteCommand(Long commandId);

    List<Command> getCommandsByRestaurant(Long restaurantId);

    List<Command> getCommandsByDate(LocalDate date);

    List<Command> getCommandsByDateAndRestaurantId(LocalDate date, Long restaurantId);

}
