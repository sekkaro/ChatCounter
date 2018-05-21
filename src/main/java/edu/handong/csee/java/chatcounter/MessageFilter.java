package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;

public class MessageFilter extends ChatMessageCounter{
	ArrayList<String> messages = new ArrayList<String>();
	String[] names;
	
	public MessageFilter() {
	}
	
	public MessageFilter(ArrayList<String> messages) {
		super();
		this.messages = messages;
	}
	
	public void convertToString() {
		String strMessage1 = messages.toString().replace("[","").replace("]","").replace(" ","");
		String[] messages = strMessage1.split(",");
		names = getNames(messages);
		
		super.setdata(messages, names);
		super.calculateCounter();
	}
	
	private String[] getNames(String[] messages) {
		ArrayList<String> names = new ArrayList<String>();
		for(String name : messages) {
			if(!names.contains(name) && !name.equals("")) {
				names.add(name);
			}
		}
		String name = names.toString().replace("[", "").replace("]", "").replace(" ","");
		return name.split(",");
	}
}
