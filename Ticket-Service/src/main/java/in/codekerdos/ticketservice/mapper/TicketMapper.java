package in.codekerdos.ticketservice.mapper;
import org.mapstruct.Mapper;

import in.codekerdos.ticketservice.dto.TicketResponse;
import in.codekerdos.ticketservice.entity.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketResponse toResponse(Ticket ticket);
    
}
