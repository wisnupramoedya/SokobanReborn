package com.plotarmor.frontpage;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.plotarmor.DataResources;


public class AvatarChoosing extends JPanel {
	private static final long serialVersionUID = 2L;
	private JComboBox<String> comboAvatar;
	public JLabel jlAvatarChoosing;
	
	public ArrayList<ImageIcon> imgIcons;
	public ArrayList<Image> playerImages;
	
	public DataResources dataResources;
	
	public AvatarChoosing(DataResources dataResources) {
		this.dataResources = dataResources;
		createImageIcons();
		JLabel picLabel = new JLabel(imgIcons.get(0));
		add(picLabel);
		
		// showing combo box
		comboAvatar = new JComboBox<String>(dataResources.strAvatar);
		comboAvatar.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					dataResources.indexAvatar = comboAvatar.getSelectedIndex();
					picLabel.setIcon(imgIcons.get(dataResources.indexAvatar));
				}
			}
		});
		add(comboAvatar);
	}
	
	public void createImageIcons() {
		imgIcons = new ArrayList<ImageIcon>();
		for (String avatar : dataResources.strAvatar) {
			imgIcons.add(new ImageIcon(dataResources.resourcesUrl + avatar + ".png"));
		}
		
		playerImages = new ArrayList<Image>();
		for (ImageIcon imgIcon: imgIcons) {
			playerImages.add(imgIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		}
	}
}
