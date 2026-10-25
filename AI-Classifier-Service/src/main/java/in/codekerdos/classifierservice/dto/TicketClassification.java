package in.codekerdos.classifierservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record TicketClassification(
  @JsonPropertyDescription ("The customer's underlying issue category. One of: BILLING, TECHNICAL_ISSUE, ACCOUNT_ACCESS, FEATURE REQUEST, GENERAL_INQUIRY")
  String intent,

  @JsonPropertyDescription ("Overall emotional tone of the ticket text. One of: POSITIVE, NEGATIVE, NEUTRAL")
  String sentiment,

  @JsonPropertyDescription ("How urgently those ticket needs to be handled. One of: LOW, MEDIUM, HIGH, URGENT")
  String priority,

  @JsonPropertyDescription ("The support team best suited to own this ticket. One of: BILLING_TEAM, TECHNICAL_SUPPORT, ACCOUNT_MANAGEMENT, PRODUCT_TEAM, GENERAL_SUPPORT")
  String suggestedTeam
) {
    
}
