package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.service.CommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/bakerdelivery/command")
public class CommandController {


    CommandService commandService;

    //On fait le constructeur
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }


    @GetMapping
    public List<Command> getCommands() {
        return commandService.getCommands();
    }


    @GetMapping("/{commandId}")
    public ResponseEntity<Command> getCommandById(@PathVariable Long commandId) {
        if (commandService.getCommandById(commandId).isPresent()){
        return ResponseEntity.ok(commandService.getCommandById(commandId).get());}
        else return ResponseEntity.notFound().build();

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
        return commandService.updateCommand(command, commandId);
    }

    @DeleteMapping("/{commandId}")
    public ResponseEntity<Command> deleteCommand(@PathVariable Long commandId) {
        return commandService.deleteCommand(commandId);
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
    public List<Command> getCommandsByDate(@PathVariable String date) {
        LocalDate newDate = LocalDate.parse(date); //on convertit notre string en LocalDate
        return commandService.getCommandsByDate(newDate);
    }

    @GetMapping("{date}/{restaurantId}")
    public List<Command> getCommandsByDateAndRestaurant(@PathVariable String date, @PathVariable Long restaurantId) {
        LocalDate newDate = LocalDate.parse(date);
        return commandService.getCommandsByDateAndRestaurantId(newDate, restaurantId);
    }


}
