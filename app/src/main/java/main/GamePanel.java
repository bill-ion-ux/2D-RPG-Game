
package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int OriginalTileSize = 16; //16 x 16
    final int scale = 3 ;
    public int tileSize = scale * OriginalTileSize; //48 x 48 tile
    final int maxScreenCol = 16; //width panel 16 column
    final int maxScreenRow = 12;  // height panel is 12 row
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    //FPS
    
    int FPS = 60;
    
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    
    public GamePanel(){//create a constructor class
        //set the size of the panel
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        //gamePanel can be "focused" to receive key input
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // once we start the game thread this method will be called
    public void run() { 
        double drawInterval = 1000000000/FPS; //o.166 seconds
        double nextDrawTime = System.nanoTime() - drawInterval;
        
        while(gameThread != null){
            //how to check the time betqeen update and repaint
//            long checkTime = System.nanoTime();
//            System.out.println("current time " + checkTime );
            //1 billion nano = 1 seconds
            
            //1. UPDATE: update information suzh as character position
            
            update();
            
            //2. DRAW : draw the screen with the updated information
            // we need to sett the time between the update and repaint
            repaint();
            
           
            try {
                 double remainingTime = nextDrawTime - System.nanoTime();
                 remainingTime = remainingTime / 1000000 ;
                 
                 if(remainingTime < 0){
                     remainingTime = 0;
                 }
                 // pauase the game loop until the time is over
                Thread.sleep((long)remainingTime);
                //once the sleep is over we add next interval
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       
    }
    
    public void update(){
        player.update();
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        //super used to call superclass method ( parents object )
        super.paintComponent(g);
       // Graphics2D class extends the Graphics class to provide more
       // sophiscated control over geometry, coordinate transformation
       // color management and text layout
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        //dispose of this graphics context and release any system
        // thath it is using
        g2.dispose();
    }
}
