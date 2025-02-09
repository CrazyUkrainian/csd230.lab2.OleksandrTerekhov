package csd230.lab2.controllers;

import csd230.lab2.entities.Book;
import csd230.lab2.respositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String books(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/add-book")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String bookSubmit(@ModelAttribute Book book, Model model) {
        book.setDescription("Book: "+book.getTitle());
        model.addAttribute("book", book);
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/books";
    }
    @GetMapping("/edit-book")
    public String edit_book(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }
    @PostMapping("/edit-book")
    public String edit_bookSubmit(@ModelAttribute Book book, Model model) {
        book.setDescription("Book: "+book.getTitle());
        bookRepository.save(book);
//        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/books";
    }

    // BookController.java
    @PostMapping("/selection")
    public String processSelection(@RequestParam("selectedBooks") List<Integer> selectedBookIds) {
        // Process the selected book list here...
        System.out.println(selectedBookIds);
        for (Integer id : selectedBookIds) {
            Book book = bookRepository.findById(id);
            bookRepository.delete(book);
        }

        return "redirect:/books";
    }
}