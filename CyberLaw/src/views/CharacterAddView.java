package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MDIChild;
import controller.MDIChildFrame;
import controller.MDIParent;
import controller.CharacterListController;
import models.Character;
import models.CharacterList;

public class CharacterAddView extends MDIChild implements Observer{

	private Character myCharacter = new Character();
	private JLabel fldId;
	private JTextField fldName, fldAddress, fldCity, fldState, fldZip;
	private int inthappiness;
	private JTextField fldHappiness;
	private CharacterList thisCharacterlist = new CharacterList();
	private CharacterListController wlcx;
	
	public CharacterAddView(String title, CharacterListController wlc , MDIParent m, CharacterList Characterlist) {
		super(title, m);

		thisCharacterlist = Characterlist;
		myCharacter.Characterlist = thisCharacterlist;
		wlcx = wlc;
		myCharacter.addObserver(this);
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(7, 2));
		
		panel.add(new JLabel("Id: " + myCharacter.getId()));
		fldId = new JLabel("");
		panel.add(fldId);
		
		panel.add(new JLabel("Happiness: "));
		fldHappiness = new JTextField("");
		panel.add(fldHappiness);
		
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCharacter();
			}

		});
		panel.add(button);
		
		this.add(panel, BorderLayout.SOUTH);
		
	}

	private void addCharacter() {
		try {
			
			String cHappiness = fldHappiness.getText();
			inthappiness = Integer.parseInt(cHappiness);
			myCharacter.setHappiness(inthappiness);
			
			refreshFields();
			
			myCharacter.finishUpdate();

			parent.displayChildMessage("Added");
		} catch (Exception e) {
			parent.displayChildMessage(e.getMessage());
			//refreshFields();
			return;
		}
		
		//thisCharacterlist.addCharacterToList(myCharacter);
		wlcx.addtolist(myCharacter);
		setInternalFrameVisible(false);
		childClosing();
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshFields();	
	}



	public void refreshFields() {
		fldId.setText("" + myCharacter.getId());

		String happinessString = Integer.toString(myCharacter.getHappiness());
		fldHappiness.setText(happinessString);
		//update window title
		this.setTitle("Character " + myCharacter.getId());
	}
	
	@Override
	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observable
		myCharacter.deleteObserver(this);
	}

}
