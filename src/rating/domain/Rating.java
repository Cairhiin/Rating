/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.domain;

/**
 *
 * @author Blackstorm
 */
public class Rating {
    private int rating;
    private String description;
    
    public Rating(int rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
    
}
