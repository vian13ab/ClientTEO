package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.CreateEventObject;
import shared.CreateEventReturnObject;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import logic.ServerConnection;

public class CreateEvent extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159230638249490832L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Create event");
	JLabel type = new JLabel("What kind of event is it?");
	JLabel eventName = new JLabel("Event name: ");
	JLabel description = new JLabel("Undertitle:");
	JLabel location = new JLabel("Location: ");
	JLabel calendarName = new JLabel("Calendar: ");
	JLabel explanation = new JLabel("Please enter the calendar you want your event to appear in");
	JLabel startDate = new JLabel("Start date: ");
	JLabel startTime = new JLabel("Start time: ");
	JLabel endDate = new JLabel("End date: ");
	JLabel explanationDateEnd = new JLabel("Please write in format yyyy-mm-dd");
	JLabel endTime = new JLabel("End time: ");
	JLabel explanationTimeEnd = new JLabel("Please write in format hh:mm:ss in 24-hour format");
	JTextField typ = new JTextField();
	JTextField eName = new JTextField();
	JTextField desc = new JTextField();
	JTextField loc = new JTextField();
	JTextField calName = new JTextField();
	JTextField startD = new JTextField();
	JTextField startT = new JTextField();
	JTextField endD = new JTextField();
	JTextField endT = new JTextField();
	JButton createEvent = new JButton("Create event");
	JButton cancel = new JButton("Cancel");
	
	
	public CreateEvent(){
		super("Create Event");
		setSize(650, 500);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		
		HD.setBounds(220, 26, 205, 44);
		type.setBounds(27,197,157,20);
		eventName.setBounds(27,135,150,20);
		description.setBounds(27,167,81,20);
		location.setBounds(27,230,67,20);
		calendarName.setBounds(27, 103, 67, 20);
		explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanation.setBounds(348, 104, 288, 20);
		startDate.setBounds(27, 260, 77, 20);
		startTime.setBounds(27, 322, 77, 20);
		endDate.setBounds(27, 295, 67, 15);
		explanationDateEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanationDateEnd.setBounds(348, 261, 173, 20);
		endTime.setBounds(27, 361, 67, 15);
		explanationTimeEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanationTimeEnd.setBounds(348, 328, 260, 16);
		typ.setBounds(196,197,150,20);
		eName.setBounds(196,135,150,20);
		desc.setBounds(196,167,150,20);
		loc.setBounds(196,230,150,20);
		calName.setBounds(196, 104, 150, 20);
		startD.setBounds(196, 260, 150, 20);
		startT.setBounds(196, 325, 150, 20);
		endD.setBounds(196, 292, 150, 20);
		endT.setBounds(196, 358, 150, 20);
		createEvent.setBounds(269, 413, 95, 20);
		cancel.setBounds(171, 413, 81, 20);
		
		panel.add(HD);
		panel.add(type);
		panel.add(eventName);
		panel.add(description);
		panel.add(location);
		panel.add(calendarName);
		panel.add(explanation);
		panel.add(startDate);
		panel.add(startTime);
		panel.add(endDate);
		panel.add(explanationDateEnd);
		panel.add(endTime);
		panel.add(explanationTimeEnd);
		panel.add(typ);
		panel.add(eName);
		panel.add(desc);
		panel.add(loc);
		panel.add(calName);
		panel.add(startD);
		panel.add(startT);
		panel.add(endD);
		panel.add(endT);
		panel.add(createEvent);
		panel.add(cancel);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		createEvent.addActionListener(new ActionCreateEvent());
		cancel.addActionListener(new ActionCancel());
		
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
		
	}
	
	public class ActionCreateEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String ty = typ.getText();	
			String en = eName.getText();
			String des = desc.getText();
			String locat = loc.getText();
			String calNa = calName.getText();
			String starD = startD.getText();
			String starT = startT.getText();
			String enD = endD.getText();
			String enT = endT.getText();
			CreateEventObject createeventobject = new CreateEventObject();
			createeventobject.setType(ty);
			createeventobject.setEventName(en);
			createeventobject.setDescription(des);
			createeventobject.setLocation(locat);
			createeventobject.setCalendarName(calNa);
			createeventobject.setStartDate(starD);
			createeventobject.setStartTime(starT);
			createeventobject.setEndDate(enD);
			createeventobject.setEndTime(enT);
			Gson gson = new Gson();
			String jsonString = gson.toJson(createeventobject);
			ServerConnection connection = new ServerConnection();
			CreateEventReturnObject createeventreturnobject = new CreateEventReturnObject();
			try {
				createeventreturnobject = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), CreateEventReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if(createeventreturnobject.isCreated()){
				JOptionPane.showMessageDialog(null, createeventreturnobject.getMessage());
				dispose();
			}else{
				
				JOptionPane.showMessageDialog(null, createeventreturnobject.getMessage());
			}
		}
	}
		
		public class ActionCancel implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
				
			}

}
