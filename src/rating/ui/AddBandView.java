/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.ui;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import rating.domain.Band;
import rating.domain.Discography;

/**
 *
 * @author Blackstorm
 */
public class AddBandView {
    
    private final Discography discography;
    
    public AddBandView(Discography discography) {
        this.discography = discography;
    }
    
    public Parent getLayout() {
        GridPane addBandView = new GridPane();
        addBandView.setPadding(new Insets(20, 20, 20, 20));
        addBandView.setVgap(10);
        addBandView.setHgap(10);
        
        Label name = new Label("Band name");
        name.setMinWidth(75);
        
        Label genre = new Label("Genres");
        Label actionMsg = new Label("");
        actionMsg.getStyleClass().add("error");
        
        TextField nameText = new TextField();
        nameText.setMinWidth(300);
        TextField genreText = new TextField();
        genreText.setMinWidth(300);
        Button submit = new Button("Submit");
        Button reset = new Button("Clear");
        
        addBandView.add(name, 0, 0);
        addBandView.add(genre, 0, 1);
        addBandView.add(nameText, 1, 0, 2, 1);
        addBandView.add(genreText, 1, 1, 2, 1);
        addBandView.add(submit, 1, 2);
        addBandView.add(reset, 2, 2);
        addBandView.add(actionMsg, 0, 3, 3, 1);
        
        reset.setOnAction((e) -> {
           nameText.setText("");
           genreText.setText("");
        });
        
        submit.setOnAction((e) -> {
            String bandname = nameText.getText().trim();
            
            
            // Form validation
            if (bandname.equals("")) {
                actionMsg.setText("You have to enter a band name");
                return;
            }
            
            if (genreText.getText().trim().equals("")) {
                actionMsg.setText("You have to add at least one genre");
                return;
            }
            
            // Create an array of the genre input
            String[] genres = genreText.getText().split(",");
            ArrayList<String> genreList = new ArrayList<>();
            
            for (String genreName : genres) {
                // Remove empty spaces and transform the genre to lower case
                genreList.add(genreName.trim().toLowerCase());
            }
            
            // Create the band and add it to the discography
            Band band = new Band(bandname, genreList);
            
            // Check if band exists - same band names can be used but must have different genres
            if (discography.addBand(band)) {
                actionMsg.getStyleClass().add("success");
                actionMsg.setText("Band successfully added!");
            } else {
                actionMsg.getStyleClass().add("error");
                actionMsg.setText("Band was already in the list!");
            }
            
            // Clear the form
            nameText.setText("");
            genreText.setText("");
            System.out.println(discography.getBands());
        });
        
        return addBandView;
    }
}
