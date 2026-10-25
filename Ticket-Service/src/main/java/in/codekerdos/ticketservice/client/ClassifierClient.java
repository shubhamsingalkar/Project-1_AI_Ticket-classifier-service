package in.codekerdos.ticketservice.client;


import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component 
public class ClassifierClient {

    private final WebClient classifierWebClient;
    private final Duration callTimeout;

    private static final Logger log = LoggerFactory.getLogger(ClassifierClient.class);

    
    public ClassifierClient(WebClient classifierWebClient, 
                             @Value("${app.classifier-service.timeout-ms:4000}") long timeoutMs) {
        this.classifierWebClient = classifierWebClient;
        this.callTimeout = Duration.ofMillis(timeoutMs);
    }
    
    @CircuitBreaker (name = "classifier")
    @Retry (name = "classifier", fallbackMethod = "classifyFallBack")
    public TicketClassification classify(String subject, String description){
        return classifierWebClient.post()
                .uri("/api/classify")
                .bodyValue(new ClassifyRequest(subject, description))
                .retrieve()

               /* .onStatus(
                    status -> status.isError(),
                    response -> response.bodyToMono(String.class)
                            .defaultIfEmpty("No error details returned")
                            .map(errorBody -> new RuntimeException(
                                    "Classifier service error: "
                                            + response.statusCode()
                                            + " - " + errorBody))
                     )  */
                     // Abobe commented code is the error handling code  (To know better and detailed error)
                     // This will help you identify whether the error comes from the classifier endpoint, the Groq API, or response conversion.                      
                         

                .bodyToMono(TicketClassification.class) //This will convert the response body to a TicketClassification object
                .block();

   }
   
   @SuppressWarnings ("unused")
   private TicketClassification classifyFallBack(String subject, String description, Throwable throwable){

    System.out.println("FALLBACK METHOD CALLED by CIRCUIT BREAKER.......");

     log.warn("AI classifier service is unavailable, degrading to default Reason={}", throwable.toString());

     return TicketClassification.unclassified();

   }
    
    
}
