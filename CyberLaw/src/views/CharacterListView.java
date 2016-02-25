package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Character;

import controller.MDIChild;
import controller.MDIParent;
import controller.MenuCommands;
import controller.CharacterListController;

public class CharacterListView extends MDIChild {

	private JList<Character> listCharacter;
	private CharacterListController myList;
	private Character selectedModel;
	JPanel panel;
	
	
	public CharacterListView(String title, CharacterListController list, MDIParent m){
		super(title, m);
		
		list.setMyListView(this);
		
		myList = list;
		listCharacter = new JList<Character>(myList);
		
		listCharacter.setCellRenderer(new CharacterListCellRenderer());
		listCharacter.setPreferredSize(new Dimension(200, myList.getSize() * 18));
		listCharacter.setVisibleRowCount(10);
		
		listCharacter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 2) {
					int index = listCharacter.locationToIndex(evt.getPoint());
					selectedModel = myList.getElementAt(index);
					openDetailView();
				}
			}
		});
		
		this.add(new JScrollPane(listCharacter));
		this.setPreferredSize(new Dimension(240, 300));
		
		//////ADD//////
//		panel = new JPanel();
//		panel.setLayout(new FlowLayout());
//		JButton buttonAdd = new JButton("Add");
//		buttonAdd.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				openAddView();
//			}
//		});
//		panel.add(buttonAdd);
//		this.add(panel, BorderLayout.SOUTH);
		
		/////EDIT/////
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton buttonEdit = new JButton("Edit");
		listCharacter.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent l) {
				int index = listCharacter.getMinSelectionIndex();
				selectedModel = myList.getElementAt(index);
				index = 1;
			}
		});
		buttonEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openEditView();
			}
		});
		panel.add(buttonEdit);
		this.add(panel, BorderLayout.SOUTH);
		
		/////DELETE/////
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton buttonDel = new JButton("Delete");
		buttonDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteCharacter();
				listCharacter.updateUI();
			}

		});
		panel.add(buttonDel);
		this.add(panel);	
	}
	
	protected void openDetailView() {
		parent.doCommand(MenuCommands.SHOW_DETAIL_CHARACTER, this);
	}
	
	public void openEditView() {
		parent.doCommand(MenuCommands.SHOW_EDIT_CHARACTER, this);
	}
	
	public void openAddView() {
		parent.doCommand(MenuCommands.SHOW_ADD_CHARACTER, this);
	}

	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observables
		myList.unregisterAsObserver();
	}
	
	private void deleteCharacter() {
		parent.doCommand(MenuCommands.DELETE_CHARACTER, this);
		selectedModel = null;
		
	}
	

	public Character getSelectedCharacter() {
		return selectedModel;
	}

}
