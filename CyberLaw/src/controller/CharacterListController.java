package controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import models.Character;
import models.CharacterList;


public class CharacterListController extends AbstractListModel<Character> implements Observer {

	private List<Character> myList;
	private MDIChild myListView;
	
	
	public CharacterListController(CharacterList wl){
		super();
		myList = wl.getList();
		registerAsObserver();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		myListView.repaint();
		
	}

	@Override
	public Character getElementAt(int index) {
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
		for(Character w: myList)
			w.addObserver(this);
	}
	
	public void unregisterAsObserver() {
		for(Character w: myList)
			w.deleteObserver(this);
		
	}
	
	public MDIChild getMyListView() {
		return myListView;
	}

	public void addtolist(Character w){
		myList.add(w);
		int i = myList.indexOf(w);
		fireIntervalAdded(this,i,i);
	}

}
