package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.MDIParent;
import models.Warehouse;
import models.WarehouseList;

public class TestWarehouse {
	
	WarehouseList warehouseList = new WarehouseList();
	MDIParent p;
	@Before
	public void createshit(){
	
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Alpha", "1234 Main", "Big City", "Texas", "12345", 1000));
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Bravo", "1234 Main", "Big City", "Texas", "12345", 1000));
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Charlie", "1234 Main", "Big City", "Texas", "12345", 1000));
		
		
		p = new MDIParent("Test", warehouseList);
	}

	@Test
	public void testnewWarehouse() {
		Warehouse w = new Warehouse();
		warehouseList.addWarehouseToList(new Warehouse("Warehouse Delta", "1234 Main", "Big City", "Texas", "12345", 1000));
		assertNotNull(w);	
	}
	
	@Test
	public void testnewValidName() {
		Warehouse w = new Warehouse();
		w.setName("Test");
		assertEquals(w.getName(), "Test");	
	}
	
	@Test
	public void testnewValidAddress() {
		Warehouse w = new Warehouse();
		w.setAddress("123 Test");
		assertEquals(w.getAddress(), "123 Test");	
	}
	
	@Test
	public void testnewValidCity() {
		Warehouse w = new Warehouse();
		w.setCity("Test City");
		assertEquals(w.getCity(), "Test City");	
	}
	
	@Test
	public void testnewValidState() {
		Warehouse w = new Warehouse();
		w.setState("Testate");
		assertEquals(w.getState(), "Testate");	
	}
	
	@Test
	public void testnewValidZip() {
		Warehouse w = new Warehouse();
		w.setZip("12345");
		assertEquals(w.getZip(), "12345");	
	}
	
	@Test
	public void testnewValidcap() {
		Warehouse w = new Warehouse();
		w.setCap(1);
		assertEquals(w.getCap(), 1);	
	}
	
/*	@Test (expected = IllegalArgumentException.class)
	public void testDuplicate() {
		Warehouse w = new Warehouse();
		w.setName("Warehouse Alpha");
		
	}*/
	
	@Test (expected = IllegalArgumentException.class)
	public void testNametoolong() {
		Warehouse w = new Warehouse();
		w.setName("012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testZiptoolong() {
		Warehouse w = new Warehouse();
		w.setZip("123456");
		
	}

	
	@Test (expected = IllegalArgumentException.class)
	public void testAddresstoolong() {
		Warehouse w = new Warehouse();
		w.setAddress("012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStatetoolong() {
		Warehouse w = new Warehouse();
		w.setState("0123456789012345678901234567890123456789012345678901");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCitytoolong() {
		Warehouse w = new Warehouse();
		w.setCity("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891");
		
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testNamenull() {
		Warehouse w = new Warehouse();
		w.setName(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddressnull() {
		Warehouse w = new Warehouse();
		w.setAddress(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCitynull() {
		Warehouse w = new Warehouse();
		w.setCity(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStatenull() {
		Warehouse w = new Warehouse();
		w.setState(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testZipnull() {
		Warehouse w = new Warehouse();
		w.setZip(null);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCapNegative() {
		Warehouse w = new Warehouse();
		w.setCap(-1);	
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testZiptooshort() {
		Warehouse w = new Warehouse();
		w.setZip("1234");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNameIsBlank() {
		Warehouse w = new Warehouse();
		w.setName("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddressIsBlank() {
		Warehouse w = new Warehouse();
		w.setAddress("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCityIsBlank() {
		Warehouse w = new Warehouse();
		w.setCity("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStateIsBlank() {
		Warehouse w = new Warehouse();
		w.setState("");
	}
	
}
