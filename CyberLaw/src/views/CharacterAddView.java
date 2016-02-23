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

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.MDIChild;
import controller.MDIChildFrame;
import controller.MDIParent;
import controller.CharacterListController;
import models.Character;
import models.CharacterList;

public class CharacterAddView extends MDIChild implements Observer{

	private Character myCharacter;
	private JLabel fldId;
	private JTextField fldState, fldHappiness;
	private int intHappiness;
	private JRadioButton selfish = new JRadioButton("Selfish");
	private JRadioButton nonselfish = new JRadioButton("NonSelfish");
	private int size;

	private CharacterList thisCharacterlist = new CharacterList();
	private CharacterListController wlcx;
	
	public CharacterAddView(String title, MDIParent m, CharacterList characterlist) {
		super(title, m);

		thisCharacterlist = characterlist;
		size = thisCharacterlist.getSize();
		myCharacter = new Character();
		myCharacter.addObserver(this);
		
		JPanel panel = new JPanel();
		ButtonGroup bState = new ButtonGroup();
		
		panel.setLayout(new GridLayout(2, 2));
		
		panel.add(new JLabel("Happiness: "));
		fldHappiness = new JTextField("");
		panel.add(fldHappiness);
		
		bState.add(selfish);
		bState.add(nonselfish);
		
		panel.add(selfish);
		panel.add(nonselfish);
		
		selfish.setSelected(true);
		
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
			myCharacter.setId(size);
			String cHappiness = fldHappiness.getText();
			intHappiness= Integer.parseInt(cHappiness);
			myCharacter.setHappiness(intHappiness);
			
			refreshFields();
			parent.displayChildMessage("Added");
		} catch (Exception e) {
			parent.displayChildMessage(e.getMessage());
			return;
		}
		
		thisCharacterlist.addCharacterToList(myCharacter);
		setInternalFrameVisible(false);
		childClosing();
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshFields();	
	}



	public void refreshFields() {
		fldId.setText("" + myCharacter.getId());
		String cHappiness = Integer.toString(myCharacter.getHappiness());
		fldHappiness.setText(cHappiness);
		this.setTitle("Character:" + myCharacter.getId());
	}
	
	@Override
	protected void childClosing() {
		super.childClosing();
		myCharacter.deleteObserver(this);
	}

}
