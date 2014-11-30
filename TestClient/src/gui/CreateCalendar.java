package gui;

import gui.Calendar;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.swing.*;

import logic.ServerConnection;
import shared.CreateCalendarObject;
import shared.CreateCalendarReturnObject;
import shared.LogInReturnObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

	public class CreateCalendar extends JFrame{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 128743479876712760L;
		
//		public static void main(String[]args){
//			CreateCalendar frameTabel = new CreateCalendar();
//			
//		}
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		JButton btncreate = new JButton("Create Calendar");
		JPanel panel = new JPanel();
		JTextField txtCname = new JTextField(30);
		JTextField txtPrivPub = new JTextField(30);
		JTextField txtUser = new JTextField(30);
		JTextField txtAuthor = new JTextField(30);
		JButton btnAddUser = new JButton("Add");
		JButton btnAddAuthors = new JButton("Add");
		JTextArea textAreaUsers = new JTextArea();
		JTextArea textAreaAuthors = new JTextArea();
		JLabel PW = new JLabel("Please write 1 for private and 0 for public");
		JLabel HE = new JLabel("Add Calendar");
		JLabel CN = new JLabel("Calendar name: ");
		JLabel PP = new JLabel ("Is it private or public?");
		JLabel US = new JLabel("Insert username for users who shall be connected to this calendar: ");
		JLabel AU = new JLabel("Insert username of users who are allowed to edit in this calender");
		
		public CreateCalendar(){
			super("Create Calendar");
			setSize(550,550);
			setLocation(500,280);
			panel.setLayout(null);
			
			
			txtCname.setBounds(187,80,150,20);
			txtPrivPub.setBounds(187,112,150,20);
			txtUser.setBounds(35,346,150,20);
			txtAuthor.setBounds(35,183,150,20);
			btncreate.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			btncreate.setBounds(35,482,150,40);
			btnAddUser.setBounds(187, 343, 67, 29);
			btnAddAuthors.setBounds(187, 180, 67, 29);
			textAreaUsers.setBounds(266, 348, 150, 117);
			textAreaAuthors.setBounds(266, 183, 150, 117);
			HE.setFont(new Font("Arial", Font.BOLD, 30));
			HE.setForeground(new Color(0, 0, 128));
			HE.setBounds(196, 0, 262, 74);
			CN.setBounds(35, 75, 115, 30);
			PP.setBounds(35, 117, 150, 15);
			US.setBounds(35, 311, 423, 30);
			AU.setBounds(35, 156, 412, 15);
			PW.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			PW.setBounds(344, 115, 198, 16);
			
			
			panel.add(txtCname);
			panel.add(txtPrivPub);
			panel.add(txtUser);
			panel.add(txtAuthor);
			panel.add(btncreate);
			panel.add(btnAddUser);
			panel.add(btnAddAuthors);
			panel.add(textAreaUsers);
			panel.add(textAreaAuthors);
			panel.add(HE);
			panel.add(CN);
			panel.add(PP);
			panel.add(US);
			panel.add(AU);
			panel.add(PW);
			
			getContentPane().add(panel);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			actionCreateCalendar();
			btnAddAuthors.addActionListener(new ActionAddAuthors());
			btnAddUser.addActionListener(new ActionAddUser());
		}
		
		
				public void actionCreateCalendar(){
					btncreate.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){
							String cname = txtCname.getText();
							String privpub = txtPrivPub.getText();
							CreateCalendarObject createcalendar = new CreateCalendarObject(cname, privpub, user, author);
							createcalendar.setCalendarName(cname);
							createcalendar.setPrivatePublic(privpub);
							createcalendar.setUsers(user);
							createcalendar.setAuthors(author);
							Gson gson = new Gson();
							String jsonString = gson.toJson(createcalendar);
							ServerConnection connection = new ServerConnection();
							CreateCalendarReturnObject createcalendarreturn = new CreateCalendarReturnObject();
							try {
								createcalendarreturn = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), CreateCalendarReturnObject.class);
							} catch (JsonSyntaxException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							if(createcalendarreturn.isCreated()){
								JOptionPane.showMessageDialog(null, createcalendarreturn.getMessage());
//								dispose();
							}else{
								
								JOptionPane.showMessageDialog(null, createcalendarreturn.getMessage());
								user.clear();
								author.clear();
							}
						}
					});
				}
			
				public class ActionAddAuthors implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						author.add(txtAuthor.getText());
						String arrayListOutput = "";
						for (String i: author){
							arrayListOutput = arrayListOutput.concat(i);
							arrayListOutput = arrayListOutput.concat("\n");
						}
						textAreaAuthors.setText(arrayListOutput);
						
					}
					
				}
				
				
				public class ActionAddUser implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						user.add(txtUser.getText());
						String arrayListOutput = "";
						for (String i: user){
							arrayListOutput = arrayListOutput.concat(i);
							arrayListOutput = arrayListOutput.concat("\n");
						}
						textAreaUsers.setText(arrayListOutput);
						
					}
					
				}
	}
	


