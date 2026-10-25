package in.codekerdos.ticketservice.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=100)
    private String subject;

    @Column(nullable = false, length=4000)
    private String description;

    @Column(nullable = false)
    private String requesterEmail;
    
  
    @Column(nullable = false, length=20)
    private TicketStatus status = TicketStatus.OPEN;



    // AI derived fields

    @Column (length=30)
    private String priority;

    @Column (length=20)
    private String sentiment;

    @Column (length=40)
    private String intent;

    @Column (length=40)
    private String suggestedTeam;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;


    public Ticket() {
    }

    @PrePersist 
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
        if(this.status == null) {
            this.status = status.OPEN;
        }
    }

     @PreUpdate 
     void onUpdate() {
        this.updatedAt = Instant.now();
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getSubject() {
         return subject;
     }

     public void setSubject(String subject) {
         this.subject = subject;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public String getRequesterEmail() {
         return requesterEmail;
     }

     public void setRequesterEmail(String requesterEmail) {
         this.requesterEmail = requesterEmail;
     }

     public TicketStatus getStatus() {
         return status;
     }

     public void setStatus(TicketStatus ticketStatus) {
         this.status = ticketStatus;
     }

     public String getPriority() {
         return priority;
     }

     public void setPriority(String priority) {
         this.priority = priority;
     }

     public String getSentiment() {
         return sentiment;
     }

     public void setSentiment(String sentiment) {
         this.sentiment = sentiment;
     }

     public String getIntent() {
         return intent;
     }

     public void setIntent(String intent) {
         this.intent = intent;
     }

     public String getSuggestedTeam() {
         return suggestedTeam;
     }

     public void setSuggestedTeam(String suggestedTeam) {
         this.suggestedTeam = suggestedTeam;
     }

     public Instant getCreatedAt() {
         return createdAt;
     }

     public void setCreatedAt(Instant createdAt) {
         this.createdAt = createdAt;
     }

     public Instant getUpdatedAt() {
         return updatedAt;
     }

     public void setUpdatedAt(Instant updatedAt) {
         this.updatedAt = updatedAt;
     }
}
