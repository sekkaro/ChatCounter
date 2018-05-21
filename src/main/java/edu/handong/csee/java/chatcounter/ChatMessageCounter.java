package edu.handong.csee.java.chatcounter;

import java.util.*;

public class ChatMessageCounter {
	private String[] message,names;
	HashMap<String,Integer> messageCounter = new HashMap<>();
	
	public ChatMessageCounter(){
		
	}
	public void setdata(String[] message,String[] names) {
		this.message = message;
		this.names = names;
	}
	public void calculateCounter() {
		int count=0;
		for(int i = 0 ; i < names.length ; i++) {
			for(int j = 0 ; j < message.length ; j++) {
				if(names[i].equals(message[j])) {
					count++;
				}
			}
			messageCounter.put(names[i],count);
			count=0;
		}
		
		for(int i=0; i < names.length ; i++) {
			System.out.println(names[i] + ":" + messageCounter.get(names[i]));
		}
	}
	
}
