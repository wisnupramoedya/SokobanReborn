package com.plotarmor.frontpage;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.plotarmor.DataResources;

public class LevelChoosing extends JPanel {
	private static final long serialVersionUID = 3L;
	private JLabel counterLabel;

	public DataResources dataResources;
	
	public LevelChoosing(DataResources dataResources) {
		this.dataResources = dataResources;
		
		this.counterLabel = new JLabel("Lvl " + String.valueOf(dataResources.count + 1));
		Action countUpAction = new CountAction(">", "To count up", 1);
		Action countDownAction = new CountAction("<", "To count down", -1);
		
		JButton btnCountUp = new JButton(countUpAction);
		JButton btnCountDown = new JButton(countDownAction);
		
		add(btnCountDown);
		add(counterLabel);
		add(btnCountUp);
	}
	
	public class CountAction extends AbstractAction {
		private static final long serialVersionUID = 5L;
		public int addingValue;
		
		public CountAction(String name, String shortDesc, int addingValue) {
			super(name);
			putValue(SHORT_DESCRIPTION, shortDesc);
			this.addingValue = addingValue;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int afterAdditionValue = dataResources.count + addingValue;
			
			if(0 <= afterAdditionValue && afterAdditionValue < dataResources.maxLevel)
				dataResources.count = afterAdditionValue;
			
			counterLabel.setText("Lvl: " + String.valueOf(dataResources.count + 1));
		}
	}
}
