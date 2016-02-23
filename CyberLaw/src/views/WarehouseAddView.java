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
import controller.WarehouseListController;
import models.Warehouse;
import models.WarehouseList;

public class WarehouseAddView extends MDIChild implements Observer{

	private Warehouse myWarehouse = new Warehouse();
	private JLabel fldId;
	private JTextField fldName, fldAddress, fldCity, fldState, fldZip;
	private int intcap;
	private JTextField fldCap;
	private WarehouseList thisWarehouselist = new WarehouseList();
	private WarehouseListController wlcx;
	
	public WarehouseAddView(String title, WarehouseListController wlc , MDIParent m, WarehouseList warehouselist) {
		super(title, m);

		thisWarehouselist = warehouselist;
		myWarehouse.warehouselist = thisWarehouselist;
		wlcx = wlc;
		myWarehouse.addObserver(this);
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(7, 2));
		
		panel.add(new JLabel("Id: " + myWarehouse.getnewId()));
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
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addWarehouse();
			}

		});
		panel.add(button);
		
		this.add(panel, BorderLayout.SOUTH);
		
	}

	private void addWarehouse() {
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

			parent.displayChildMessage("Added");
		} catch (Exception e) {
			parent.displayChildMessage(e.getMessage());
			//refreshFields();
			return;
		}
		
		//thisWarehouselist.addWarehouseToList(myWarehouse);
		wlcx.addtolist(myWarehouse);
		setInternalFrameVisible(false);
		childClosing();
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshFields();	
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
	}
	
	@Override
	protected void childClosing() {
		//let superclass do its thing
		super.childClosing();
				
		//unregister from observable
		myWarehouse.deleteObserver(this);
	}

}
