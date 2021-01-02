package com.plotarmor.frontpage;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvatarChoosing extends JPanel {
	private static final long serialVersionUID = 2L;
	private String resourcesUrl = "src/resources/avatar/";
	private JComboBox<String> comboAvatar;
	private ArrayList<ImageIcon> imgIcons;
	public String[] strAvatar = {"Carlo", "Este"};
	public int indexAvatar;
	public JLabel jlAvatarChoosing;
	
	public AvatarChoosing() {
		// showing image icon
		createImageIcons();
		JLabel picLabel = new JLabel(imgIcons.get(0));
		add(picLabel);
		
		// showing combo box
		comboAvatar = new JComboBox<String>(strAvatar);
		comboAvatar.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					indexAvatar = comboAvatar.getSelectedIndex();
					picLabel.setIcon(imgIcons.get(indexAvatar));
				}
			}
		});
		add(comboAvatar);
	}
	
	private void createImageIcons() {
		imgIcons = new ArrayList<ImageIcon>();
		for (String avatar : strAvatar) {
			imgIcons.add(new ImageIcon(resourcesUrl + avatar + ".png"));
			System.out.println(imgIcons);
		}
	}
}
