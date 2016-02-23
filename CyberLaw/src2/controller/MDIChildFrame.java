package controller;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 * Subclassed JInternalFrame to give us access to internalFrameClosing event
 * allowing us to tell MDIChild panel to clean up (e.g., unregister from observed, etc.)
 * @author marcos
 *
 */
public class MDIChildFrame extends JInternalFrame implements InternalFrameListener {
	/**
	 * MDI child panel
	 */
	protected MDIChild myChild;

	/**
	 * Constructor from JInternalFrame
	 * also sets myChild instance variable and places the child panel in the center (should work for most layouts)
	 * @param title
	 * @param resizable
	 * @param closable
	 * @param maximizable
	 * @param iconifiable
	 */
	public MDIChildFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable
			, MDIChild child) {
		super(title, resizable, closable, maximizable, iconifiable);
		myChild = child;
		this.add(child, BorderLayout.CENTER);
		
		//add itself as a window listener
		this.addInternalFrameListener(this);
	}
	
	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Frame's method for telling MDI Child to clean up
	 * Having this method public allows MDIParent to access the frame's child and tell it to close
	 */
	public void closeChild() {
		myChild.childClosing();
	}
	
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// tell child to clean up (e.g., remove from MDIParent's openViews list
		closeChild();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

}
