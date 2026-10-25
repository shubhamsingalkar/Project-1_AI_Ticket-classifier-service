package in.codekerdos.ticketservice.client;

public record TicketClassification( //This class is used to hold the classification result from the classifier service
   String intent, String sentiment, String priority, String suggestedTeam) {

      private static final String UNCLASSIFIED = "UNCLASSIFIED";
      private static final String UNKNOWN = "UNKNOWN";
      private static final String DEFAULT_PRIORITY = "MEDIUM";
      private static final String DEFAULT_TEAM = "GENERAL_SUPPORT";



       /**
     * Returned by ClassifierClient when the AI Classifier Service is
     * unreachable, slow, or its own circuit breaker/retry gives up. The
     * ticket still gets created — just parked at a safe default instead of
     * failing the whole request with a 500.
     */
      public static TicketClassification unclassified(){
         return new TicketClassification(UNCLASSIFIED, UNKNOWN, DEFAULT_PRIORITY, DEFAULT_TEAM);
      }
}
