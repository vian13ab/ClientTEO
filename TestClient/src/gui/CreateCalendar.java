package gui;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.swing.*;

import logic.ServerConnection;
import shared.CreateCalendarObject;
import shared.CreateCalendarReturnObject;

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
		
		String msg = "";
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		JPanel panel = new JPanel();
		JButton btncreate = new JButton("Create Calendar");
		JButton cancel = new JButton("Cancel");
		JTextField txtCname = new JTextField(30);
		JTextField txtPrivPub = new JTextField(30);
		JTextField txtUser = new JTextField(30);
		JTextField txtAuthor = new JTextField(30);
		JButton btnAddUser = new JButton("Add");
		JButton btnAddAuthors = new JButton("Add");
		JTextArea textAreaUsers = new JTextArea();
		JTextArea textAreaAuthors = new JTextArea();
		JLabel PW = new JLabel("Please write 0 for private and 1 for public");
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
			btncreate.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			btncreate.setBounds(286,488,140,29);
			cancel.setBounds(219, 488, 67, 29);
			btnAddUser.setBounds(187, 343, 67, 29);
			btnAddAuthors.setBounds(187, 180, 67, 29);
			textAreaUsers.setBounds(266, 348, 150, 117);
			textAreaAuthors.setBounds(266, 183, 150, 117);
			HE.setFont(new Font("Arial", Font.BOLD, 30));
			HE.setForeground(new Color(0, 0, 128));
			HE.setBounds(187, 6, 198, 74);
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
			panel.add(cancel);
			panel.add(textAreaUsers);
			panel.add(textAreaAuthors);
			panel.add(HE);
			panel.add(CN);
			panel.add(PP);
			panel.add(US);
			panel.add(AU);
			panel.add(PW);
			
			getContentPane().add(panel);
			
			
			setDefaultCloseOperation(closeOperation());
			setVisible(true);
			btncreate.addActionListener(new ActionCreateCalendar());
			btnAddAuthors.addActionListener(new ActionAddAuthors());
			btnAddUser.addActionListener(new ActionAddUser());
			cancel.addActionListener(new ActionCancel());
		}
		public int closeOperation(){
			setVisible(false);
			return 1;
			
		}
		
		public class ActionCreateCalendar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
								msg = connection.connectToServerAndSendReturnObject(jsonString);
							} catch (JsonSyntaxException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							if(createcalendarreturn.isCreated()){
								JOptionPane.showMessageDialog(null, msg);
								EditEvent editevent = new EditEvent();
								editevent.setVisible(true);
								dispose();
							}else{
								
								JOptionPane.showMessageDialog(null, msg);
							}
						}
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
						txtAuthor.setText("");
						txtAuthor.requestFocus();
						
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
						txtUser.setText("");
						txtUser.requestFocus();
						
					}
					
				}
				
				public class ActionCancel implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
					
				}
	}
	


