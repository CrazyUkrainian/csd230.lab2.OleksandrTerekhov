package csd230.lab2.pojos;

/**
 * DTO for {@link jpa_04.entities.BookEntity}
 */
/**
 * DTO for {@link jpa_04.entities.Book}
 */
public class Book extends Publication {
    private String author;
    private String ISBN;
//    private String ISBN_10;
    public Book() {}

    public Book(double price, int quantity, String description, Cart cart, String title, int copies, String author, String ISBN) {
        super(price, quantity, description, cart, title, copies);
        this.author = author;
        this.ISBN = ISBN;
    }

}
