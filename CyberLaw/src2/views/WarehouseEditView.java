package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MDIChild;
import controller.MDIParent;
import models.Warehouse;

public class WarehouseEditView extends MDIChild implements Observer{

	private Warehouse myWarehouse;
	private JLabel fldId;
	private JTextField fldName, fldAddress, fldCity, fldState, fldZip;
	private int intcap;
	private JTextField fldCap;
	
	
	public WarehouseEditView(String title, Warehouse warehouse, MDIParent m) {
		super(title, m);
		
		myWarehouse = warehouse;
		myWarehouse.addObserver(this);;
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(7, 2));
		
		panel.add(new JLabel("Id: " + warehouse.getId()));
		fldId = new JLabel("");
		panel.add(fldId);
		
		panel.add(new JLabel("Name: "));
		fldName = new JTextField("");
		panel.add(fldName);
		
		panel.add(new JLabel("Address: "));
		fldAddress = new JTextField("");
		panel.add(fldAddress);
		
		panel.add(new JLabel("City: "));
		fldCity = new JTextField("");
		panel.add(fldCity);
		
		panel.add(new JLabel("State: "));
		fldState = new JTextField("");
		panel.add(fldState);
		
		panel.add(new JLabel("Zip Code: "));
		fldZip = new JTextField("");
		panel.add(fldZip);
		
		panel.add(new JLabel("Capacity: "));
		fldCap = new JTextField("");
		panel.add(fldCap);
		
		this.add(panel);
		
		//add a Save button to write field changes back to model data
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveWarehouse();
			}
		});
		panel.add(button);
		this.add(panel, BorderLayout.SOUTH);
		refreshFields();
		
		this.setPreferredSize(new Dimension(360, 210));
	}

	private void saveWarehouse() {
		
		
		try {
			String cName = fldName.getText();
			myWarehouse.setName(cName);
			
			String cAddress = fldAddress.getText();
			myWarehouse.setName(cName);
			
			String cCity = fldCity.getText();
			myWarehouse.setCity(cCity);
			
			String cState = fldState.getText();
			myWarehouse.setState(cState);
			
			String cZip = fldZip.getText();
			myWarehouse.setZip(cZip);
			
			String cCap = fldCap.getText();
			intcap = Integer.parseInt(cCap);
			myWarehouse.setCap(intcap);
			
			refreshFields();
			
			myWarehouse.finishUpdate();

			parent.displayChildMessage("Saved");
		} catch (Exception e) {
			parent.displayChildMessage(e.getMessage());
			return;
		}
		
	}

	public void refreshFields() {
		fldId.setText("" + myWarehouse.getId());
		fldName.setText(myWarehouse.getName());
		fldAddress.setText(myWarehouse.getAddress());
		fldCity.setText(myWarehouse.getCity());
		fldState.setText(myWarehouse.getState());
		fldZip.setText(myWarehouse.getZip());
		String capString = Integer.toString(myWarehouse.getCap());
		fldCap.setText(capString);
		//update window title
		this.setTitle(myWarehouse.getName());
		setInternalFrameVisible(false);
		childClosing();
	}
	
	@Override
	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observable
		myWarehouse.deleteObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		refreshFields();
	}

}
