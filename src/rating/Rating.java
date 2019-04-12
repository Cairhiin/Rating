/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import rating.domain.Discography;
import rating.io.LoadData;
import rating.io.SaveData;
import rating.ui.AddAlbumView;
import rating.ui.AddBandView;

/**
 *
 * @author Blackstorm
 */
public class Rating extends Application {

    private Discography discography;
    private AddBandView addBandLayout;
    private AddAlbumView addAlbumLayout;
    
    @Override
    public void start(Stage window) throws IOException {
        this.discography = new Discography();
        this.addBandLayout = new AddBandView(this.discography);
        this.addAlbumLayout = new AddAlbumView(this.discography);
        
        BorderPane layout = new BorderPane();
        MenuBar menubar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu discoMenu = new Menu("Discography");
        Menu helpMenu = new Menu("Help");
        
        menubar.getMenus().addAll(fileMenu, discoMenu, helpMenu);
        layout.setTop(menubar);
        layout.setCenter(addBandLayout.getLayout());
        
        MenuItem fileLoad = new MenuItem("Load File");
        MenuItem fileSave = new MenuItem("Save");
        fileMenu.getItems().addAll(fileLoad, fileSave);
        
        MenuItem addBand = new MenuItem("Add New Band");
        MenuItem addAlbum = new MenuItem("Add New Album");
        discoMenu.getItems().addAll(addBand, addAlbum);
        
        fileLoad.setOnAction((e) -> {
          LoadData load;
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Select Discography file");
          fileChooser.getExtensionFilters().add(
            new ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(window);
            if(selectedFile == null) {
                load = new LoadData();
            } else {
                load = new LoadData(selectedFile);
              try {
                  load.LoadDiscographyData();
              } catch (IOException ex) {
                  Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
            
            discography = load.getDiscography();
            addBandLayout.setDiscography(discography);
            addAlbumLayout.setDiscography(discography);
        });
        
        fileSave.setOnAction((ActionEvent e) -> {
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Save Discography file");
          fileChooser.getExtensionFilters().add(
            new ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showSaveDialog(window);
            if(selectedFile != null) {
                SaveData save = new SaveData(discography, selectedFile);
              try {
                  save.SaveDiscographyData();
              } catch (IOException ex) {
                  Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
        });
        
        addAlbum.setOnAction((e) -> {
            layout.setCenter(addAlbumLayout.getLayout());
        });
        
        addBand.setOnAction((e) -> {
            layout.setCenter(addBandLayout.getLayout());
        });
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("/rating/ui/RatingStyleSheet.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args) throws IOException {
        launch(Rating.class);
    }
    
}
