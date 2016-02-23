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
import models.Warehouse;

public class WarehouseDetailView extends MDIChild implements Observer{

	private Warehouse myWarehouse;
	private JLabel fldId, fldName, fldAddress, fldCity, fldState, fldZip, fldCap;
	
	
	public WarehouseDetailView(String title, Warehouse warehouse, MDIParent m) {
		super(title, m);
		
		myWarehouse = warehouse;
		myWarehouse.addObserver(this);;
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(7, 2));
		
		panel.add(new JLabel("Id: " + warehouse.getId()));
		fldId = new JLabel("");
		panel.add(fldId);
		
		panel.add(new JLabel("Address: " + warehouse.getAddress()));
		fldAddress = new JLabel("");
		panel.add(fldAddress);
		
		panel.add(new JLabel("City: " + warehouse.getCity()));
		fldCity = new JLabel("");
		panel.add(fldCity);
		
		panel.add(new JLabel("State: " + warehouse.getState()));
		fldState = new JLabel("");
		panel.add(fldState);
		
		panel.add(new JLabel("Zip Code: " + warehouse.getZip()));
		fldZip = new JLabel("");
		panel.add(fldZip);
		
		panel.add(new JLabel("Capacity: " + warehouse.getCap()));
		fldCap = new JLabel("");
		panel.add(fldCap);
		
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
		myWarehouse.deleteObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
