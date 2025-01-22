package csd230.lab2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Book extends Publication {
    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    public Book() {
    }

    public Book(double price, int quantity, String description, String title, int copies, String author, String ISBN) {
        super(price, quantity, description, title, copies);
        this.author = author;
        this.isbn = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}