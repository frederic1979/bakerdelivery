package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
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
            return ResponseEntity.ok(commandMapper.toDto(command.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/{restaurantId}/{dateString}")
    public ResponseEntity<CommandDto> getCommandByRestaurantIdAndDate(@PathVariable Long restaurantId, @PathVariable String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return ResponseEntity.ok(commandMapper.toDto(commandService.getCommandByRestaurantIdAndDate(restaurantId, date)));
    }


/*    @GetMapping("etat/{etat}/{dateString}")
    public List<CommandDto> getCommandsByEtatAndDate(@PathVariable Etat etat,
                                              @PathVariable String dateString) {
LocalDate date = LocalDate.parse(dateString);
        return commandService.getCommandsByDate(etat, date);
    }*/

    @GetMapping("restaurant/{restaurantId}/datesbetween")
    public ResponseEntity<List<Command>> getCommandsByRestaurantAndOptionalDateAndBetweenTwoDates(
            @PathVariable Long restaurantId,
            @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        try {

            return ResponseEntity.ok(commandService.getCommandsByRestaurantIdAndBetweenTwoDates(restaurantId, start, end));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }


    /*@GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<CommandDto> getCommandByRestaurantIdAndDate(
            @PathVariable Long restaurantId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {


        try {
            return ResponseEntity.ok(commandService.getCommandByDateAndRestaurantId(date, restaurantId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
*/



    @GetMapping("date/{stringDate}")
    public List<CommandDto> getCommandsByDate(@PathVariable String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        return commandService.getCommandsByDate(date);

    }


    @GetMapping("etat/{etat}/{stringDate}")
    public List<CommandDto> getCommandsByDate(@PathVariable Etat etat, @PathVariable String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        return commandService.getCommandsByEtatAndDate(etat,date);

    }


/*
    @GetMapping("date/{date}")
    public List<CommandDto> getCommandsByDate(@PathVariable String stringDate) {
        try {
            LocalDate date = LocalDate.parse(stringDate);
            return ResponseEntity.ok(commandService.getCommandsByDate(date));
        }//on convertit notre string en LocalDate
        catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("pb date..");
        }

    }*/



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


}
