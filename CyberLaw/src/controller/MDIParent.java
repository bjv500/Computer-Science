package controller;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import models.Character;
import models.CharacterList;
import views.CharacterAddView;
import views.CharacterDetailView;
import views.CharacterEditView;
import views.CharacterListView;

public class MDIParent extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktop;
	private int newFrameX = 0, newFrameY = 0;
	private int sizeoflist = 0;
	
	
	private CharacterList characterList;
	
	private List<MDIChild> openViews;
	
	private CharacterListController wlc;
	
	public MDIParent(String title, CharacterList wList) {
		super(title);
		System.out.println(wList.getList());
		sizeoflist = wList.getSize();
		System.out.println(wList.getSize());
		
		characterList = wList;
		wlc = new CharacterListController(characterList);
		
		//init the view list
		openViews = new LinkedList<MDIChild>();
		
		//create menu for adding inner frames
		MDIMenu menuBar = new MDIMenu(this);
		setJMenuBar(menuBar);
		   
		//create the MDI desktop
		desktop = new JDesktopPane();
		add(desktop);
		
		//add shutdown hook to clean up properly even when VM quits (e.g., Command-Q)
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				closeProperly();
			}
		});

	}

	public void doCommand(MenuCommands cmd, Container caller) {
		switch(cmd) {
			case APP_QUIT :
				//close all child windows first
				closeChildren();
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				break;
				
			case SHOW_LIST_CHARACTER :
				CharacterListView v1 = new CharacterListView("Character List", wlc, this);
				//v1.setSingleOpenOnly(true);
				openMDIChild(v1);
				
				break;
			case SHOW_DETAIL_CHARACTER :
				Character w = ((CharacterListView) caller).getSelectedCharacter();
		    	CharacterDetailView v = new CharacterDetailView("Character " + w.getId(), w, this);
				openMDIChild(v);
				break;
				
			case SHOW_EDIT_CHARACTER :
				Character w2 = ((CharacterListView) caller).getSelectedCharacter();
		    	CharacterEditView v2 = new CharacterEditView("Character " + w2.getId(), w2, this);
				openMDIChild(v2);
				break;
				
			case SHOW_ADD_CHARACTER :
		    	CharacterAddView v3 = new CharacterAddView("Character " + characterList.getSize() + 1, this, characterList);
		    	openMDIChild(v3);
				break;
				
			case DELETE_CHARACTER :
				characterList.removeCharacterFromList(((CharacterListView) caller).getSelectedCharacter());
				break;	
				
		}
	}
		
	/**
	 * Close all open children by telling them to close normally (this will fire their own cleanup events)
	 * This is called when Quit is selected on the menu
	 */
	public void closeChildren() {
		JInternalFrame [] children = desktop.getAllFrames();
		for(int i = children.length - 1; i >= 0; i--) {
			children[i].dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			//as each child window closes, it will call its closeChild() method to clean itself up
		}
	}

	/**
	 * Child windows are already closing so we just need to tell the child panels to clean up.
	 * This can happen when the JVM closes
	 */
	public void cleanupChildPanels() {
		JInternalFrame [] children = desktop.getAllFrames();
		for(int i = children.length - 1; i >= 0; i--) {
			if(children[i] instanceof MDIChildFrame)
				((MDIChildFrame) children[i]).closeChild();
		}
	}
	
	/**
	 * this method will always be called when the app quits since it hooks into the VM
	 */
	public void closeProperly() {
		cleanupChildPanels();
	}

	/**
	 * create the child panel, insert it into a JInternalFrame and show it
	 * @param child
	 * @return
	 */
	public JInternalFrame openMDIChild(MDIChild child) {
		//first, if child's class is single open only and already open,
		//then restore and show that child
		//System.out.println(openViewNames.contains(child));
		if(child.isSingleOpenOnly()) {
			for(MDIChild testChild : openViews) {
				if(testChild.getClass().getSimpleName().equals(child.getClass().getSimpleName())) {
					try {
						testChild.restoreWindowState();
					} catch(PropertyVetoException e) {
						e.printStackTrace();
					}
					JInternalFrame c = (JInternalFrame) testChild.getMDIChildFrame();
					return c;
				}
			}
		}
		
		//create then new frame
		MDIChildFrame frame = new MDIChildFrame(child.getTitle(), true, true, true, true, child);
		
		//pack works but the child panels need to use setPreferredSize to tell pack how much space they need
		//otherwise, MDI children default to a standard size that I find too small
		frame.pack();
		frame.setLocation(newFrameX, newFrameY);
		
		//tile its position
		newFrameX = (newFrameX + 10) % desktop.getWidth(); 
		newFrameY = (newFrameY + 10) % desktop.getHeight(); 
		desktop.add(frame);
		//show it
		frame.setVisible(true);
		
		//add child to openViews
		openViews.add(child);
		
		return frame;
	}
	
	//display a child's message in a dialog centered on MDI frame
	public void displayChildMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	/**
	 * When MDIChild closes, we need to unregister it from the list of open views
	 * @param child
	 */
	public void removeFromOpenViews(MDIChild child) {
		openViews.remove(child);
	}
	
}