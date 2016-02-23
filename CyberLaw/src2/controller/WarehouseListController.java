package controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import models.Warehouse;
import models.WarehouseList;


public class WarehouseListController extends AbstractListModel<Warehouse> implements Observer {

	private List<Warehouse> myList;
	private MDIChild myListView;
	
	
	public WarehouseListController(WarehouseList wl){
		super();
		myList = wl.getList();
		registerAsObserver();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		myListView.repaint();
		
	}

	@Override
	public Warehouse getElementAt(int index) {
		if(index > getSize())
			throw new IndexOutOfBoundsException("Index " + index + " is out of list bounds!");
		return myList.get(index);
	}

	@Override
	public int getSize() {
		return myList.size();
	}

	public void setMyListView(MDIChild myListView) {
		this.myListView = myListView;
		
	}

	public void registerAsObserver() {
		//register with the observable models
		for(Warehouse w: myList)
			w.addObserver(this);
	}
	
	public void unregisterAsObserver() {
		for(Warehouse w: myList)
			w.deleteObserver(this);
		
	}
	
	public MDIChild getMyListView() {
		return myListView;
	}

	public void addtolist(Warehouse w){
		myList.add(w);
		int i = myList.indexOf(w);
		fireIntervalAdded(this,i,i);
	}

}
