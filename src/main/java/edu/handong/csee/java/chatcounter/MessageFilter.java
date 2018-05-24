package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

public class MessageFilter extends ChatMessageCounter{
	ArrayList<String> messages = new ArrayList<String>();
	ArrayList<String> names = new ArrayList<String>();
	
	public MessageFilter() {
	}
	
	public MessageFilter(ArrayList<String> messages) {
		super();
		this.messages = messages;
	}
	
	public void calculateNames() {
		names = getNames(messages);
		
		super.setdata(messages, names);
		super.calculateCounter();
	}
	
	private ArrayList<String> getNames(ArrayList<String> messages) {
		ArrayList<String> names = new ArrayList<String>();
		for(String name : messages) {
			if(!names.contains(name)) {
				names.add(name);
			}
		}
		return names;
	}
}
