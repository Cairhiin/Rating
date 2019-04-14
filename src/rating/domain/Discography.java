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

    private final List<Band> discography;

    public Discography() {
        this.discography = new ArrayList<>();
    }

    public boolean addBand(Band band) {
        if (checkIfBandAlreadyIsInList(band)) {
            return false;
        }
        this.discography.add(band);
        return true;
    }

    public List<Band> getBands() {
        return this.discography;
    }

    public List<Band> getDiscography() {
        return this.discography;
    }

    public void addAlbum(Album album, Band band) {

        if (!checkIfBandAlreadyIsInList(band)) {
            band.addAlbum(album);
            this.discography.add(band);
        } else {
            for (Band currentBand : discography) {
                if (currentBand.equals(band)) {
                    currentBand.addAlbum(album);
                }
            }
        }
    }

    private boolean checkIfBandAlreadyIsInList(Band band) {
        return this.discography.contains(band);
    }
}
