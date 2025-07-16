package ph.edu.cspb.ticketing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.ResponseEntity;
import ph.edu.cspb.ticketing.domain.Ticket;
import ph.edu.cspb.ticketing.domain.TicketStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class TicketControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void ticketLifecycle() {
        // create ticket
        Ticket toCreate = new Ticket();
        toCreate.setRequestor("Alice");
        toCreate.setType("Issue");
        toCreate.setDescription("Something broke");
        ResponseEntity<Ticket> createResp = restTemplate.postForEntity("/tickets", toCreate, Ticket.class);
        Ticket created = createResp.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        // list tickets
        Ticket[] all = restTemplate.getForObject("/tickets", Ticket[].class);
        assertThat(all).hasSize(1);

        // update ticket
        created.setDescription("fixed");
        restTemplate.put("/tickets/" + created.getId(), created);
        Ticket updated = restTemplate.getForObject("/tickets/" + created.getId(), Ticket.class);
        assertThat(updated.getDescription()).isEqualTo("fixed");

        // close ticket
        restTemplate.postForEntity("/tickets/" + created.getId() + "/close", null, Ticket.class);
        Ticket closed = restTemplate.getForObject("/tickets/" + created.getId(), Ticket.class);
        assertThat(closed.getStatus()).isEqualTo(TicketStatus.CLOSED);
    }
}
