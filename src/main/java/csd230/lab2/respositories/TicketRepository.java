package csd230.lab2.respositories;

import csd230.lab2.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  Ticket findById(long id);
  }