package com.plotarmor.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import com.plotarmor.DataResources;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int OFFSET = 50;
    private final int SPACE = 40;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    public ArrayList<Wall> walls;
    public ArrayList<Box> boxes;
    public ArrayList<Area> areas;
    
    public Player soko;
    private int w = 0;
    private int h = 0;
    
    public DataResources dataResources;
    
    private boolean isCompleted = false;

    private String level;

    public Board(DataResources dataResources) {
    	this.dataResources = dataResources;
        initBoard();
    }

    public void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public void initWorld() {
        walls = new ArrayList<>();
        boxes = new ArrayList<>();
        areas = new ArrayList<>();

        int x = OFFSET;
        int y = OFFSET;

        Wall wall;
        Box b;
        Area a;
        
        level = dataResources.createLevel();

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            switch (item) {

                case '\n':
                    y += SPACE;

                    if (this.w < x) {
                        this.w = x;
                    }

                    x = OFFSET;
                    break;

                case '#':
                    wall = new Wall(x, y);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case '$':
                    b = new Box(x, y);
                    boxes.add(b);
                    x += SPACE;
                    break;

                case '.':
                    a = new Area(x, y);
                    areas.add(a);
                    x += SPACE;
                    break;

                case '@':
                    soko = new Player(x, y, dataResources.createAvatarUrl());
                    x += SPACE;
                    break;

                case ' ':
                    x += SPACE;
                    break;

                default:
                    break;
            }

            h = y;
        }
    }

    private void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList<Actor> world = new ArrayList<>();

        world.addAll(walls);
        world.addAll(areas);
        world.addAll(boxes);
        world.add(soko);

        for (int i = 0; i < world.size(); i++) {

            Actor item = world.get(i);

            if (item instanceof Player || item instanceof Box) {
                
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (isCompleted) {
                
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        buildWorld(g);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();

            switch (key) {
                
                case KeyEvent.VK_LEFT:
                    
                    if (checkWallCollision(soko,
                            LEFT_COLLISION)) {
                        return;
                    }
                    
                    if (checkboxCollision(LEFT_COLLISION)) {
                        return;
                    }
                    
                    soko.move(-SPACE, 0);
                    
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    
                    if (checkWallCollision(soko, RIGHT_COLLISION)) {
                        return;
                    }
                    
                    if (checkboxCollision(RIGHT_COLLISION)) {
                        return;
                    }
                    
                    soko.move(SPACE, 0);
                    
                    break;
                    
                case KeyEvent.VK_UP:
                    
                    if (checkWallCollision(soko, TOP_COLLISION)) {
                        return;
                    }
                    
                    if (checkboxCollision(TOP_COLLISION)) {
                        return;
                    }
                    
                    soko.move(0, -SPACE);
                    
                    break;
                    
                case KeyEvent.VK_DOWN:
                    
                    if (checkWallCollision(soko, BOTTOM_COLLISION)) {
                        return;
                    }
                    
                    if (checkboxCollision(BOTTOM_COLLISION)) {
                        return;
                    }
                    
                    soko.move(0, SPACE);
                    
                    break;
                    
                case KeyEvent.VK_R:
                    
                    restartLevel();
                    
                    break;
                    
                default:
                    break;
            }

            repaint();
        }
    }

    private boolean checkWallCollision(Actor actor, int type) {

        switch (type) {
            
            case LEFT_COLLISION:
                
                for (int i = 0; i < walls.size(); i++) {
                    
                    Wall wall = walls.get(i);
                    
                    if (actor.isLeftCollision(wall)) {
                        
                        return true;
                    }
                }
                
                return false;
                
            case RIGHT_COLLISION:
                
                for (int i = 0; i < walls.size(); i++) {
                    
                    Wall wall = walls.get(i);
                    
                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }
                
                return false;
                
            case TOP_COLLISION:
                
                for (int i = 0; i < walls.size(); i++) {
                    
                    Wall wall = walls.get(i);
                    
                    if (actor.isTopCollision(wall)) {
                        
                        return true;
                    }
                }
                
                return false;
                
            case BOTTOM_COLLISION:
                
                for (int i = 0; i < walls.size(); i++) {
                    
                    Wall wall = walls.get(i);
                    
                    if (actor.isBottomCollision(wall)) {
                        
                        return true;
                    }
                }
                
                return false;
                
            default:
                break;
        }
        
        return false;
    }

    private boolean checkboxCollision(int type) {

        switch (type) {
            
            case LEFT_COLLISION:
                
                for (int i = 0; i < boxes.size(); i++) {

                    Box box = boxes.get(i);

                    if (soko.isLeftCollision(box)) {

                        for (int j = 0; j < boxes.size(); j++) {
                            
                            Box item = boxes.get(j);
                            
                            if (!box.equals(item)) {
                                
                                if (box.isLeftCollision(item)) {
                                    return true;
                                }
                            }
                            
                            if (checkWallCollision(box, LEFT_COLLISION)) {
                                return true;
                            }
                        }
                        
                        box.move(-SPACE, 0);
                        isCompleted();
                    }
                }
                
                return false;
                
            case RIGHT_COLLISION:
                
                for (int i = 0; i < boxes.size(); i++) {

                    Box box = boxes.get(i);
                    
                    if (soko.isRightCollision(box)) {
                        
                        for (int j = 0; j < boxes.size(); j++) {

                            Box item = boxes.get(j);
                            
                            if (!box.equals(item)) {
                                
                                if (box.isRightCollision(item)) {
                                    return true;
                                }
                            }
                            
                            if (checkWallCollision(box, RIGHT_COLLISION)) {
                                return true;
                            }
                        }
                        
                        box.move(SPACE, 0);
                        isCompleted();
                    }
                }
                return false;
                
            case TOP_COLLISION:
                
                for (int i = 0; i < boxes.size(); i++) {

                    Box box = boxes.get(i);
                    
                    if (soko.isTopCollision(box)) {
                        
                        for (int j = 0; j < boxes.size(); j++) {

                            Box item = boxes.get(j);

                            if (!box.equals(item)) {
                                
                                if (box.isTopCollision(item)) {
                                    return true;
                                }
                            }
                            
                            if (checkWallCollision(box, TOP_COLLISION)) {
                                return true;
                            }
                        }
                        
                        box.move(0, -SPACE);
                        isCompleted();
                    }
                }

                return false;
                
            case BOTTOM_COLLISION:
                
                for (int i = 0; i < boxes.size(); i++) {

                    Box box = boxes.get(i);
                    
                    if (soko.isBottomCollision(box)) {
                        
                        for (int j = 0; j < boxes.size(); j++) {

                            Box item = boxes.get(j);
                            
                            if (!box.equals(item)) {
                                
                                if (box.isBottomCollision(item)) {
                                    return true;
                                }
                            }
                            
                            if (checkWallCollision(box,BOTTOM_COLLISION)) {
                                
                                return true;
                            }
                        }
                        
                        box.move(0, SPACE);
                        isCompleted();
                    }
                }
                
                break;
                
            default:
                break;
        }

        return false;
    }

    public void isCompleted() {

        int nOfboxs = boxes.size();
        int finishedboxs = 0;

        for (int i = 0; i < nOfboxs; i++) {
            
            Box box = boxes.get(i);
            
            for (int j = 0; j < nOfboxs; j++) {
                
                Area area =  areas.get(j);
                
                if (box.x() == area.x() && box.y() == area.y()) {
                    
                    finishedboxs += 1;
                }
            }
        }

        if (finishedboxs == nOfboxs) {
            
            isCompleted = true;
            repaint();
        }
    }

    public void restartLevel() {

        areas.clear();
        boxes.clear();
        walls.clear();

        initWorld();

        if (isCompleted) {
            isCompleted = false;
        }
    }
}
