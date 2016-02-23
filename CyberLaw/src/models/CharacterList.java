package models;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


import java.util.ArrayList;

public class CharacterList extends Observable implements Observer{

	private static List<Character> myList = new ArrayList<Character>();
	private int size;
	
	public CharacterList(){
		myList = new ArrayList<Character>();
		setSize(myList.size());
	}
	
	private void setSize(int size2) {
		size = size2;
		
	}

	public void addCharacterToList(Character w){
		myList.add(w);
		w.addObserver(this);
		this.notifyObservers();
		
	}
	
	public Character removeCharacterFromList(Character w) {
		if(myList.contains(w)) {
			myList.remove(w);
			return w;
		}
		this.notifyObservers();
		return null;
	}
	
	public List<Character> getList() {
		return myList;
	}
	
	public void setList(ArrayList<Character> nmyList) {
		myList = nmyList;
		
	}

	public int getSize() {
		return size;
	}



	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
		
	}
	
}
