/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Dalto
 */
public class Pickup {
    static private int healthCounter = 0;
    
    public Pickup(){
        
    }
    
    public Pickup(GameScreenController controller, Player player){
        if(healthCounter == 5){
            HealthPack health = new HealthPack(controller, player);
            healthCounter = 0;
        }else{
            ++healthCounter;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    ImageView coin = controller.makeCoin();
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (ActionEvent)->{
                            coin.setX(coin.getX()-1);
                            if(coin.getX() <= player.getX()+35 && coin.getX() >= player.getX()-15){

                                if(coin.getY() <= player.getY()+75 && coin.getY() >= player.getY()){
                                    controller.getGold();
                                    coin.setVisible(false); 
                                    coin.setX(coin.getX()-100);
                                }
                            }
                    });
                    Timeline timeline = new Timeline(keyFrame);
                    timeline.setCycleCount(Animation.INDEFINITE);
                    timeline.play();
                }        
            });
        }
    }
}
