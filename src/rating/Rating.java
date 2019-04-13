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
import rating.ui.ListBandsView;

/**
 *
 * @author Blackstorm
 */
public class Rating extends Application {

    private Discography discography;
    private AddBandView addBandView;
    private AddAlbumView addAlbumView;
    private ListBandsView listBandsView;
    private File file;
    private Stage window;
    private FileChooser fileChooser;

    @Override
    public void start(Stage window) throws IOException {
        this.window = window;
        this.discography = new Discography();
        this.addBandView = new AddBandView();
        this.addAlbumView = new AddAlbumView();
        this.listBandsView = new ListBandsView();
        
        this.fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("Text Files", "*.txt"));

        BorderPane layout = new BorderPane();
        MenuBar menubar = new MenuBar();
        menubar.getStyleClass().add("main-menu");

        Menu fileMenu = new Menu("File");
        fileMenu.getStyleClass().add("menu-item");
        Menu discoMenu = new Menu("Discography");
        discoMenu.getStyleClass().add("menu-item");
        Menu helpMenu = new Menu("Help");
        helpMenu.getStyleClass().add("menu-item");

        menubar.getMenus().addAll(fileMenu, discoMenu, helpMenu);
        layout.setTop(menubar);
        layout.setCenter(addBandView.getLayout());

        MenuItem fileLoad = new MenuItem("Load File");
        MenuItem fileSave = new MenuItem("Save");
        fileSave.setDisable(true);
        MenuItem fileSaveAs = new MenuItem("Save As...");
        MenuItem fileExit = new MenuItem("Exit");
        fileMenu.getItems().addAll(fileLoad, fileSave, fileSaveAs, fileExit);

        MenuItem addBand = new MenuItem("Add New Band");
        MenuItem addAlbum = new MenuItem("Add New Album");
        MenuItem listBands = new MenuItem("List Bands");
        listBands.setDisable(true);
        MenuItem listAlbums = new MenuItem("List Albums");
        listAlbums.setDisable(true);
        discoMenu.getItems().addAll(addBand, addAlbum, listBands, listAlbums);

        fileLoad.setOnAction((e) -> {
            if (this.handleLoadEvent()) {
                fileSave.setDisable(false);
                listAlbums.setDisable(false);
                listBands.setDisable(false);
            }
        });

        fileSaveAs.setOnAction((e) -> {
            this.handleSaveAsEvent();
        });

        fileSave.setOnAction((e) -> {
            SaveData save = new SaveData(discography, this.file);
            try {
                save.SaveDiscographyData();
            } catch (IOException ex) {
                Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        fileExit.setOnAction((e) -> {
            window.close();
        });

        addAlbum.setOnAction((e) -> {
            addAlbumView.setDiscography(discography);
            layout.setCenter(addAlbumView.getLayout());
        });

        addBand.setOnAction((e) -> {
            addBandView.setDiscography(discography);
            layout.setCenter(addBandView.getLayout());
        });

        listBands.setOnAction((e) -> {
            listBandsView.setDiscography(discography);
            layout.setCenter(listBandsView.getLayout());
        });
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("/rating/ui/RatingStyleSheet.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }

    private boolean handleLoadEvent() {
        this.fileChooser.setTitle("Select Discography file");     
        this.file = fileChooser.showOpenDialog(this.window);
        if (file != null) {
            LoadData load = new LoadData(file);
            try {
                load.LoadDiscographyData();

                // load the discography and update the views
                discography = load.getDiscography();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        return false;
    }

    private boolean handleSaveAsEvent() {
        this.fileChooser.setTitle("Save Discography file");
        this.file = fileChooser.showSaveDialog(window);
        if (file != null) {
            SaveData save = new SaveData(discography, file);
            try {
                save.SaveDiscographyData();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        launch(Rating.class);
    }

}
