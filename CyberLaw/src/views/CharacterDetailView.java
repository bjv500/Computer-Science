package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MDIChild;
import controller.MDIParent;
import models.Character;

public class CharacterDetailView extends MDIChild implements Observer{

	private Character myCharacter;
	private JLabel fldId, fldName, fldAddress, fldCity, fldState, fldZip, fldHappiness;
	
	
	public CharacterDetailView(String title, Character Character, MDIParent m) {
		super(title, m);
		
		myCharacter = Character;
		myCharacter.addObserver(this);
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(7, 2));
		
		panel.add(new JLabel("Id: " + Character.getId()));
//		fldId = new JLabel("");
//		panel.add(fldId);
		
		panel.add(new JLabel("Happiness: " + Character.getHappiness()));
		fldHappiness = new JLabel("");
		panel.add(fldHappiness);
		
		panel.add(new JLabel("Selfishness: " + Character.getSelfishness()));
		
		this.add(panel);
		

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setInternalFrameVisible(false);
				childClosing();
				
			}
		});
		panel.add(button);
		
		this.add(panel, BorderLayout.SOUTH);
		
	}
	
	@Override
	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observable
		myCharacter.deleteObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
