package models;

import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.UUID;

public class Character extends Observable{

	private int id, happiness, selfish;
	Random rn = new Random();

	public CharacterList Characterlist = new CharacterList();
	
	public Character() {
		
	}
	
	public Character(CharacterList myList) {
		id = myList.getSize() + 1;
		setHappiness(rn.nextInt(100) + 1);
		setSelfish(rn.nextInt(3) + 0);
	}
	
	public Character(int Id, int Happiness, int Selfish) {
		this();
		id = Id;
		happiness = Happiness;
		selfish = Selfish;
	}
	
	public String getId(){
		return Integer.toString(id);
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
