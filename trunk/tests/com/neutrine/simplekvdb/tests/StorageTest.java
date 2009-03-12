package com.neutrine.simplekvdb.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.neutrine.simplekvdb.Item;
import com.neutrine.simplekvdb.Storage;

public class StorageTest {

	private Storage storage;
	
	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testPut() throws IOException, ClassNotFoundException {
		openDatabase();
		
		storage.put("car01", createItem());
		storage.put("car02", createItem());

		openDatabase();
		assertNotNull(storage.get("car01"));
		assertNotNull(storage.get("car02"));
	}

	@Test
	public void testGet() throws IOException, ClassNotFoundException {
		openDatabase();
		
		String key = "car";
		Item item = createItem();
		storage.put(key, item);
		
		openDatabase();
		assertNotNull(storage.get(key));
	}

	@Test
	public void testRemove() throws IOException, ClassNotFoundException {
		openDatabase();
		
		String key = "car";
		Item item = createItem();
		storage.put(key, item);
		
		openDatabase();
		assertNotNull(storage.get(key));
		
		storage.remove(key);
		
		openDatabase();
		assertNull(storage.get(key));
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	private void openDatabase() throws IOException, ClassNotFoundException {
		storage = new Storage(new File("database.data"));
	}
	
	private Item createItem() {
		Item item = new Item();
		item.put("model", "BMW");
		item.put("color", "black");
		return item;
	}

}
