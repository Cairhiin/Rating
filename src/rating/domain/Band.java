/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Blackstorm
 */
public class Band implements Rateable {
    private String name;
    private double rating;
    private List<String> genres;
    private String description;
    private Set<Album> albums;
    
    public Band(String name, List<String> genres, String description) {
        this.name = name;
        this.genres = genres;
        this.description = description;
        this.albums = new HashSet<>();
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
    public void rate(double rating) {
        this.rating = rating;
    }
    
    public double calculateRating() {
        return this.albums.stream()
                .map(album -> album.getRating())
                .reduce(0.0, (sum, rate) -> sum += rate) / albums.size();
    }
    
    public void addAlbum(Album album) {
        this.albums.add(album);
    }
    
    public List<Album> getAlbums() {
        return new ArrayList<>(this.albums);
    }
    
    public String getName() {
        return name;
    }
    
    public void addGenre(String genre) {
        this.genres.add(genre);
    }    

    public double getRating() {
        return rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        String genreString = "(" + String.join(" | ", this.genres) + ")";
        return name + " " + genreString + " ";
    }
}
