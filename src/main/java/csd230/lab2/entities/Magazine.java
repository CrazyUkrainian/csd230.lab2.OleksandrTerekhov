package csd230.lab2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Magazine extends Publication {
    @Column(name = "order_qty", nullable = true)
    private int orderQty;

    @Column(name = "curr_issue")
    private Date currIssue;

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrIssue() {
        return currIssue;
    }

    public void setCurrIssue(Date currIssue) {
        this.currIssue = currIssue;
    }

    public Magazine() {
    }

    public Magazine(double price, int quantity, String description, String title, int copies, int orderQty, Date currIssue) {
        super(price, quantity, description, title, copies);
        this.orderQty = orderQty;
        this.currIssue = currIssue;
    }
}