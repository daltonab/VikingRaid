/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dabk33vikingraid;

/**
 *
 * @author Dalto
 */
public class Enemy implements Sprite{
    static private int numberOfEnemies = 0;
    private double Y;
    private double X = 735;
    
    public Enemy(){
        ++numberOfEnemies;
    }
    
    public int getNumEnemies(){
        return numberOfEnemies;
    }
    
    public double setY(){
        Y = ((numberOfEnemies * 80)-50);
        return Y;
    }
    
    @Override
    public double getY(){
        return Y;
    }
    
    @Override
    public double getX(){
        return X;
    }
}
