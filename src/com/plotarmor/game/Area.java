package com.plotarmor.game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Area extends Actor {

    public Area(int x, int y) {
        super(x, y);
        
        initArea();
    }
    
    private void initArea() {

        ImageIcon iicon = new ImageIcon("src/resources/target.jpg");
        Image image = iicon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        setImage(image);
    }
}
