package models;

import java.util.List;
import java.util.ArrayList;

public class WarehouseList {
	private List<Warehouse> myList;
	
	public WarehouseList(){
		myList = new ArrayList<Warehouse>();
	}
	
	public void addWarehouseToList(Warehouse w){
		myList.add(w);
		
	}
	
	public Warehouse removeWarehouseFromList(Warehouse w) {
		if(myList.contains(w)) {
			myList.remove(w);
			return w;
		}
		return null;
	}
	
	public List<Warehouse> getList() {
		return this.myList;
	}
	
	public void setList(List<Warehouse> myList) {
		this.myList = myList;
		
	}
	
}
