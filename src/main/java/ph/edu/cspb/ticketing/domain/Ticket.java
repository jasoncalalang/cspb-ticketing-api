package ph.edu.cspb.ticketing.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
public class Ticket {
    @Id
    private String id;

    private String requestor;
    private String type;
    private String description;
    private String comments;
    private String assignedUser;

    private TicketStatus status = TicketStatus.OPEN;

    public Ticket() {}

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRequestor() { return requestor; }
    public void setRequestor(String requestor) { this.requestor = requestor; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public String getAssignedUser() { return assignedUser; }
    public void setAssignedUser(String assignedUser) { this.assignedUser = assignedUser; }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
