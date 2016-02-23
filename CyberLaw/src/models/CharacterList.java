package models;

import java.util.List;
import java.util.ArrayList;

public class CharacterList {
	private ArrayList<Character> myList;
	private int size;
	
	public CharacterList(){
		myList = new ArrayList<Character>();
		setSize(myList.size());
	}
	
	public void addCharacterToList(Character w){
		myList.add(w);
		
	}
	
	public Character removeCharacterFromList(Character w) {
		if(myList.contains(w)) {
			myList.remove(w);
			return w;
		}
		return null;
	}
	
	public List<Character> getList() {
		return this.myList;
	}
	
	public void setList(ArrayList<Character> myList) {
		this.myList = myList;
		
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
