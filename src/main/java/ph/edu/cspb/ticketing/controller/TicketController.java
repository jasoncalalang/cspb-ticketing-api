package ph.edu.cspb.ticketing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.edu.cspb.ticketing.domain.Ticket;
import ph.edu.cspb.ticketing.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ticket> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable String id) {
        Ticket ticket = service.findById(id);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(@RequestBody Ticket ticket) {
        return service.create(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable String id, @RequestBody Ticket ticket) {
        Ticket updated = service.update(id, ticket);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Ticket> close(@PathVariable String id) {
        Ticket closed = service.close(id);
        return closed != null ? ResponseEntity.ok(closed) : ResponseEntity.notFound().build();
    }
}
