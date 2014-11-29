package gui;

import gui.Calendar;

import java.util.ArrayList;

import com.google.gson.Gson;

import javax.swing.*;

import logic.ServerConnection;
import shared.CreateCalendarObject;
import shared.CreateCalendarReturnObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

	public class CreateCalendar extends JFrame{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 128743479876712760L;
		
		public static void main(String[]args){
			CreateCalendar frameTabel = new CreateCalendar();
			
		}
		JButton createCalendar = new JButton("Create Calendar");
		JPanel panel = new JPanel();
		JTextField txtCname = new JTextField(30);
		JTextField txtPrivPub = new JTextField(30);
		JTextField txtUser = new JTextField(30);
		JTextField txtAuthor = new JTextField(30);
		JLabel HE = new JLabel("Add Calendar");
		JLabel CN = new JLabel("Calendar name: ");
		JLabel PP = new JLabel ("Is it private or public, please write private or public:");
		JLabel US = new JLabel("Insert username for users who shall be connected to this calendar: ");
		JLabel AU = new JLabel("Insert your username: ");
		
		private CreateCalendar(){
			super("Create Calendar");
			setSize(700,400);
			setLocation(500,280);
			panel.setLayout(null);
			
			
			txtCname.setBounds(470,105,150,20);
			txtPrivPub.setBounds(470,169,150,20);
			txtUser.setBounds(470,201,150,83);
			txtAuthor.setBounds(470,137,150,20);
			createCalendar.setBounds(470,296,150,30);
			HE.setFont(new Font("Arial", Font.BOLD, 30));
			HE.setForeground(new Color(0, 0, 128));
			HE.setBounds(196, 6, 262, 74);
			CN.setBounds(35, 100, 115, 30);
			PP.setBounds(35, 172, 326, 15);
			US.setBounds(35, 196, 423, 30);
			AU.setBounds(34, 140, 150, 15);
			
			
			panel.add(txtCname);
			panel.add(txtPrivPub);
			panel.add(txtUser);
			panel.add(txtAuthor);
			panel.add(createCalendar);
			panel.add(HE);
			panel.add(CN);
			panel.add(PP);
			panel.add(US);
			panel.add(AU);
			
			getContentPane().add(panel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			actionCreateCalendar();
		}
		
		
				public void actionCreateCalendar(){
					createCalendar.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){
							String cname = txtCname.getText();
							String privpub = txtPrivPub.getText();
//							ArrayList<String> user = txtUser.getText();
//							ArrayList<String> author = txtAuthor.getText();
							CreateCalendarObject createcalendar = new CreateCalendarObject(privpub, privpub, author, author);
							createcalendar.setCalendarName(cname);
							createcalendar.setPrivatePublic(privpub);
//							createcalendar.setUsers(user);
//							createcalendar.setAuthors(author);
							Gson gson = new Gson();
							String jsonString = gson.toJson(createcalendar);
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

	


