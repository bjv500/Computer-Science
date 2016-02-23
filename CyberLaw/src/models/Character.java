package models;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Character extends Observable{

	private static int id, happiness;
	private boolean state;
	private int size;

	public CharacterList Characterlist = new CharacterList();
	
	
	public Character() {
		id = 0;
		happiness = 0;
		state = false;
	}
	
	public Character(int tid, int thappiness, boolean tstate) {

		id = tid;
		happiness = thappiness;
		state = tstate;
	}
	
	public long getId(){
		return id;
	}

	public boolean getState(){
		return state;
	}
	
	public int getHappiness(){
		return happiness;
	}
	
	public int setId( int tid){
		id = tid;
		return id;
	}

	public boolean setState(boolean tstate){
		return state =  tstate;
	}
	
	public int setHappiness(int thappiness){
		return happiness = thappiness;
	}

}
