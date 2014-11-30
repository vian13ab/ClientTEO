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
import java.awt.event.ActionListener;

	public class CreateCalendar extends JFrame{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 128743479876712760L;
		
		public static void main(String[]args){
			CreateCalendar frameTabel = new CreateCalendar();
			
		}
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		JButton btncreate = new JButton("Create Calendar");
		JPanel panel = new JPanel();
		JTextField txtCname = new JTextField(30);
		JTextField txtPrivPub = new JTextField(30);
		JTextField txtUser = new JTextField(30);
		JTextField txtAuthor = new JTextField(30);
		JButton btnAddUser = new JButton("Add");
		JTextArea textAreaUsers = new JTextArea();
		JLabel HE = new JLabel("Add Calendar");
		JLabel CN = new JLabel("Calendar name: ");
		JLabel PP = new JLabel ("Is it private or public?");
		JLabel US = new JLabel("Insert username for users who shall be connected to this calendar: ");
		JLabel AU = new JLabel("Insert username of users who are allowed to edit in this calender");
		private final JTextArea textAreaAuthors = new JTextArea();
		private final JButton btnAdd = new JButton("Add");
		
		public CreateCalendar(){
			super("Create Calendar");
			setSize(700,700);
			setLocation(500,280);
			panel.setLayout(null);
			
			
			txtCname.setBounds(187,80,150,20);
			txtPrivPub.setBounds(187,112,150,20);
			txtUser.setBounds(451,304,150,20);
			txtAuthor.setBounds(451,141,150,20);
			btncreate.setBounds(461,473,150,30);
			btnAddUser.setBounds(600, 138, 67, 29);
			textAreaUsers.setBounds(471, 336, 150, 117);
			HE.setFont(new Font("Arial", Font.BOLD, 30));
			HE.setForeground(new Color(0, 0, 128));
			HE.setBounds(196, 0, 262, 74);
			CN.setBounds(35, 75, 115, 30);
			PP.setBounds(35, 117, 150, 15);
			US.setBounds(35, 299, 423, 30);
			AU.setBounds(35, 144, 412, 15);
			
			
			panel.add(txtCname);
			panel.add(txtPrivPub);
			panel.add(txtUser);
			panel.add(txtAuthor);
			panel.add(btncreate);
			panel.add(btnAddUser);
			panel.add(textAreaUsers);
			panel.add(HE);
			panel.add(CN);
			panel.add(PP);
			panel.add(US);
			panel.add(AU);
			
			getContentPane().add(panel);
			textAreaAuthors.setBounds(461, 173, 150, 117);
			
			panel.add(textAreaAuthors);
			
			JLabel lblPleaseWrite = new JLabel("Please write 1 for private and 0 for public");
			lblPleaseWrite.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			lblPleaseWrite.setBounds(344, 115, 198, 16);
			panel.add(lblPleaseWrite);
			btnAdd.setBounds(600, 301, 67, 29);
			
			panel.add(btnAdd);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			actionCreateCalendar();
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
							createcalendarreturn = gson.fromJson(connection.execute(jsonString), CreateCalendarReturnObject.class);
							
							if(createcalendarreturn.isCreated()){
								JOptionPane.showMessageDialog(null, createcalendarreturn.getMessage());
								dispose();
							}else{
								
								JOptionPane.showMessageDialog(null, createcalendarreturn.getMessage());
								user.clear();
								author.clear();
							}
						}
					});
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
//	author.add(txtAuthor.getText());
	


