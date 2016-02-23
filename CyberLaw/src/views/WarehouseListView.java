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

import models.Warehouse;

import controller.MDIChild;
import controller.MDIParent;
import controller.MenuCommands;
import controller.WarehouseListController;

public class WarehouseListView extends MDIChild {

	private JList<Warehouse> listWarehouse;
	private WarehouseListController myList;
	private Warehouse selectedModel;
	JPanel panel;
	
	
	public WarehouseListView(String title, WarehouseListController list, MDIParent m){
		super(title, m);
		
		list.setMyListView(this);
		
		myList = list;
		listWarehouse = new JList<Warehouse>(myList);
		
		listWarehouse.setCellRenderer(new WarehouseListCellRenderer());
		listWarehouse.setPreferredSize(new Dimension(200, 200));
		
		listWarehouse.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 2) {
					int index = listWarehouse.locationToIndex(evt.getPoint());
					selectedModel = myList.getElementAt(index);
					openDetailView();
				}
			}
		});
		
		this.add(new JScrollPane(listWarehouse));
		this.setPreferredSize(new Dimension(240, 200));
		
		//////ADD//////
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openAddView();
			}
		});
		panel.add(buttonAdd);
		this.add(panel, BorderLayout.SOUTH);
		
		/////EDIT/////
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton buttonEdit = new JButton("Edit");
		listWarehouse.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent l) {
				int index = listWarehouse.getMinSelectionIndex();
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
				deleteWarehouse();
				listWarehouse.updateUI();
			}

		});
		panel.add(buttonDel);
		this.add(panel);	
	}
	
	protected void openDetailView() {
		parent.doCommand(MenuCommands.SHOW_DETAIL_WAREHOUSE, this);
	}
	
	public void openEditView() {
		parent.doCommand(MenuCommands.SHOW_EDIT_WAREHOUSE, this);
	}
	
	public void openAddView() {
		parent.doCommand(MenuCommands.SHOW_ADD_WAREHOUSE, this);
	}

	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observables
		myList.unregisterAsObserver();
	}
	
	private void deleteWarehouse() {
		parent.doCommand(MenuCommands.DELETE_WAREHOUSE, this);
		selectedModel = null;
		
	}
	

	public Warehouse getSelectedWarehouse() {
		return selectedModel;
	}

}
