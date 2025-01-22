package csd230.lab2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class DiscMag extends Publication {
    @Column(name = "has_disc", nullable = true)
    private boolean hasDisc;

    public boolean getHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    public DiscMag() {
        hasDisc=false;
    }

    public DiscMag(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }
}