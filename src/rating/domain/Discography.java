/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Blackstorm
 */
public class Discography {

    private final Map<Band, String> discography;

    public Discography() {
        this.discography = new HashMap<>();
    }

    public boolean addBand(Band band) {
        if (checkIfBandAlreadyIsInList(band)) {
            return false;
        }
        this.discography.put(band, band.getName());
        return true;
    }
    
    public List<Band> getBands() {
        return new ArrayList<>(this.discography.keySet());
    }
    
    public Map<Band, String> getDiscography() {
        return this.discography;
    }

    public void addAlbum(Album album, Band band) {

        if (!checkIfBandAlreadyIsInList(band)) {
            this.discography.put(band, band.getName());
        }

        band.addAlbum(album);
    }

    private boolean checkIfBandAlreadyIsInList(Band band) {
        return this.discography.containsKey(band);
    }
}
