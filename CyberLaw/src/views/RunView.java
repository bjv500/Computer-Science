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

public class RunView extends MDIChild implements Observer{

	private Character myCharacter = new Character();
	private JTextField fldNumber;
	private int intnumber;
	private JTextField fldHappiness;
	private CharacterList thisCharacterlist = new CharacterList();
	private CharacterListController wlcx;
	Random rn = new Random();
	
	public RunView(String title, CharacterListController wlc , MDIParent m, CharacterList Characterlist) {
		super(title, m);

	
		thisCharacterlist = Characterlist;
		myCharacter.Characterlist = thisCharacterlist;
		wlcx = wlc;
		myCharacter.addObserver(this);
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		
		panel.add(new JLabel("Solo run - Enter Character ID"));
		fldNumber = new JTextField("");
		fldNumber.setColumns(4);
		
		panel.add(fldNumber);
		
		this.add(panel);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton button = new JButton("Run Solo");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Run();
			}
		});
		
		JButton buttonAll =  new JButton("Run All");
		
		panel.add(button);
		
		buttonAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunAll();
			}
		});
		panel.add(buttonAll);
		
		this.add(panel);
		
	}

	private void Run() {
			System.out.println("RUN SOLO");
	}
	
	private void RunAll() {
			System.out.println("RUN ALL");
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshFields();	
	}
	
	public boolean fixSelfish(int intselfish){
		if(intselfish == 1){
			return true;
		}
		return false;
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
