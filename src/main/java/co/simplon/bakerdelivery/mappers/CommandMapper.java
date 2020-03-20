package co.simplon.bakerdelivery.mappers;


import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.model.Command;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
    CommandDto map(Command command);

    List<CommandDto> maps(List<Command> commands);

}
