package csd230.lab2.pojos;
/**
 * @author fcarella
 */
/**
 * DTO for {@link jpa_04.entities.Ticket}
 */
public class Ticket extends CartItem {
    private String text;
    public Ticket() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void sellItem() {
        System.out.println(getDescription());
    }


}