/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private final Discography discography;
    
    public AddAlbumView(Discography discography) {
        this.discography = discography;
    }
    
    public Parent getLayout() {
        GridPane addAlbumView = new GridPane();
        addAlbumView.setPadding(new Insets(20, 20, 20, 20));
        addAlbumView.setVgap(10);
        addAlbumView.setHgap(10);
        
        Label title = new Label("Album Title");
        title.setMinWidth(75);
        
        Label band = new Label("Band");
        Label actionMsg = new Label("");
        actionMsg.getStyleClass().add("error");
        
        TextField titleText = new TextField();
        titleText.setMinWidth(300);
        
        ComboBox<Band> bands = new ComboBox<>();
        bands.setMinWidth(300);
        Set<Band> bandSet = this.discography.getBands();
        ArrayList<Band> bandList = new ArrayList<>();
        
        // Convert the set to a list so it can be sorted
        bandList.addAll(bandSet);
        Collections.sort(bandList, Comparator.comparing(Band::getName));
        bands.getItems().addAll(bandList);
        
        Button submit = new Button("Submit");
        Button reset = new Button("Clear");
        
        submit.setOnAction((e) -> {
            
            String albumTitle = titleText.getText().trim();
            Band newBand = bands.getSelectionModel().getSelectedItem();
            
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
            Album newAlbum = new Album(albumTitle, newBand);
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
        addAlbumView.add(submit, 1, 2);
        addAlbumView.add(reset, 2, 2);
        addAlbumView.add(actionMsg, 0, 3, 3, 1);
        
        return addAlbumView;
    }
}
