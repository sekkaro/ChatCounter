package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DataReaderForCSV {
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> wholeMessages = new ArrayList<String>();
	Pattern p = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):\\d+,\"(.+)\",\"(.+)\"");  
	String msg,name;
	int year,month,day,hour,min;
	boolean readName = false;
	public void readFiles(File file) {
		this.wholeMessages.addAll(wholeMessages);
		names.clear();
			try {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				String line;
				while((line=inputStream.readLine())!=null) {
					Matcher m = p.matcher(line);
					if(m.find()) {
						year = Integer.parseInt(m.group(1));
						month = Integer.parseInt(m.group(2));
						day = Integer.parseInt(m.group(3));
						hour = Integer.parseInt(m.group(4));
						min = Integer.parseInt(m.group(5));
						name = m.group(6);
						msg = m.group(7);
						if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
							wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg);
							readName = true;
						}
						if(readName) {
							names.add(name);
							readName=false;
						}
					}
				}
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
	public ArrayList<String> getNames(){
		return names;
	}
	
	public ArrayList<String> getMessages(){
		return wholeMessages;
	}
	
	public void addMessages(ArrayList<String> wholeMessages) {
		for(String message : wholeMessages) {
			if(!this.wholeMessages.contains(message)) {
				this.wholeMessages.add(message);
			}
		}
	}
}
