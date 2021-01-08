package com.plotarmor.game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall extends Actor {

    private Image image;

    public Wall(int x, int y) {
        super(x, y);
        
        initWall();
    }
    
    private void initWall() {
        
        ImageIcon iicon = new ImageIcon("src/resources/wall.jpg");
        image = iicon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        setImage(image);
    }
}
