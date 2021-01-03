package com.plotarmor;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.plotarmor.frontpage.FrontPagePanel;

public class Main {
	private static void createAndShowGUI() throws IOException {
		//Create and set up the window.
        JFrame frame = new JFrame("Sokoban Reborn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new FrontPagePanel());
        frame.setSize(700, 700);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

}
