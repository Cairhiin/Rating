/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import rating.domain.Discography;
import rating.ui.AddAlbumLayout;
import rating.ui.AddBandLayout;

/**
 *
 * @author Blackstorm
 */
public class Rating extends Application {

    @Override
    public void start(Stage window) {
        Discography discography = new Discography();
        AddBandLayout addBandLayout = new AddBandLayout(discography);
        AddAlbumLayout addAlbumLayout = new AddAlbumLayout(discography);
        
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
        
        addAlbum.setOnAction((e) -> {
            layout.setCenter(addAlbumLayout.getLayout());
        });
        
        addBand.setOnAction((e) -> {
            layout.setCenter(addBandLayout.getLayout());
        });
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(Rating.class);
    }
    
}
