package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.MDIChild;
import controller.MDIParent;
import models.Character;

public class CharacterEditView extends MDIChild implements Observer{

	private Character myCharacter;
	private JLabel fldId;
	private JTextField fldName, fldAddress, fldCity, fldState, fldZip;
	private int intHappiness;
	private JTextField fldHappiness;
	
	
	public CharacterEditView(String title, Character Character, MDIParent m) {
		super(title, m);
		
		myCharacter = Character;
		myCharacter.addObserver(this);;
		
		JPanel panel = new JPanel(); 
		JPanel panel1 = new JPanel(); 
		panel.setLayout(new GridLayout(1, 2));
	
		panel.add(new JLabel("Happiness: "));
		fldHappiness = new JTextField("");
		panel.add(fldHappiness);
	
		panel.setLayout(new FlowLayout());
	     JRadioButton selfish = new JRadioButton("Selfish");
	     JRadioButton unselfish = new JRadioButton("Unselfish");
	     JRadioButton mixed = new JRadioButton("Mixed");
	     ButtonGroup bG = new ButtonGroup();
	     bG.add(selfish);
	     bG.add(unselfish);
	     bG.add(mixed);
	     panel.add(selfish);
	     panel.add(unselfish);
	     panel.add(mixed);
	     selfish.setSelected(true);
	     //this.setVisible(true);
	     
	     this.add(panel);
		
		//add a Save button to write field changes back to model data
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveCharacter();
			}
		});
		panel1.add(button);
		this.add(panel1, BorderLayout.SOUTH);
		refreshFields();
		
		this.setPreferredSize(new Dimension(350, 100));
	}

	private void saveCharacter() {
		
		
		try {			
			String cHappiness = fldHappiness.getText();
			intHappiness = Integer.parseInt(cHappiness);
			myCharacter.setHappiness(intHappiness);
			
			refreshFields();
			
			myCharacter.finishUpdate();

			parent.displayChildMessage("Saved");
		} catch (Exception e) {
			parent.displayChildMessage(e.getMessage());
			return;
		}
		
	}

	public void refreshFields() {
		//fldId.setText("" + myCharacter.getId());
		String HappinessString = Integer.toString(myCharacter.getHappiness());
		fldHappiness.setText(HappinessString);
		//update window title
		this.setTitle("Character " + myCharacter.getId());
		setInternalFrameVisible(false);
		childClosing();
	}
	
	@Override
	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observable
		myCharacter.deleteObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		refreshFields();
	}

}
