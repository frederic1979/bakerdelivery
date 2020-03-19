package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bakerdelivery/commands")
@CrossOrigin("*")
public class CommandController {

    @Autowired
    CommandService commandService;

    @Autowired
    CommandMapper commandMapper;

    //On fait le constructeur, inutile avec autowired

    /*public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }*/


    @GetMapping
    public List<Command> getCommands() {

        return commandService.getCommands();
    }



    @GetMapping("/{commandId}")
    public ResponseEntity<Command> getCommandById(@PathVariable Long commandId) {
        Optional<Command> command = commandService.getCommandById(commandId);
        if (command.isPresent()) {
            System.out.println("Commande ID : " +commandMapper.map(commandService.getCommandById(commandId).get()).getId());
            System.out.println("Restaurant ID : " +commandMapper.map(commandService.getCommandById(commandId).get()).getRestaurantId());
            return ResponseEntity.ok(command.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Command> createCommand(@RequestBody Command command) {
        try {
            return ResponseEntity.ok(commandService.createCommand(command));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{commandId}")
    public ResponseEntity<Command> updateCommand(@RequestBody Command command, @PathVariable Long commandId) {

        try {

            return ResponseEntity.ok(commandService.updateCommand(command, commandId));
        } catch (CommandNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{commandId}")
    public ResponseEntity<Command> deleteCommand(@PathVariable Long commandId) {
        if (commandService.deleteCommand(commandId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<List<Command>> getCommandsByRestaurant(@PathVariable Long restaurantId) {
        try {

            return ResponseEntity.ok(commandService.getCommandsByRestaurant(restaurantId));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("date/{date}")
    public ResponseEntity<?> getCommandsByDate(@PathVariable String date) {
        try {
            LocalDate newDate = LocalDate.parse(date);
            return ResponseEntity.ok(commandService.getCommandsByDate(newDate));
        }//on convertit notre string en LocalDate
        catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("pb date..");
        }

    }

    @GetMapping("date/{date}/{restaurantId}")
    public List<Command> getCommandsByDateAndRestaurant(@PathVariable String date, @PathVariable Long restaurantId) {
        LocalDate newDate = LocalDate.parse(date);
        return commandService.getCommandsByDateAndRestaurantId(newDate, restaurantId);
    }


}
