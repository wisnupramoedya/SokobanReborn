package com.plotarmor.frontpage;

import javax.swing.JPanel;

public class FrontPagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public FrontPagePanel() {
		add(new AvatarChoosing());
		add(new LevelChoosing());
	}
}
