package gui;

import gui.Calendar;

import java.util.ArrayList;

import com.google.gson.Gson;

import javax.swing.*;

import shared.CreateCalendarObject;
import shared.CreateCalendarReturnObject;

	public class CreateCalendar{
		
				public void actionCreateCalendar(){
					addCalendar.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){
							String cname = txtCname.getText();
							String privpub = txtPrivPub.getText();
							ArrayList<String> user = txtUser.getText();
							ArrayList<String> author = txtAuthor.getText
							CreateCalendarObject createcalendar = new CreateCalendarObject(privpub, privpub, author, author);
							createcalendar.setCalendarName(cname);
							createcalendar.setPrivatePublic(privpub);
							createcalendar.setUsers(user);
							createcalendar.setAuthors(author);
							Gson gson = new Gson();
							String jsonString = gson.toJson(login);
							ServerConnection connection = new ServerConnection();
							CreateCalendarReturnObject createcalendarreturn = new CreateCalendarReturnObject();
							createcalendarreturn = gson.fromJson(connection.execute(jsonString), CreateCalendarReturnObject.class);
							
							if(createcalendarreturn.isCreated()){
								Calendar calendar = new Calendar();
								JOptionPane.showMessageDialog(null, createcalendarreturn.getMessage());
								calendar.setVisible(true);
								dispose();
							}else{
								
								JOptionPane.showMessageDialog(null, createcalendarreturn.getMessage());
								Calendar calendar = new Calendar();
								calendar.setVisible(true);
								dispose();
							}
						}
					});
				}


	}

	

}
