package models;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Character extends Observable{

	private int id, happiness;
	private boolean selfish;

	public CharacterList Characterlist = new CharacterList();
	
	public Character() {
		
	}
	
	public Character(CharacterList myList) {
		id = myList.getSize() + 1;
		setHappiness(5);
		setSelfish(false);
	}
	
	public Character(int Id, int Happiness, boolean Selfish) {
		this();
		id = Id;
		happiness = Happiness;
		selfish = Selfish;
		

	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int Id){
		id = Id;
	}

	public int setHappiness(int nHappiness) {
		happiness = nHappiness;
		return 0;
	}

	public int getHappiness() {
		return happiness;
	}

	public boolean isSelfish() {
		return selfish;
	}

	public void setSelfish(boolean selfish) {
		this.selfish = selfish;
	}

	public void finishUpdate() {
		notifyObservers();
	}	
}
