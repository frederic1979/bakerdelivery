package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
import co.simplon.bakerdelivery.repository.CommandRepository;
import co.simplon.bakerdelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    CommandRepository commandRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CommandMapper commandMapper;


    //Constructeur
    /*private CommandServiceImpl(CommandRepository commandRepository, RestaurantRepository restaurantRepository) {
        this.commandRepository = commandRepository;
        this.restaurantRepository = restaurantRepository;

    }*/

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
    public CommandDto createCommand(CommandDto commandDto) {
        Command command = commandMapper.toEntity(commandDto);
        /*System.out.println("Restau ID de commandDto : "+commandDto.getRestaurantId());
        System.out.println("command : "+command.getRestaurant().getId());*/
        command = commandRepository.save(command);
        return commandMapper.toDto(command);
    }


    @Override
    public CommandDto updateCommand(CommandDto commandDto, Long commandId) throws CommandNotFoundException {

        if (!commandRepository.existsById(commandId)) {
            throw new CommandNotFoundException();
        } else {
            Command command = commandMapper.toEntity(commandDto);
            command.setId(commandId); //si on ne met pas le set, on créé des news
            command = commandRepository.save(command);

            return commandMapper.toDto(command);
        }

    }

    @Override
    public Boolean deleteCommand(Long commandId) {
        if (commandRepository.existsById((commandId))) {
            commandRepository.deleteById(commandId);
            return true;
        } else return false;

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
    public List<Command> getCommandsByRestaurantIdAndBetweenTwoDates(Long restaurantId, LocalDate date, LocalDate start, LocalDate end) {

        if (start == null && date == null && end==null) {
            return this.getCommandsByRestaurant(restaurantId);
        } else {
            if (start == null && end==null)
            {

            return commandRepository.findCommandsByDateAndRestaurantId(date, restaurantId);}
        else if (date==null){ return commandRepository.findCommandBetweenDatesAndAndRestaurantId(restaurantId,start,end);}
            else return null;}
    }

@Override
    public List<CommandDto> getCommandsByEtat(Etat etat, LocalDate date){
        List<CommandDto> commandDtos = commandMapper.toDto(commandRepository.findCommandsByEtatAndDate(etat, date));
        return commandDtos;
}


}
