package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.model.Command;



public interface CommandMapper {
    CommandDto toDto(Command command);
}
