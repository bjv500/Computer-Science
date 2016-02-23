package models;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

public class Warehouse extends Observable{

	private static long id; //unique part ID
	private String wName, address, city, state, zip;
	private int capacity;
	public WarehouseList warehouselist = new WarehouseList();
	
	public Warehouse() {
		id = UUID.randomUUID().getLeastSignificantBits();  //create id for part
		wName = address = city = state = zip = "";
		capacity = 0;
	}
	
	public Warehouse(String name, String add, String ncity, String nstate, String nzip, int ncapacity) {
		this();
		
		if(!validName(name))
			throw new IllegalArgumentException("Invalid name!");
		if(!validAddress(add))
			throw new IllegalArgumentException("Invalid address!");
		if(!validZip(nzip))
			throw new IllegalArgumentException("Invalid zip code!");
		if(!validCity(ncity))
			throw new IllegalArgumentException("Invalid city!");
		if(!validState(nstate))
			throw new IllegalArgumentException("Invalid state!");
		if(ncapacity <= 0 )
			throw new IllegalArgumentException("Invalid capacity!");
		wName = name;
		address = add;
		city = ncity;
		state = nstate;
		zip = nzip;
		this.capacity = ncapacity;
		

	}
	
	public long getId(){
		return id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return wName;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public String getZip(){
		return zip;
	}
	
	public int getCap(){
		return capacity;
	}
	
	public void finishUpdate() {
		notifyObservers();
	}

	public void setName(String cName) {
		if(!validName(cName))
			throw new IllegalArgumentException("Invalid name!");
		wName = cName;
		setChanged();
		
	}

	public void setAddress(String cAddress) {
		if(!validAddress(cAddress))
			throw new IllegalArgumentException("Invalid address!");
		address = cAddress;
		setChanged();
		
	}

	public void setCity(String cCity) {
		if(!validCity(cCity))
			throw new IllegalArgumentException("Invalid city!");
		city = cCity;
		setChanged();
		
	}

	public void setState(String cState) {
		if(!validState(cState))
			throw new IllegalArgumentException("Invalid state!");
		state = cState;
		setChanged();
	}

	public void setZip(String cZip) {
		if(!validZip(cZip))
			throw new IllegalArgumentException("Invalid zip code!");
		zip = cZip;
		setChanged();
		
	}

	public void setCap(int intcap) {
		if(!validCap(intcap))
			throw new IllegalArgumentException("Invalid zip capacity!");		
		capacity = intcap;
		setChanged();
		
	}

	private boolean validCap(int intcap) {
		if(intcap < 0)
			return false;
		return true;
	}

	public long getnewId() {

		return UUID.randomUUID().getLeastSignificantBits();
	}

	public boolean validZip(String zip) {
		if(zip == null)
			return false;
		if(zip.length() != 5)
			return false;
		return true;
	}
	
	public boolean validAddress(String add) {
		if(add == null)
			return false;
		if(add.length() > 255)
			return false;
		if(add == "")
			return false;
		return true;
	}
	
	public boolean validName(String name) {	
		if(name == "")
			return false;
		if(name == null)
			return false;
		if(name.length() > 255)
			return false;
		
		if(warehouselist.getList().isEmpty()) {
			return true;
		}
	    for(Warehouse w : warehouselist.getList()){
	        if(w.getName().contains(name) && this.equals(w))
	           return false;
	    }
		return true;
		
		
	}
	
	public boolean validCity(String city) {
		if(city == "")
			return false;
		if(city == null)
			return false;
		if(city.length() > 100)
			return false;
		return true;
	}
	
	public boolean validState(String state) {
		if(state == "")
			return false;
		if(state == null)
			return false;
		if(state.length() > 50)
			return false;
		return true;
	}
	
	
	
}
