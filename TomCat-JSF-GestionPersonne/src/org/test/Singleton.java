package org.test;

public class Singleton {
	
	private static Singleton instance;
	
	
	public static Singleton getInstance() throws Exception {
		if (instance == null){
			instance = new Singleton();
		}
		return instance; 
	}

	private Singleton(){
		
	}
}
