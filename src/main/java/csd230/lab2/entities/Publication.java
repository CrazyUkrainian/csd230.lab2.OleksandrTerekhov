package csd230.lab2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Publication extends CartItem {
    public Publication() {
    }

    public Publication(double price, int quantity, String description, String title, int copies) {
        super(price, quantity, description);
        this.title = title;
        this.copies = copies;
    }

    @Column(name = "title")
    private String title;

    @Column(name = "copies", nullable = true)
    private int copies;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }


}