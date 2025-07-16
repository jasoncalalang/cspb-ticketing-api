package ph.edu.cspb.ticketing.service;

import org.springframework.stereotype.Service;
import ph.edu.cspb.ticketing.domain.Ticket;
import ph.edu.cspb.ticketing.domain.TicketStatus;
import ph.edu.cspb.ticketing.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Ticket create(Ticket ticket) {
        ticket.setStatus(TicketStatus.OPEN);
        return repository.save(ticket);
    }

    public Ticket update(String id, Ticket updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setRequestor(updated.getRequestor());
                    existing.setType(updated.getType());
                    existing.setDescription(updated.getDescription());
                    existing.setComments(updated.getComments());
                    existing.setAssignedUser(updated.getAssignedUser());
                    return repository.save(existing);
                })
                .orElse(null);
    }

    public Ticket close(String id) {
        return repository.findById(id)
                .map(ticket -> {
                    ticket.setStatus(TicketStatus.CLOSED);
                    return repository.save(ticket);
                })
                .orElse(null);
    }
}
