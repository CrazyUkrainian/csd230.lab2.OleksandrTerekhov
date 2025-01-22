package csd230.lab2.pojos;

import java.util.Date;
import java.util.Objects;

/**
 * @author fcarella
 */
/**
 * DTO for {@link jpa_04.entities.DiscMag}
 */
public class DiscMag extends Magazine {

    private boolean hasDisc;

    public DiscMag() {
    }

    public DiscMag(double price, int quantity, String description, Cart cart, String title, int copies, int orderQty, Date currIssue, boolean hasDisc) {
        super(price, quantity, description, cart, title, copies, orderQty, currIssue);
        this.hasDisc = hasDisc;
    }

    @Override
    public String toString() {
        return "DiscMag{" +
                "hasDisc=" + hasDisc +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscMag discMag)) return false;
        if (!super.equals(o)) return false;
        return getHasDisc() == discMag.getHasDisc();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHasDisc());
    }

    public boolean getHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

}