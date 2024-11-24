/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author USER
 */
public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp , KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        
    }
    public void setDefaultValue(){
        
        x = 100;
        y = 100;
        speed = 4;
        
    }
    public void update(){
        if(keyH.upPressed == true){
            y -= speed;
        }
        else if(keyH.downPressed == true){
            y += speed;
        }
        else if(keyH.rightPressed == true){
            x += speed;
        }
        else if(keyH.leftPressed == true){
            x -= speed;
        }
    }
    public void draw(Graphics2D g2){
           g2.setColor(Color.white);
        //draw a rectangle and fills it with a specified color
        //coordinate(100,100)
        //size rectangle is 48 x 48
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
