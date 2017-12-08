/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Dalto
 */
public class FXMLDocumentController extends Switchable implements Initializable {
    
    private Stage stage;
    
    @FXML
    private Button play; 
    
    @FXML
    private Button about;
    
    @FXML
    private Button quit;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Label title;
    
    @FXML
    private Button load;
    //private String song = "Vitaeveris.m4a";
    
    public void ready(Stage stage) {
        this.stage = stage;
//        Media media = new Media(this.getClass().getResource(song).toExternalForm());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        mediaPlayer.setAudioSpectrumNumBands(4);
//        mediaPlayer.setAudioSpectrumInterval(0.01);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleAbout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Viking Raid");
        alert.setContentText("This application was developed by Dalton Burge for a final project in CS3330 at the University of Missouri.");
        
        TextArea textArea = new TextArea("This project is my first step into Game Development. All you have to do is move up and down to dodge the arrows and gather the loot.");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }

    @FXML
    public void handlePlay(ActionEvent event){
        Switchable.switchTo("GameScreen");
        GameScreenController gameScreenController = (GameScreenController)getControllerByName("GameScreen");
        if(gameScreenController != null){
            //System.out.println("ran");
            gameScreenController.ready(stage);
        }
    }
    
    @FXML
    public void handleQuit(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    public void handleLoad(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                FileReader fileReader = new FileReader(file.getPath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String json = "";
                String line = null;
                
                while((line = bufferedReader.readLine()) != null){
                    json += line;
                }
                bufferedReader.close();
                fileReader.close();
                //person.initFromJsonString(json);
                //fillFormFromPerson(person);
                Label label = new Label();
                label.setText("Gold Coins Recieved\n" + "               " + json);
                label.setTranslateX(155);
                label.setTranslateY(80);
                label.setScaleX(1.5);
                label.setScaleY(1.5);
                root.getChildren().add(label);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
