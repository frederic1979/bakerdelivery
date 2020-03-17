package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.CommandDTO;
import co.simplon.bakerdelivery.model.Command;



public interface CommandMapper {
    CommandDTO toDto(Command command);
}
