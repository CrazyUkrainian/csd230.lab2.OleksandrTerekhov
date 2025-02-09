package csd230.lab2.controllers;

import csd230.lab2.entities.DiscMag;
import csd230.lab2.respositories.DiscMagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discMags")
public class DiscMagController {

    private final DiscMagRepository discMagRepository;

    public DiscMagController(DiscMagRepository discMagRepository) {
        this.discMagRepository = discMagRepository;
    }

    @GetMapping
    public String discMags(Model model) {
        model.addAttribute("discMags", discMagRepository.findAll());
        return "discMags";
    }

    @GetMapping("/add-discMag")
    public String discMagForm(Model model) {
        model.addAttribute("discMag", new DiscMag());
        return "add-discMag";
    }

    @PostMapping("/add-discMag")
    public String discMagSubmit(@ModelAttribute DiscMag discMag, Model model) {
        model.addAttribute("discMag", discMag);
        discMagRepository.save(discMag);
        model.addAttribute("discMags", discMagRepository.findAll());
        return "redirect:/discMags";
    }

    @GetMapping("/edit-discMag")
    public String editDiscMag(@RequestParam(value = "id") Integer id, Model model) {
        DiscMag discMag = discMagRepository.findById(id);

        if (discMag == null) {
            // If no DiscMag is found, redirect or handle the error as you like
            return "redirect:/discMags"; // Or show an error message
        }

        model.addAttribute("discMag", discMag);
        return "edit-discMag";
    }

    @PostMapping("/edit-discMag")
    public String editDiscMagSubmit(@ModelAttribute DiscMag discMag, Model model) {
        // Optionally modify some fields before saving
        discMag.setDescription("DiscMag: " + discMag.getTitle()); // Example modification

        discMagRepository.save(discMag); // Save the edited DiscMag
        return "redirect:/discMags"; // Redirect back to the list
    }


    @PostMapping("/selection")
    public String processSelection(@RequestParam("selectedDiscMags") List<Integer> selectedDiscMagIds) {
        for (Integer id : selectedDiscMagIds) {
            DiscMag discMag = discMagRepository.findById(id);
            discMagRepository.delete(discMag);
        }
        return "redirect:/discMags";
    }
}
