package in.codekerdos.ticketservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTicketRequest(

    @NotBlank (message = "Subject must not be blank")
    @Size (max = 200, message = "Subject must not exceed 200 characters")
    @JsonProperty ("subject")
    String subject,

    @NotBlank (message = "Description must not be blank")
    @Size (max = 4000, message = "Description must not exceed 4000 characters")
    String description,

    @NotBlank (message = "Requester email must not be blank")
    @Email (message = "Requester email must be a valid email address")
    String requesterEmail
) {
    
   }
