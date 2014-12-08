package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.Event;
import shared.GetCalendarObject;
import shared.GetCalendarReturnObject;

import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import logic.ServerConnection;
import javax.swing.JScrollPane;

public class EditEvent extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4946099623438005761L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Edit Event");
	JButton createEvent = new JButton("Create event");
	JButton deleteEvent = new JButton("Delete Event");
	JButton note = new JButton("Note");
	JTextField username = new JTextField("Enter your username");
	JButton showCalendars = new JButton("Show events");
	JTextArea list = new JTextArea();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	
	public EditEvent(){
		super("Edit Event");
		setSize(400, 450);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		
		HD.setBounds(123, 6, 136, 33);
		createEvent.setBounds(6, 375, 124, 47);
		deleteEvent.setBounds(270, 375, 124, 47);
		note.setBounds(138, 375, 124, 47);
		username.setBounds(31, 38, 198, 30);
		showCalendars.setBounds(241, 38, 124, 30);
		
		panel.add(HD);
		panel.add(createEvent);
		panel.add(deleteEvent);
		panel.add(note);
		panel.add(username);
		panel.add(showCalendars);
		
		getContentPane().add(panel);
		scrollPane_1.setBounds(31, 80, 334, 283);
		
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(list);
		setDefaultCloseOperation(closeOperation());
		showCalendars.addActionListener(new ActionShowCalendars());
		createEvent.addActionListener(new ActionCreateEvent());
		deleteEvent.addActionListener(new ActionDeleteEvent());
		note.addActionListener(new ActionAddNote());
		
		list.setEditable(false);
		
		setVisible(true);
		
		
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
	
	public class ActionCreateEvent implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CreateEvent createevent = new CreateEvent();
			createevent.setVisible(true);
			
		}
		
	}
	
	public class ActionDeleteEvent implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			DeleteEvent deleteevent = new DeleteEvent();
			deleteevent.setVisible(true);
		}
		
	}
	
	public class ActionAddNote implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){
			EditNote editnote = new EditNote();
			editnote.setVisible(true);
		}
	}
}
