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
	JLabel description = new JLabel("Description: ");
	JLabel location = new JLabel("Location: ");
	JLabel createdby = new JLabel("Your username: ");
	JLabel calendarName = new JLabel("Calendar: ");
	JLabel explanation = new JLabel("Please enter the calendar you want your event to appear in");
	JLabel startDate = new JLabel("Start date: ");
	JLabel startTime = new JLabel("Start time: ");
	JLabel endDate = new JLabel("End date: ");
	JLabel explanationDateEnd = new JLabel("Please write in format xx.xx.xxxx");
	JLabel endTime = new JLabel("End time: ");
	JLabel explanationTimeEnd = new JLabel("Please write in 24 hour format");
	JTextField typ = new JTextField();
	JTextField eName = new JTextField();
	JTextField desc = new JTextField();
	JTextField loc = new JTextField();
	JTextField created = new JTextField();
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
		eventName.setBounds(27,165,150,20);
		description.setBounds(27,391,81,20);
		location.setBounds(27,230,67,20);
		createdby.setBounds(27,105,102,20);
		calendarName.setBounds(27, 133, 67, 20);
		explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanation.setBounds(348, 134, 288, 20);
		startDate.setBounds(27, 260, 77, 20);
		startTime.setBounds(27, 322, 77, 20);
		endDate.setBounds(27, 295, 67, 15);
		explanationDateEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanationDateEnd.setBounds(348, 261, 172, 20);
		endTime.setBounds(27, 361, 67, 15);
		explanationTimeEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanationTimeEnd.setBounds(348, 328, 157, 16);
		typ.setBounds(196,197,150,20);
		eName.setBounds(196,165,150,20);
		desc.setBounds(196,391,150,20);
		loc.setBounds(196,230,150,20);
		created.setBounds(196,105,150,20);
		calName.setBounds(196, 134, 150, 20);
		startD.setBounds(196, 260, 150, 20);
		startT.setBounds(196, 325, 150, 20);
		endD.setBounds(196, 292, 150, 20);
		endT.setBounds(196, 358, 150, 20);
		createEvent.setBounds(271, 437, 95, 20);
		cancel.setBounds(173, 437, 81, 20);
		
		panel.add(HD);
		panel.add(type);
		panel.add(eventName);
		panel.add(description);
		panel.add(location);
		panel.add(createdby);
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
		panel.add(created);
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
			String creat = created.getText();
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
			createeventobject.setCreatedby(creat);
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

}
