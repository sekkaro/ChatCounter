package edu.handong.csee.java.chatcounter;

import java.util.*;

public class ChatMessageCounter {
	ArrayList<String> listOfNames,names;
	HashMap<String,Integer> messageCounter = new HashMap<>();
	HashMap<String,Integer> sortedMessageCounter = new HashMap<>();
	ArrayList<Integer> counter = new ArrayList<Integer>();
	ArrayList<String> sortedNames= new ArrayList<String>();
	public ChatMessageCounter(){
		
	}
	public void setdata(ArrayList<String> listOfNames,ArrayList<String> names) {
		this.listOfNames = listOfNames;
		this.names = names;
	}
	public void calculateCounter() {
		int count=0;
		for(String name:names) {
			for(String listOfName:listOfNames) {
				if(name.equals(listOfName)) {
					count++;
				}
			}
			counter.add(count);
			messageCounter.put(name,count);
			count=0;
		}
		
		Collections.sort(counter);
		Collections.reverse(counter);
		for(int counter : counter) { 
			for(String name : names) {
			if(!sortedNames.contains(name)&&(messageCounter.get(name)==counter)) {
			sortedNames.add(name);
			sortedMessageCounter.put(name,messageCounter.get(name));
				}
			}
		}
		
		/*for(String name: sortedNames) {
			System.out.println(name+":"+sortedMessageCounter.get(name));
		}*/
		
	}
	
	public HashMap<String,Integer> getHashMap(){
		return sortedMessageCounter;
	}
	
	public ArrayList<String> getNames(){
		return sortedNames;
	}
	
}
