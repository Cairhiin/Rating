/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.ui;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import rating.domain.Band;
import rating.domain.Discography;

/**
 *
 * @author Blackstorm
 */
public class ListBandsView {

    private Discography discography;

    public ListBandsView() {
        this.discography = new Discography();
    }

    public Parent getLayout() {
        ListView<String> bandsList = new ListView<>();
        List<Band> bands = this.discography.getBands();
        List<String> bandNames = bands.stream()
                .map(band -> band.getName())
                .sorted()
                .collect(Collectors.toList());
        bandsList.setItems(FXCollections.observableList(bandNames));
        
        return bandsList;
    }

    public void setDiscography(Discography discography) {
        this.discography = discography;
    }
}
