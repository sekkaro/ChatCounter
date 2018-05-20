package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;

public class DataReaderForCSV {
	ArrayList<String> messages = new ArrayList<String>();
	
	public void readFiles(File file) {
			messages.clear();
			try {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				String line;
				while((line=inputStream.readLine())!=null) {
					if(line.contains("\"")) {
						messages.add(line.split("\"",-1)[1]);
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
