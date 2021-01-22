package com.plotarmor.frontpage;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.plotarmor.DataResources;


public class FrontPagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public AvatarChoosing avatarChoosing;
	public LevelChoosing levelChoosing;
	
	public FrontPagePanel(DataResources dataResources) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		avatarChoosing = new AvatarChoosing(dataResources);
		levelChoosing = new LevelChoosing(dataResources);
		
		add(avatarChoosing);
		add(levelChoosing);
	}
}
