package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
import co.simplon.bakerdelivery.model.Matrix;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.repository.CommandRepository;
import co.simplon.bakerdelivery.repository.MatrixRepository;
import co.simplon.bakerdelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    MatrixRepository matrixRepository;

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

    @Transactional
    @Override
    public Command getCommandByRestaurantIdAndDate(Long restaurantId, LocalDate date) {
        Optional<Command> existingCommand = commandRepository.findCommandByRestaurantIdAndDate(restaurantId, date);

        if (existingCommand.isPresent()) { /*si la commande est presente dans le repo on la renvoi*/

            return existingCommand.get();
        } else {

            Optional<Matrix> matrix =
                    matrixRepository.findFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(
                            restaurantId,
                            date.getDayOfWeek().getValue() - 1, // Get day of week from date
                            date
                    );

            if (matrix.isPresent()) {
                Command command = new Command(date,
                        matrix.get().getQuantity(),
                        Etat.Attente,
                        restaurantRepository.findById(restaurantId).get());
                return commandRepository.save(command);


            } else {
                throw new CommandNotFoundException();
            }


        }

    }


    @Override
    public List<CommandDto> getCommandsByDate(LocalDate date) {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<CommandDto> commandsOfTheDay = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            Optional<Command> existingCommand = commandRepository.findCommandByRestaurantIdAndDate(restaurant.getId(), date);


            if (existingCommand.isPresent()) {
                commandsOfTheDay.add(commandMapper.toDto(existingCommand.get()));

            } else {

                Optional<Matrix> matrix =
                        matrixRepository.findFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(
                                restaurant.getId(),
                                date.getDayOfWeek().getValue() - 1, // Get day of week from date
                                date
                        );



                 if (matrix.isPresent()) {

                    /*sinon si la matrix existe on créé la newCommandDto*/
                    CommandDto commandDto = new CommandDto(date,
                            matrix.get().getQuantity(),
                            Etat.Attente,
                            restaurant.getId());
                    commandRepository.save(commandMapper.toEntity(commandDto));
                    commandsOfTheDay.add(commandDto);

                } else {
                    throw new CommandNotFoundException();
                }
            }
        }

        /*à ce stade, les commandes sont save dans notre table, on peut faire appel à findCommandsByEtatAndDate(etat,date) pour avoir
        * les commandes avec l'etat demandé*/

        return commandsOfTheDay;
    }

    @Override
    public List<CommandDto> getCommandsByEtatAndDate(Etat etat, LocalDate date){
        List<CommandDto> commandDtoList = commandMapper.toDto(commandRepository.findCommandsByEtatAndDate(etat,date));
        return commandDtoList;

    }


    @Override
    public List<Command> getCommandsByRestaurantIdAndBetweenTwoDates(Long restaurantId, LocalDate start, LocalDate end) {

        if (start == null && end == null) {
            return this.getCommandsByRestaurant(restaurantId);
        } else {
            return commandRepository.findCommandsBetweenDatesAndAndRestaurantId(restaurantId, start, end);
        }
    }



/*
    @Override
    public CommandDto getCommandByDateAndRestaurantId(LocalDate date,Long restaurantId){

        return commandMapper.toDto(commandRepository.findCommandByDateAndRestaurantId(date, restaurantId));
    }
*/



}
