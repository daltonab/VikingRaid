/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;

import java.util.Random;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Dalto
 */
public class Arrow extends Thread{
    static public int numberOfArrows = 0;
    private Timeline timeline;
    private Random rand;
    private GameScreenController controller;
    private AnchorPane root;
    private boolean stop = false;
    private double time;
    private Player player;
    private int counter = 0;
   
    public Arrow(GameScreenController controller, Player player){
        this.controller = controller;
        this.player = player;
    }
    
    public void createTimeline(){
        ++numberOfArrows;
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                ImageView arrow = controller.makeArrow();
                KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (ActionEvent)->{
                        arrow.setX(arrow.getX()-3);
                        if(arrow.getX() == player.getX()+24){
                            //System.out.println("matchX");
                            //System.out.println(arrow.getX()+" and "+player.getX());
                            if(arrow.getY() <= player.getY()+75 && arrow.getY() >= player.getY()){
                                controller.hit();
                                arrow.setVisible(false); 
                            }
                        }
//                        else{
//                            System.out.println(arrow.getX()+" and "+player.getX());
//                        }
                });
                timeline = new Timeline(keyFrame);
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            }        
        });
    }
    
    public int getNumberOfArrows(){
        return numberOfArrows;
    }

    @Override
    public void run(){
        time = System.currentTimeMillis();
        //System.out.println(time);
        while(!stop){
            if((System.currentTimeMillis()-time)/1000 >= .3){
                //System.out.println("fire");
                createTimeline();
                time = System.currentTimeMillis();
                ++counter;
                if(counter == 20){
                    //System.out.println("This ran");
                    Pickup pickup = new Pickup(controller, player);
                    counter = 0;
                    
                }
            }else{
                //System.out.println((time - System.currentTimeMillis())/1000);
            }
        } 
    }

    public void end(){
        stop = true;
    }
    
    public void restart(){
        stop = false;
    }  

}

