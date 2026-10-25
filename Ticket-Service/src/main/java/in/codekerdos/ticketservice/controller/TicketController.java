package in.codekerdos.ticketservice.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.codekerdos.ticketservice.dto.CreateTicketRequest;
import in.codekerdos.ticketservice.dto.TicketResponse;
import in.codekerdos.ticketservice.dto.UpdateTicketStatusRequest;
import in.codekerdos.ticketservice.service.TicketService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/tickets")
public class TicketController {
    
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody @Valid CreateTicketRequest request){

        TicketResponse response= ticketService.create(request);

        return 
        ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<TicketResponse> getById(@PathVariable Long id) throws Exception{

          TicketResponse response = ticketService.getById(id);
          return ResponseEntity.ok(response);
    }

      // @GetMapping 
    // public ResponseEntity<Page<TicketResponse>> list(
    //     @RequestParam(required = false) TicketStatus status,
    //     @PageableDefault(size=20, sort = "createdAt") Pageable pageable){
    //         Page<TicketResponse> page = status == null
    //         ? ticketService.list(pageable)
    //         : ticketService.l
    //     }

    @GetMapping 
    public Page<TicketResponse> list(Pageable pageable){
        return ticketService.list(pageable);
    }

    @PatchMapping("/{id}/updatestatus")
    public TicketResponse updateStatus(@PathVariable Long id, @RequestBody @Valid UpdateTicketStatusRequest request) throws Exception{
      return 
            ticketService.updateStatus(id, request.status());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        ticketService.delete(id);
        return ResponseEntity.noContent().build();

    }
    
}
