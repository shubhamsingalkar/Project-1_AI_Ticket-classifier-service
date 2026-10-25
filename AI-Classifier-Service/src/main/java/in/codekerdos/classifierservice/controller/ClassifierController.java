package in.codekerdos.classifierservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.codekerdos.classifierservice.dto.ClassifyRequest;
import in.codekerdos.classifierservice.dto.TicketClassification;
import in.codekerdos.classifierservice.service.TicketClassifierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/api/classify")
public class ClassifierController {

    private final TicketClassifierService ticketClassifierService;
    public ClassifierController(TicketClassifierService ticketClassifierService) {
        this.ticketClassifierService = ticketClassifierService;
        System.out.println("Reached Ai classifier controller...");
    }

    @PostMapping 
    public ResponseEntity<TicketClassification> classify(
        @RequestBody @Valid ClassifyRequest request){

            return 
            ResponseEntity.ok(ticketClassifierService.classify(request));
        }
    
    
}
