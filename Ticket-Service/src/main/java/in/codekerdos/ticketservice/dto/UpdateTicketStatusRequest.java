package in.codekerdos.ticketservice.dto;

import in.codekerdos.ticketservice.entity.TicketStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketStatusRequest(

    @NotNull (message = "Status must not be null")
    TicketStatus status

) {
    
}
