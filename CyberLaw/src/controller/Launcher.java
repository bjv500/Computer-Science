package controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import models.Character;
import models.CharacterList;

public class Launcher {

	
	public static void createAndShowGUI() {
		
		CharacterList characterList = new CharacterList();
		JTextField fldNumChars;
		
		
		characterList.addCharacterToList(new Character(1, 1, false ));
		characterList.addCharacterToList(new Character(2, 2, false));
		characterList.addCharacterToList(new Character(3, 3, false));
		
		MDIParent appFrame = new MDIParent("Cyber Law Project - Eric Applonie", characterList);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		JButton buttonCreate = new JButton("Create");
		buttonCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}

		});
		
		panel.add(new JLabel("        Number of Characters: "));
		fldNumChars = new JTextField("");
		panel.add(fldNumChars);
		
		panel.add(buttonCreate);
		appFrame.add(panel, BorderLayout.NORTH);	
		
		//use exit on close if you only want windowClosing to be called (can abort closing here also)
		//appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//use dispose on close if you want windowClosed to also be called
		appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//need to set initial size of MDI frame
		appFrame.setSize(640, 480);
		
		appFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		//CS 4743 Assignemtn 1 by Eric Applonie
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
}
