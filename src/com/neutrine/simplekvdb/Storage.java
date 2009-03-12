package com.neutrine.simplekvdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
	private File file;
	private Map<String, Item> store;

	public Storage(File file) throws IOException, ClassNotFoundException {
		this.file = file;
		load();
	}
	
	public void put(String key, Item value) throws IOException {
		store.put(key, value);
		save();
	}
	
	public Item get(String key) {
		return store.get(key);
	}
	
	public void remove(String key) throws IOException {
		store.remove(key);
		save();
	}
	
	public void update(String key, Item value) throws IOException {
		store.remove(key);
		store.put(key, value);
		save();
	}

	private synchronized void load() throws IOException, ClassNotFoundException {
		if (file.exists()) {
			
			FileInputStream fis = new FileInputStream(file);
			
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);
				store = (Map<String, Item>)ois.readObject();
				
			} finally {
				fis.close();
			}
			
		} else {
			store = new HashMap<String, Item>();
		}
	}

	private synchronized void save() throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(store);
		} finally {
			fos.close();
		}
	}
}
