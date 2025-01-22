package csd230.lab2.pojos;
import java.io.Serializable;
import java.util.Objects;
/**
 * DTO for {@link jpa_04.entities.PublicationEntity}
 */
/**
 * DTO for {@link jpa_04.entities.Publication}
 */
/**
 * DTO for {@link jpa_04.entities.Publication}
 */
public abstract class Publication extends CartItem implements Serializable {
    private String title;
    private int copies;

    public Publication() {
    }

    public Publication(double price, int quantity, String description, Cart cart, String title, int copies) {
        super(price, quantity, description, cart);
        this.title = title;
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", copies=" + copies +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication that)) return false;
        if (!super.equals(o)) return false;
        return getCopies() == that.getCopies() && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getCopies());
    }

    public void sellItem() {
        if(copies>0) copies--;
    }

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
