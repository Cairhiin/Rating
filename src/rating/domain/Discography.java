/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Blackstorm
 */
public class Discography {
    private final Map<Band, ArrayList<Album>> discography;
    
    public Discography() {
        this.discography = new HashMap<>();
    }

    public Set<Band> getBands() {
        return this.discography.keySet();
    }
    
    public boolean addBand(Band band) {
        if (checkIfBandAlreadyIsInList(band)) {
            return false;
        }
        this.discography.put(band, new ArrayList<>());
        return true;
    }
    
    public Map<Band, ArrayList<Album>> getDiscography() {
        return discography;
    }
    
    public void addAlbum(Album album, Band band) {
        ArrayList<Album> albums = new ArrayList<>();
        if (!checkIfAlbumAlreadyIsInList(album, band)) {
            
            if (checkIfBandAlreadyIsInList(band)) {
                albums = this.discography.get(band);               
            } 
            
            albums.add(album);
            this.discography.put(band, albums);
        }
    }
    
    private boolean checkIfAlbumAlreadyIsInList(Album album, Band band) {
        if (!checkIfBandAlreadyIsInList(band)) {
            return false;
        }
        
        ArrayList<Album> albums = this.discography.get(band);
        return albums.contains(album);
    }
    
    private boolean checkIfBandAlreadyIsInList(Band band) {
        return this.discography.containsKey(band);
    }
}
