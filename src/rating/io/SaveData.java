/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import rating.domain.Album;
import rating.domain.Band;
import rating.domain.Discography;

/**
 *
 * @author Blackstorm
 */
public class SaveData {

    private Discography discography;
    private File file;
    private Map<Band, String> disco;

    public SaveData(Discography discography, File file) {
        this.discography = discography;
        this.file = file;
    }

    public SaveData(Discography discography) {
        this(discography, new File("ratingManagerData.txt"));
    }

    public void SaveDiscographyData() throws IOException {
        PrintWriter fileWriter = new PrintWriter(file);

        this.disco = discography.getDiscography();

        for (Band band : this.disco.keySet()) {
            List<Album> albums = band.getAlbums();
            albums.stream()
                    .forEach(album -> {
                        StringBuilder data = new StringBuilder();
                        data.append(band.getName());
                        data.append(";");
                        data.append(album.getTitle());
                        data.append(";");
                        data.append(album.getRating());
                        data.append(";");
                        String genres = String.join(",", band.getGenres());
                        data.append(genres);
                        data.append(";");
                        data.append(band.getDescription());
                        fileWriter.println(data);
                    });
        }

        fileWriter.close();
    }
}
