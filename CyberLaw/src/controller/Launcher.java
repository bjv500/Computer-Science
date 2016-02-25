package controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import models.Character;
import models.CharacterList;

public class Launcher {
	

	public static void createAndShowGUI() {
		
		CharacterList CharacterList = new CharacterList();
		
//		CharacterList.addCharacterToList(new Character(1, 3, false));
//		CharacterList.addCharacterToList(new Character(2, 6, true));
//		CharacterList.addCharacterToList(new Character(3, 7, true));
		
		
		MDIParent appFrame = new MDIParent("Simulation", CharacterList);
		
		
		
		//use exit on close if you only want windowClosing to be called (can abort closing here also)
appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//use dispose on close if you want windowClosed to also be called
		appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		
//		//need to set initial size of MDI frame
		appFrame.setSize(640, 480);
		appFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
}
