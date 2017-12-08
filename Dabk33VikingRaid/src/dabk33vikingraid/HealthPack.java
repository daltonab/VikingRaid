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
public class HealthPack extends Pickup{

    public HealthPack(GameScreenController controller, Player player){
        Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    ImageView health = controller.makeHealth();
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (ActionEvent)->{
                            health.setX(health.getX()-1);
                            if(health.getX() <= player.getX()+35 && health.getX() >= player.getX()-15){

                                if(health.getY() <= player.getY()+75 && health.getY() >= player.getY()){
                                    controller.getHealth();
                                    health.setVisible(false); 
                                    health.setX(health.getX()-100);
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
