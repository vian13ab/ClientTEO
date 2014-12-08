package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import logic.ServerConnection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.Event;
import shared.GetCalendarObject;
import shared.GetCalendarReturnObject;

public class EditCalendar extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822685275169969878L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Edit Calendar");
	JButton createCalendar = new JButton("Create calendar");
	JButton deleteCalendar = new JButton("Delete calendar");
	JButton share = new JButton("Share...");
	JTextArea list = new JTextArea();
	JTextField username = new JTextField("Enter your username");
	JButton showCalendars = new JButton("Show events");
	JScrollPane scrollPane = new JScrollPane();
	
	public EditCalendar(){
		super("Edit Calendar");
		setSize(400, 450);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		
		HD.setBounds(135, 6, 144, 33);
		createCalendar.setBounds(6, 375, 124, 47);
		deleteCalendar.setBounds(270, 375, 124, 47);
		share.setBounds(139, 375, 124, 47);
		showCalendars.setBounds(242, 39, 124, 29);
		username.setBounds(31, 38, 214, 28);
		username.setColumns(10);
		scrollPane.setBounds(31, 78, 335, 285);
		scrollPane.setViewportView(list);
		list.setEditable(false);
		
		panel.add(HD);
		panel.add(createCalendar);
		panel.add(deleteCalendar);
		panel.add(scrollPane);
		panel.add(username);
		panel.add(showCalendars);
		panel.add(share);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		
		showCalendars.addActionListener(new ActionShowCalendars());
		createCalendar.addActionListener(new ActionCreateCalendar());
		deleteCalendar.addActionListener(new ActionDeleteCalendar());
		share.addActionListener(new ActionShare());
		
		
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
		
	}
	
	public class ActionShowCalendars implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GetCalendarObject getcaelendarobject = new GetCalendarObject();
			getcaelendarobject.setUserID(username.getText());
			Gson gson = new Gson();
			String jsonString = gson.toJson(getcaelendarobject);
			ServerConnection connection = new ServerConnection();
			GetCalendarReturnObject calendarreturnobject = new GetCalendarReturnObject();
			try {
				calendarreturnobject = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), GetCalendarReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			String calendars = "";
			for(ArrayList<Event> i: calendarreturnobject.getCalendars()){
				for(Event x: i){
					calendars = calendars.concat(x.getTitle());
					calendars = calendars.concat("\n");
				}
			}
			
			list.setText(calendars);
		}
		
	}
	
	public class ActionCreateCalendar implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CreateCalendar createcalendar = new CreateCalendar();
			createcalendar.setVisible(true);
			
		}
		
	}
	
	public class ActionDeleteCalendar implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			DeleteCalendar deletecalendar = new DeleteCalendar();
			deletecalendar.setVisible(true);
		}
	}
	
	public class ActionShare implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "This function is not available right now");
		}
	}
}
