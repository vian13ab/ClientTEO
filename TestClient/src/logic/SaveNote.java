package logic;

import java.io.Serializable;

import javax.swing.JOptionPane;

import shared.Encryption;
import shared.SaveNoteObject;

import com.google.gson.*;

public class SaveNote implements Serializable{
	
		private static final long serialVersionUID = -8026572303276942230L;

		public static void main(String[] args){
			SaveNote instance = new SaveNote();
			instance.setUserEmailFromGUI();
			instance.setNoteContentFromGUI();
			instance.setEventNameFromGUI();
			instance.javaToJson();
			instance.encryptSaveNoteObject();
			
		}
		
		SaveNoteObject saveNoteObject = new SaveNoteObject();
		Encryption encryptedSaveNoteObject = new Encryption();
		Gson gson = new Gson();
		String json;
		
		private void setUserEmailFromGUI(){
			saveNoteObject.setUserEmail(JOptionPane.showInputDialog("Enter user email: "));
			
			System.out.println(saveNoteObject.getUserEmail());
		}
		
		private void setNoteContentFromGUI(){
			saveNoteObject.setNoteContent(JOptionPane.showInputDialog("Enter note content: "));
			
			System.out.println(saveNoteObject.getNoteContent());
		}
		
		private void setEventNameFromGUI(){
			saveNoteObject.setEventName(JOptionPane.showInputDialog("Enter event name: "));
			
			System.out.println(saveNoteObject.getEventName());
		}
		
		private void javaToJson(){
			json = gson.toJson(saveNoteObject);
			System.out.println(json);
		}	
			//write converted json data to a file named "CountryGSON.json"  		
//			try {  
//			   FileWriter writer = new FileWriter("/Users/hogni/Desktop/HHPGSON.json");  
//			   writer.write(json);  
//			   writer.close();  
//			    
//			  } catch (IOException e) {  
//			   e.printStackTrace();  
//			  }  

		private void encryptSaveNoteObject(){
//			encryptedSaveNoteObject.encrypt(json);
			System.out.println(encryptedSaveNoteObject.encrypt(json));
		}
		
		
			
	}

