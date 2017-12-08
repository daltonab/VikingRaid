/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;

import javafx.scene.image.ImageView;

/**
 *
 * @author Dalto
 */
public class Player implements Sprite{
    private ImageView imageView;
    
    public Player(ImageView imageView){
        this.imageView = imageView;
    }
    
    @Override
    public double getX(){
        return imageView.getX();
    }
    
    @Override
    public double getY(){
        return imageView.getY(); 
    }
}
