package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFilter extends ChatMessageCounter{
	ArrayList<String> listOfNames = new ArrayList<String>();
	ArrayList<String> names = new ArrayList<String>();
	
	public MessageFilter() {
		
	}
	
	public MessageFilter(ArrayList<String> listOfNames) {
		super();
		this.listOfNames = listOfNames;
	}
	
	public void calculateNames() {
		names = getNames(listOfNames);
		
		super.setdata(listOfNames, names);
		super.calculateCounter();
	}
	
	private ArrayList<String> getNames(ArrayList<String> listOfNames) {
		ArrayList<String> names = new ArrayList<String>();
		for(String name : listOfNames) {
			if(!names.contains(name)) {
				names.add(name);
			}
		}
		return names;
	}
	
	@Override
	public HashMap<String,Integer> getHashMap(){
		return super.getHashMap();
	}
	@Override
	public ArrayList<String> getNames(){
		return super.getNames();
	}
}
