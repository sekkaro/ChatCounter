package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

public class MessageFilter {
	ArrayList<String> messages = new ArrayList<String>();
	String[] names;
	
	public MessageFilter() {
		
	}
	
	public MessageFilter(ArrayList<String> messages) {
		this.messages = messages;
	}
	
	public void convertToString() {
		String strMessage1 = messages.toString().replace("[","").replace("]","").replace(" ","");
		String[] messages = strMessage1.split(",");
		names = getNames(messages);
		
	}
	
	private String[] getNames(String[] messages) {
		ArrayList<String> names = new ArrayList<String>();
		for(String name : messages) {
			if(!names.contains(name) && !name.equals("")) {
				names.add(name);
			}
		}
		String name = names.toString();
		return name.split(",");
	}
}
