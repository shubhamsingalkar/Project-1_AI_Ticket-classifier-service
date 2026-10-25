package in.codekerdos.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.codekerdos.ticketservice.entity.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long>  {
    
}
