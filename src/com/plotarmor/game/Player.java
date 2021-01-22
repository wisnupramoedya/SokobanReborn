package com.plotarmor.game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends Actor {

    public Player(int x, int y, String playerImgUrl) {
        super(x, y);

        initPlayer(playerImgUrl);
    }

    private void initPlayer(String playerImgUrl) {
        ImageIcon iicon = new ImageIcon(playerImgUrl);
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
