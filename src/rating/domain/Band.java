/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Blackstorm
 */
public class Band implements Rateable {
    private String name;
    private List<Rating> ratings;
    private List<String> genres;
    private String description;
    
    public Band(String name, List<String> genres, String description) {
        this.ratings = new ArrayList<>();
        this.name = name;
        this.genres = genres;
        this.description = description;
    }
    
    public Band(String name, List<String> genres) {
        this(name, genres, "This band doesn't have a description yet.");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.genres);
        hash = 79 * hash + Objects.hashCode(this.description);
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
        final Band other = (Band) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.genres, other.genres)) {
            return false;
        }
        return true;
    }
    
    @Override
    public void rate(int rating, String description) {
        this.ratings.add(new Rating(rating, description));
    }

    public String getName() {
        return name;
    }
    
    public void addGenre(String genre) {
        this.genres.add(genre);
    }
    
    @Override
    public int calculateRating() {
        int sum = ratings.stream()
                .mapToInt(x -> x.getRating())
                .reduce(0, (x, y) -> x + y);
    
        return Math.round(sum / ratings.size());
    }

    public List<String> getGenre() {
        return genres;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
