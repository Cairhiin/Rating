/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import rating.domain.Album;
import rating.domain.Band;
import rating.domain.Discography;

/**
 *
 * @author Blackstorm
 */
public class LoadData {

    private Discography discography;
    private Set<Band> bands;
    private File file;

    public LoadData(File file) {
        this.file = file;
        this.bands = new HashSet<>();
        this.discography = new Discography();
    }
    
    public LoadData() {
        this(new File("ratingManagerData.txt"));
    }
    
    public void LoadDiscographyData() throws IOException {
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] parts = line.split(";");

            String bandname = parts[0];
            String title = parts[1];
            double rating = Double.valueOf(parts[2]);
            String genres = parts[3];
            ArrayList<String> genreList = new ArrayList<>(Arrays.asList(genres.split(",")));
            String description = parts[4];
            
            Band band = new Band(bandname, genreList, description);
            Album album = new Album(title, band.getName(), rating);
            
            this.discography.addAlbum(album, band);
            this.bands.add(band);
        }
    }
    
    public List<Band> getBandList() {
        return new ArrayList<>(bands);
    }
    
    public Discography getDiscography() {
        return discography;
    }
}
