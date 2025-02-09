package csd230.lab2.controllers;

import csd230.lab2.entities.Ticket;
import csd230.lab2.respositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String tickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets";
    }

    @GetMapping("/add-ticket")
    public String ticketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "add-ticket";
    }

    @PostMapping("/add-ticket")
    public String ticketSubmit(@ModelAttribute Ticket ticket, Model model) {
        ticket.setDescription("Ticket: " + ticket.getText());
        model.addAttribute("ticket", ticket);
        ticketRepository.save(ticket);
        model.addAttribute("tickets", ticketRepository.findAll());
        return "redirect:/tickets";
    }
    @GetMapping("/edit-ticket")
    public String editTicket(@RequestParam("id") Integer id, Model model) {
        Ticket ticket = ticketRepository.findById(id);
        if (ticket == null) {
            // Handle case when ticket is not found (e.g., show 404 error)
            return "redirect:/tickets"; // Or show a "not found" page
        }
        model.addAttribute("ticket", ticket);
        return "edit-ticket";
    }


    @PostMapping("/edit-ticket")
    public String editTicketSubmit(@ModelAttribute Ticket ticket, Model model) {
        ticket.setDescription("Ticket: " + ticket.getText());
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("/selection")
    public String processSelection(@RequestParam("selectedTickets") List<Integer> selectedTicketIds) {
        // Process the selected ticket list here...
        System.out.println(selectedTicketIds);
        for (Integer id : selectedTicketIds) {
            Ticket ticket = ticketRepository.findById(id);
            ticketRepository.delete(ticket);
        }

        return "redirect:/tickets";
    }
}
