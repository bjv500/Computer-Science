package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Custome menu to use in the MDIParent 
 * @author marcos
 *
 */
public class MDIMenu extends JMenuBar {
	/**
	 * Containing JFrame
	 */
	MDIParent parent;
	
	public MDIMenu(MDIParent p) {
		super();
		
		this.parent = p;
		
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.doCommand(MenuCommands.APP_QUIT, null);
			}
		});
		menu.add(menuItem);
		this.add(menu);
		
		menu = new JMenu("Warehouses");
		menuItem = new JMenuItem("Warehouse List");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.doCommand(MenuCommands.SHOW_LIST_WAREHOUSE, null);
			}
		});
		menu.add(menuItem);
		this.add(menu);		
	}
}