/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import rating.domain.Album;
import rating.domain.Band;
import rating.domain.Discography;

/**
 *
 * @author Blackstorm
 */
public class AddAlbumView  {
    private Discography discography;
    
    public AddAlbumView() {
        this.discography = new Discography();
    }
    
    public Parent getLayout() {
        GridPane addAlbumView = new GridPane();
        addAlbumView.setId("main");
        
        Label title = new Label("Album Title");
        title.setMinWidth(75);
        
        Label band = new Label("Band");
        Label actionMsg = new Label("");
        actionMsg.getStyleClass().add("error");
        
        TextField titleText = new TextField();
        titleText.getStyleClass().add("input-field");
        
        ComboBox<Band> bands = new ComboBox<>();
        bands.getStyleClass().add("input-field");
        List<Band> bandList = this.discography.getBands();
        Collections.sort(bandList, Comparator.comparing(Band::getName));
        bands.getItems().addAll(bandList);
        
        Label ratingLabel = new Label("Rating");
        Slider rating = new Slider(0, 5, 0);
        rating.getStyleClass().add("rating-slider");
        
        Button submit = new Button("Submit");
        Button reset = new Button("Clear");
        
        submit.setOnAction((e) -> {
            
            String albumTitle = titleText.getText().trim();
            Band newBand = bands.getSelectionModel().getSelectedItem();
            double albumRating = rating.getValue();
            
            // Form validation
            if (albumTitle.equals("")) {
                actionMsg.setText("You have to enter an album title");
                return;
            }
            
            if (newBand == null) {
                actionMsg.setText("You have to select a band");
                return;
            }
                   
            // Creating the new Album and adding it to the Discography
            Album newAlbum;
            if (albumRating != 0) {
                newAlbum = new Album(albumTitle, newBand, albumRating);
            } else {
                newAlbum = new Album(albumTitle, newBand);
            }
            discography.addAlbum(newAlbum, newBand);
            actionMsg.getStyleClass().add("success");
            actionMsg.setText("Album successfully added!");
            
            // Clearing form
            titleText.setText("");
            bands.setValue(null);
            
            System.out.println(discography.getDiscography());
        });
        
        reset.setOnAction((e) -> {
            titleText.setText("");
            bands.setValue(null);
            actionMsg.setText("");
        });
        
        addAlbumView.add(title, 0, 0);
        addAlbumView.add(titleText, 1, 0, 2, 1);
        addAlbumView.add(band, 0, 1);
        addAlbumView.add(bands, 1, 1, 2, 1);
        addAlbumView.add(ratingLabel, 0, 2);
        addAlbumView.add(rating, 1, 2, 2, 1);
        addAlbumView.add(submit, 1, 3);
        addAlbumView.add(reset, 2, 3);
        addAlbumView.add(actionMsg, 0, 4, 3, 1);
        
        return addAlbumView;
    }

    public void setDiscography(Discography discography) {
        this.discography = discography;
    }
    
}
