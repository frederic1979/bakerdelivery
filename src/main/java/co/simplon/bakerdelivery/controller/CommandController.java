package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    public List<CommandDto> getCommands() {

        return commandMapper.toDto(commandService.getCommands());
    }



    @GetMapping("/{commandId}")
    public ResponseEntity<CommandDto> getCommandById(@PathVariable Long commandId) {
        Optional<Command> command = commandService.getCommandById(commandId);
        if (command.isPresent()) {
            System.out.println("Commande ID : " +commandMapper.toDto(commandService.getCommandById(commandId).get()).getId());
            System.out.println("Restaurant ID : " +commandMapper.toDto(commandService.getCommandById(commandId).get()).getRestaurantId());
            //System.out.println("Restaurant Name : " + commandMapper.toDto(commandService.getCommandById(commandId).get()).getName());
            return ResponseEntity.ok(commandMapper.toDto(command.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<?> createCommand(@RequestBody CommandDto command) {
        try {
            return ResponseEntity.ok(commandService.createCommand(command));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{commandId}")
    public ResponseEntity<CommandDto> updateCommand(@RequestBody CommandDto commandDto, @PathVariable Long commandId) {
        System.out.println("dans controller de l'update");
        System.out.println(commandDto.getRestaurantId());
        System.out.println(commandDto.getQuantity());
        System.out.println(commandDto.getDate());
        System.out.println(commandId);
        try {

            return ResponseEntity.ok(commandService.updateCommand(commandDto, commandId));
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
    public ResponseEntity<List<Command>> getCommandsByRestaurant(
            @PathVariable Long restaurantId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
            @RequestParam(value = "start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end) {
        /*ici le requestParam n est pas obligat...false*/

        try {

            return ResponseEntity.ok(commandService.getCommandsByRestaurantIdAndBetweenTwoDates(restaurantId,date, start, end));
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

    /*@GetMapping("date/{date}/{restaurantId}")
    public List<Command> getCommandsByDateAndRestaurant(@PathVariable String date, @PathVariable Long restaurantId) {
        LocalDate newDate = LocalDate.parse(date);
        return commandService.getCommandsByDateAndRestaurantId(newDate, restaurantId);
    }*/


}
