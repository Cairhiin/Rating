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
import rating.domain.Band;
import rating.domain.Discography;

/**
 *
 * @author Blackstorm
 */
public class AddAlbumLayout {
    private final Discography discography;
    
    public AddAlbumLayout(Discography discography) {
        this.discography = discography;
    }
    
    public Parent getLayout() {
        GridPane addAlbum = new GridPane();
        addAlbum.setPadding(new Insets(20, 20, 20, 20));
        addAlbum.setVgap(10);
        addAlbum.setHgap(10);
        
        Label title = new Label("Album Title");
        Label band = new Label("Band");
        Label actionMsg = new Label("");
        title.setMinWidth(75);
        
        TextField titleText = new TextField();
        titleText.setMinWidth(300);
        
        ComboBox bands = new ComboBox();
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
            String bandName = (String) bands.getValue();
        });
        
        reset.setOnAction((e) -> {
            titleText.setText("");
            bands.setValue("");
        });
        
        addAlbum.add(title, 0, 0);
        addAlbum.add(titleText, 1, 0, 2, 1);
        addAlbum.add(band, 0, 1);
        addAlbum.add(bands, 1, 1, 2, 1);
        addAlbum.add(submit, 1, 2);
        addAlbum.add(reset, 2, 2);
        addAlbum.add(actionMsg, 0, 3, 2, 1);
        
        return addAlbum;
    }
}
