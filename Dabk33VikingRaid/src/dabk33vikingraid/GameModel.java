/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;


import java.util.ArrayList;
import static java.util.Locale.filter;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Dalto
 */
public class GameModel {
    
    public Stage stage;
    private Random rand = new Random();
    static double time;
    private Boolean stop = false;
    private Arrow arrow;
    private GameScreenController controller;
    private Player player;
    private boolean alive = true;

    
    public void start(Stage stage, GameScreenController controller){
        //System.out.println("This got ran");
            this.stage = stage;
            this.controller = controller;
            time = System.currentTimeMillis();
            controller.makeEnemies();
            ImageView viking = controller.makePlayer();
            player = new Player(viking);
            stage.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event)->{
                if(alive){
                    if("DOWN".equals(event.getCode().toString())){
                        if(viking.getY() < 440){
                            viking.setY(viking.getY() + 20);
                        }
                    }else if("UP".equals(event.getCode().toString())){
                        if(viking.getY() > 0){
                            viking.setY(viking.getY() - 20);
                        }
                    }
                }
            });
            
            
            
           arrow = new Arrow(controller, player);
           arrow.start();
           //Timer timer = new Timer(true);
           //timer.schedule(new Arrow(), 0, 1000);
//            Runnable frequency = new Runnable(){
//                @Override
//                public void run() {
//                    while(stop != true){
//                        System.out.println("Twang");
//                    }
//                    stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
//                        @Override
//                        public void handle(WindowEvent event) {
//                            stop = true;
//                        }
//                        
//                    });
//                }
//                
//            };
//            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//            executor.scheduleAtFixedRate(frequency, 1, 1, TimeUnit.SECONDS);
    }
    
    public void restart(){
        alive = true;
        arrow = new Arrow(controller, player);
        arrow.start();
    }
    
    public void end(){
        alive = false;
        
        arrow.end();
        //stage.removeEventFilter(KeyEvent.KEY_PRESSED,);
    }
}

