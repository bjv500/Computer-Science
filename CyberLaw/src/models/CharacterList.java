package models;

import java.util.List;
import java.util.ArrayList;

public class CharacterList {
	private List<Character> myList;
	
	public CharacterList(){
		myList = new ArrayList<Character>();
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
	
	public void setList(List<Character> myList) {
		this.myList = myList;
		
	}

	public int getSize() {
		myList.size();
		return 0;
	}
	
}
