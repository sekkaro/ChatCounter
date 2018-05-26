package edu.handong.csee.java.chatcounter;

import java.util.*;
import java.io.*;

public class DataWriter {
	HashMap<String,Integer> messageCounter = new HashMap<>();
	ArrayList<String> names = new ArrayList<>(); 
	public DataWriter(){
		
	}
	public DataWriter(HashMap<String,Integer> messageCounter,ArrayList<String> names) {
		this.messageCounter = messageCounter;
		this.names = names;
	}
	public void writeFile(File file) {
		try {
			BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));
			outputStream.write("kakao_id,count\n");
			for(String name:names) {
				outputStream.write(name+","+messageCounter.get(name)+"\n");
			}
			outputStream.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
