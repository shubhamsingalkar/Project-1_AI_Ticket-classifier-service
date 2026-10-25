package in.codekerdos.ticketservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.codekerdos.ticketservice.client.ClassifierClient;
import in.codekerdos.ticketservice.client.TicketClassification;
import in.codekerdos.ticketservice.dto.CreateTicketRequest;
import in.codekerdos.ticketservice.dto.TicketResponse;
import in.codekerdos.ticketservice.entity.Ticket;
import in.codekerdos.ticketservice.entity.TicketStatus;
import in.codekerdos.ticketservice.repository.TicketRepository;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ClassifierClient classifierClient;
    
    public TicketService(TicketRepository ticketRepository, ClassifierClient classifierClient) {
        this.ticketRepository = ticketRepository;
        this.classifierClient = classifierClient;
    }

    @Transactional 
    public TicketResponse create(CreateTicketRequest request){

        TicketClassification classification = classifierClient.classify(request.subject(), request.description());

        Ticket ticket = new Ticket();
        ticket.setSubject(request.subject());
        ticket.setDescription(request.description());
        ticket.setRequesterEmail(request.requesterEmail());
        ticket.setStatus(TicketStatus.OPEN);

        ticket.setIntent(classification.intent());
        ticket.setSentiment(classification.sentiment());
        ticket.setPriority(classification.priority());
        ticket.setSuggestedTeam(classification.suggestedTeam());

        Ticket savedTicket = ticketRepository.save(ticket); // Saves the ticket to the database

       System.out.println("Ticket generated: " + "status: "+savedTicket.getStatus());

        return TicketResponse.from(savedTicket);
    }

    @Transactional (readOnly = true)
    public TicketResponse getById(Long id) throws Exception {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new Exception("Ticket not found with id: " + id));
        return TicketResponse.from(ticket);
    }

    @Transactional (readOnly = true)
    public Page<TicketResponse> list(Pageable pageable) { //This method returns a paginated list of tickets, converting each Ticket entity to a TicketResponse DTO
        
        return ticketRepository.findAll(pageable)
                .map(TicketResponse::from);
    }

    @Transactional 
    public TicketResponse updateStatus(Long id, TicketStatus status) throws Exception{
        Ticket ticket = findOrThrow(id);
        ticket.setStatus(status);
        return TicketResponse.from(ticketRepository.save(ticket));
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Ticket ticket = findOrThrow(id);
        ticketRepository.delete(ticket);
    }

     private Ticket findOrThrow(Long id) throws Exception{
        return 
        ticketRepository.findById(id)
        .orElseThrow(() -> new Exception("Ticket not found" + id));
    }
    
}