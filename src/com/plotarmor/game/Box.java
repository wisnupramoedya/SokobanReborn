package com.plotarmor.game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Box extends Actor {

    public Box(int x, int y) {
        super(x, y);
        
        initBox();
    }
    
    private void initBox() {
        ImageIcon iicon = new ImageIcon("src/resources/box.png");
        Image image = iicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        setImage(image);
    }

    public void move(int x, int y) {
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
}
