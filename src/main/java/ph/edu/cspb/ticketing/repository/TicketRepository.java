package ph.edu.cspb.ticketing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ph.edu.cspb.ticketing.domain.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {}
