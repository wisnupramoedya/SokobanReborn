package com.plotarmor;

//import java.awt.Image;
//import java.util.ArrayList;
//
//import javax.swing.ImageIcon;

public class DataResources {
	public String resourcesUrl = "src/resources/avatar/";
	public String[] strAvatar = {"Carlo", "Este"};
	public int indexAvatar;
	
	public int maxLevel;
	public int count;
	
	public String[] strLevel = {
		  "    ######\n"
		+ "    ##   #\n"
		+ "    ##$  #\n"
		+ "  ####  $##\n"
		+ "  ##  $ $ #\n"
		+ "#### # ## #   ######\n"
		+ "##   # ## #####  ..#\n"
		+ "## $  $          ..#\n"
		+ "###### ### #@##  ..#\n"
		+ "    ##     #########\n"
		+ "    ########\n",
		  "######\n"
		+ "##   #\n"
		+ "#@$$ #\n"
		+ "#$ . #\n"
		+ "#.  .#\n"
		+ "######\n"
	};
	
	public DataResources() {
		maxLevel = strLevel.length;
		indexAvatar = 0;
		count = 0;
	}
	
	public String createAvatarUrl() {
		return resourcesUrl + strAvatar[indexAvatar] + ".png";
	}
	
	public String createLevel() {
		return strLevel[count];
	}
	
}
