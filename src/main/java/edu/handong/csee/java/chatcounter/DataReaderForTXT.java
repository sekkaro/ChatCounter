package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;

public class DataReaderForTXT {
	ArrayList<String> messages = new ArrayList<String>();
	ArrayList<String> subMessages = new ArrayList<String>(); 
	public void readFiles(File file) {
			messages.clear();
			try {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				String line;
				while((line=inputStream.readLine())!=null) {
					 if(line.contains("[")) {
						 String[] data = line.substring(1, line.length() - 1).split("]",-1);
						 messages.add(data[0]);
					 }
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public String getMessages(){
		return messages.toString().replace("[","").replace("]","");
	}
}
