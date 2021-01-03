package com.plotarmor.frontpage;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelChoosing extends JPanel {
	private static final long serialVersionUID = 3L;
	private int maxLevel = 11;
	private JLabel counterLabel;
	private int count;
	
	public LevelChoosing() {
		this.count = 1;
		this.counterLabel = new JLabel("Lvl " + count);
		Action countUpAction = new CountAction("+", "To count up", new Integer(KeyEvent.VK_U), 1);
		Action countDownAction = new CountAction("-", "To count down", new Integer(KeyEvent.VK_D), -1);
		
		JButton btnCountUp = new JButton(countUpAction);
		JButton btnCountDown = new JButton(countDownAction);
		
		add(btnCountUp);
		add(counterLabel);
		add(btnCountDown);
	}
	
	public class CountAction extends AbstractAction {
		private static final long serialVersionUID = 5L;
		public int addingValue;
		
		public CountAction(String name, String shortDesc, Integer mnemonic, int addingValue) {
			super(name);
			putValue(SHORT_DESCRIPTION, shortDesc);
			putValue(MNEMONIC_KEY, mnemonic);
			this.addingValue = addingValue;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int afterAdditionValue = count + addingValue;
			
			if(1 <= afterAdditionValue && afterAdditionValue <= maxLevel)
				count = afterAdditionValue;
			
			counterLabel.setText("Lvl: " + count);
		}
		
		
	}
}
