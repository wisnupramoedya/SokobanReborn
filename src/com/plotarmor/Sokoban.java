package com.plotarmor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.plotarmor.frontpage.FrontPagePanel;
import com.plotarmor.game.Board;

public class Sokoban extends JFrame {
	private static final long serialVersionUID = 5L;
	private final int OFFSET = 50;
	private final int SIDEBAR = 200;
	
	public Board board;
	public FrontPagePanel frontPagePanel;
	public DataResources dataResources;
	
	public JLabel start;
	
    public Sokoban() {
        initUI();
    }

    private void initUI() {
    	setLayout(new BorderLayout());
    	dataResources = new DataResources();
        board = new Board(dataResources);
        frontPagePanel = new FrontPagePanel(dataResources);
        
        start = new JLabel("Tekan R untuk merestart level");
        frontPagePanel.add(start);
        
        add(board, BorderLayout.CENTER);
        add(frontPagePanel, BorderLayout.EAST);
        
        setTitle("Sokoban");
        
        setSize(SIDEBAR + board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sokoban game = new Sokoban();
            game.setVisible(true);
        });
    }
}
