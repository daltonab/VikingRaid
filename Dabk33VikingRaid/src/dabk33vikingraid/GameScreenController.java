 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dalto
 */
public class GameScreenController extends Switchable implements Initializable {
    
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Label lives;
    
    @FXML
    private Label gold;
    
    private Stage stage;
    private int lastArrow = 0;
    private KeyFrame keyFrame;
    ArrayList<Enemy> enemies = new ArrayList<>();
    private Random rand = new Random();
    private int numLives;
    private int numGold;
    public GameModel game;
    private ImageView viking;
    private Label gameOver;
    private Button newGame;
    private Button saveScore;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void ready(Stage stage){
        game = new GameModel();
        game.start(stage, this);
        numLives = 3;
        lives.setText(""+numLives); 
        numGold = 0;
        gold.setText(""+numGold);
        
//        try {
//            File file = new File("Vitaeveris.m4a");
//            Media media = new Media(file.toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            //mediaView.setMediaPlayer(mediaPlayer);
////            mediaPlayer.setOnReady(() -> {
////                handleReady();
////            });
////            mediaPlayer.setOnEndOfMedia(() -> {
////                handleEndOfMedia();
////            });
//            //mediaPlayer.setAudioSpectrumNumBands(numBands);
//            //mediaPlayer.setAudioSpectrumInterval(updateInterval);
//            //mediaPlayer.setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
//                //handleUpdate(timestamp, duration, magnitudes, phases);
//            //});
//            //mediaPlayer.setAutoPlay(true);
//            mediaPlayer.play();
//            //filePathText.setText(file.getPath());
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                game.end();
            }
                        
        });
    }
    
    public void makeEnemies(){
        for(int i = 0; i<7; ++i){    
            Enemy enemy = new Enemy();
            enemies.add(enemy);
            //if(i!=7){    
                Image image = new Image(this.getClass().getResourceAsStream("EnemyArcher.png"));
                ImageView imageView = new ImageView(image);
                imageView.setX(enemy.getX());
                imageView.setY(enemy.setY());
                imageView.setFitHeight(70);
                imageView.setFitWidth(55);
                root.getChildren().add(imageView);
                //System.out.println(enemy.getNumEnemies());
            //}
        }
    }
    
    public ImageView makeArrow(){
        int who = rand.nextInt(6)+1;
        Image image = new Image(this.getClass().getResourceAsStream("Arrow.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(715);
        if(who != lastArrow){
            imageView.setY(enemies.get(who).getY() - 50);    
        }
        else{
            if(who != 6){
                imageView.setY(enemies.get(who+1).getY() - 50);
            }else{
                imageView.setY(enemies.get(who-1).getY()-50);
            }
        }
        root.getChildren().add(imageView);
        lastArrow = who;
        return imageView;
    }
    
//    public double playerX(){
//        //System.out.println(viking.getY());
//        return viking.getX();
//    }
//    
//    public double playerY(){
//        return viking.getY();
//    }
    
    public void hit(){
        // System.out.println("hit");
        --numLives;
        lives.setText(""+numLives);
        if(numLives == 0){
            game.end();
            viking.setRotate(270); 
            gameOver = new Label();
            gameOver.setText("GAME OVER");
            gameOver.setTranslateX(380);
            gameOver.setTranslateY(200);
            gameOver.setScaleX(7);
            gameOver.setScaleY(9);
            root.getChildren().add(gameOver);
            newGame = new Button("New Game?");
            newGame.setLayoutX(380);
            newGame.setLayoutY(300);
            newGame.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    end();
                }
            });
            root.getChildren().add(newGame);
            saveScore = new Button("Save Score");
            saveScore.setLayoutX(383);
            saveScore.setLayoutY(340);
            saveScore.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showSaveDialog(stage);
                    if (file != null) {
                        PrintWriter out = null;
                        try {
                            int jsonString = numGold;
                            out = new PrintWriter(file.getPath());
                            out.print(jsonString);
                            out.close();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            out.close();
                        }
                    }
                }
            });
            root.getChildren().add(saveScore);
           
        }
    }
    
    public ImageView makePlayer(){
        Image image = new Image(this.getClass().getResourceAsStream("8bitViking.png"));
        viking = new ImageView(image);
        viking.setFitHeight(70);
        viking.setFitWidth(55);
        viking.setX(160);
        viking.setY(200);
        root.getChildren().add(viking);
        return viking;
    }
    
    public void end(){
        root.getChildren().remove(gameOver);
        root.getChildren().remove(newGame);
        root.getChildren().remove(saveScore);
        viking.setRotate(1);
        numLives = 3;
        numGold = 0;
        gold.setText(""+numGold);
        lives.setText(""+numLives);
        game.restart();
//        Switchable.switchTo("FXMLDocument");
//        FXMLDocumentController controller = (FXMLDocumentController)getControllerByName("FXMLDocument");
//        if(controller != null){
//            controller.ready(stage);
//        }
    }
    
    public ImageView makeCoin(){
        int who = rand.nextInt(6)+1;
        Image image = new Image(this.getClass().getResourceAsStream("goldCoin.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(715);
        
        imageView.setY(enemies.get(who).getY());    
        
        root.getChildren().add(imageView);
        return imageView;
    }
    
    public void getGold(){
        ++numGold;
        gold.setText(""+numGold);
    }
    
    public void getHealth(){
        ++numLives;
        lives.setText(""+numLives);
    }
    
    public ImageView makeHealth(){
        int who = rand.nextInt(6)+1;
        Image image = new Image(this.getClass().getResourceAsStream("extraLife.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(715);
        
        imageView.setY(enemies.get(who).getY()); 
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        
        root.getChildren().add(imageView);
        return imageView;
    }
}
