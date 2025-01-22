package csd230.lab2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Ticket extends CartItem {
    public Ticket() {
    }

    public Ticket(double price, int quantity, String description, String text) {
        super(price, quantity, description);
        this.text = text;
    }

    @Column(name = "text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}