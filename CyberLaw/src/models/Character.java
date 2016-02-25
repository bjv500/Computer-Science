package models;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Character extends Observable{

	private int id, happiness, selfish;

	public CharacterList Characterlist = new CharacterList();
	
	public Character() {
		
	}
	
	public Character(CharacterList myList) {
		id = myList.getSize() + 1;
		setHappiness(0);
		setSelfish(0);
	}
	
	public Character(int Id, int Happiness, int Selfish) {
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

	public String isSelfish() {
		return this.getSelfishness();
	}

	public void setSelfish(int selfish) {
		this.selfish = selfish;
	}

	public void finishUpdate() {
		notifyObservers();
	}

	public String getSelfishness() {
		if(selfish == 1)
				return "Selfish";
		else if(selfish == 0)
				return "Not Selfish";
		else
			return "Mixed";
	}	
}
