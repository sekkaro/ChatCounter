package edu.handong.csee.java.chatcounter;

import java.util.*;

public class ChatMessageCounter {
	ArrayList<String> messages,names;
	HashMap<String,Integer> messageCounter = new HashMap<>();
	
	public ChatMessageCounter(){
		
	}
	public void setdata(ArrayList<String> messages,ArrayList<String> names) {
		this.messages = messages;
		this.names = names;
	}
	public void calculateCounter() {
		int count=0;
		for(String name:names) {
			for(String message:messages) {
				if(name.equals(message)) {
					count++;
				}
			}
			messageCounter.put(name,count);
			count=0;
		}
		
		for(String name : names) {
			System.out.println(name + ":" + messageCounter.get(name));
		}
	}
	
}
