/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.domain;

import java.util.Objects;

/**
 *
 * @author Blackstorm
 */
public class Album implements Rateable {
    private String title;
    private Band band;
    private double rating;
    
    public Album(String title, Band band) {
        this(title, band, -1);
    }
    
    public Album(String title, Band band, double rating) {
        this.rating = rating;
        this.title = title;
        this.band = band;
    }
    
    @Override
    public void rate(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public Band getBand() {
        return band;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.band);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.band, other.band)) {
            return false;
        }
        return true;
    }
}
