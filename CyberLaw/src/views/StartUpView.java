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
import java.util.Random;

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

public class StartUpView extends MDIChild implements Observer{

	private Character myCharacter = new Character();
	private JTextField fldNumber;
	private int intnumber;
	private JTextField fldHappiness;
	private CharacterList thisCharacterlist = new CharacterList();
	private CharacterListController wlcx;
	Random rn = new Random();
	
	public StartUpView(String title, CharacterListController wlc , MDIParent m, CharacterList Characterlist) {
		super(title, m);

	
		thisCharacterlist = Characterlist;
		myCharacter.Characterlist = thisCharacterlist;
		wlcx = wlc;
		myCharacter.addObserver(this);
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		
		panel.add(new JLabel("Number of characters to create"));
		fldNumber = new JTextField("");
		fldNumber.setColumns(4);
		panel.add(fldNumber);
		
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton button = new JButton("Create");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Create();
			}

		});
		panel.add(button);
		this.add(panel, BorderLayout.SOUTH);
		
	}

	private void Create() {
		try {
			
			String Number = fldNumber.getText();
			intnumber = Integer.parseInt(Number);
			
			for(int i = 1; i <= intnumber; i++){
				Character newCharacter = new Character();
				newCharacter.setHappiness(rn.nextInt(100) + 1);
				newCharacter.setId(i);
				newCharacter.setSelfish(rn.nextInt(3) + 0);
				newCharacter.finishUpdate();
				wlcx.addtolist(newCharacter);
			}
			
			} catch (Exception e) {
				parent.displayChildMessage(e.getMessage());
				System.out.println("failed" + e);
				return;
			}

	}

	@Override
	public void update(Observable o, Object arg) {
		refreshFields();	
	}
	

	public void refreshFields() {

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
