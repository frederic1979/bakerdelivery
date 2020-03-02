package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.repository.CommandRepository;
import co.simplon.bakerdelivery.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandServiceImpl implements CommandService {

    CommandRepository commandRepository;
    RestaurantRepository restaurantRepository;


    //Constructeur
    public CommandServiceImpl(CommandRepository commandRepository, RestaurantRepository restaurantRepository) {
        this.commandRepository = commandRepository;
        this.restaurantRepository = restaurantRepository;

    }

    @Override
    public List<Command> getCommands() {
        return commandRepository.findAll();
    }


    @Override
    public Optional<Command> getCommandById(Long commandId) {
        // d'abord on voit si la commande correspondant à cet ID existe
        Optional<Command> command = commandRepository.findById(commandId); //comme le findById est un optional,la command n'existe pas forcemm, on aura pas un type command mais Optional<Command>
        return command;
    }

    @Override
    public Command createCommand(Command command) {
        return commandRepository.save(command);
    }


    @Override
    public ResponseEntity<Command> updateCommand(Command command, Long commandId) {

        if (commandRepository.existsById(commandId)) {
            command.setId(commandId);
            commandRepository.save(command);
            return ResponseEntity.ok().build(); //l'appi Rest renverra un 200OK , si on fait return ResponseEntity.ok(commandRepository.save(command)) on a aussi la command à jour qui s'affiche
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Command> deleteCommand(Long commandId) {
        if (commandRepository.existsById(commandId)) {
            commandRepository.deleteById(commandId);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();

    }

    @Override
    public List<Command> getCommandsByRestaurant(Long restaurantId) {

        List<Command> commands = new ArrayList<>();
        if (restaurantRepository.existsById(restaurantId)) { //On verifie que le restauID existe bien
            return commandRepository.findCommandsByRestaurantId(restaurantId); //Si oui

        } else {
            return commands;
        }

    }

    @Override
    public List<Command> getCommandsByDate(LocalDate date) {
        //LocalDate date = LocalDate.of(2020,02,07);
               return commandRepository.findCommandsByDate(date);

    }


    @Override
    public List<Command> getCommandsByDateAndRestaurantId(LocalDate date, Long restaurantId){
        return commandRepository.findCommandsByDateAndRestaurantId(date,restaurantId);
    }

}
