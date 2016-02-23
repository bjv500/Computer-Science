package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import models.Warehouse;
import models.WarehouseList;

public class TestWarehouseList {
	
	static WarehouseList warehouseList = new WarehouseList();
	
	@Before
	public void createshit(){
	
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Alpha", "1234 Main", "Big City", "Texas", "12345", 1000));
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Bravo", "1234 Main", "Big City", "Texas", "12345", 1000));
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Charlie", "1234 Main", "Big City", "Texas", "12345", 1000));
	}

	@Test
	public void testnewWarehouseList() {
		WarehouseList wl = new WarehouseList();
		assertNotNull(wl);	
	}
	
	@Test
	public void testDeleteWarehouse(){	
	}

	@Test
	public void testAddWarehouse(){
		Warehouse newWarehouse = new Warehouse("Warehouse Delta", "1234 Main", "Big City", "Texas", "12345", 1000);
		warehouseList.addWarehouseToList(newWarehouse);
	}
	
}
