package controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import models.Warehouse;
import models.WarehouseList;

public class Launcher {
	
	public static void createAndShowGUI() {
		
		WarehouseList warehouseList = new WarehouseList();
		
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Alpha", "1234 Main", "Big City", "Texas", "12345", 1000));
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Bravo", "1234 Main", "Big City", "Texas", "12345", 1000));
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Charlie", "1234 Main", "Big City", "Texas", "12345", 1000));
		
		
		MDIParent appFrame = new MDIParent("Assignment 1 - Cabnetron", warehouseList);
		
		
		//use exit on close if you only want windowClosing to be called (can abort closing here also)
		//appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//use dispose on close if you want windowClosed to also be called
		appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//need to set initial size of MDI frame
		appFrame.setSize(640, 480);
		
		appFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		//CS 4743 Assignemtn 1 by Eric Applonie
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
}
