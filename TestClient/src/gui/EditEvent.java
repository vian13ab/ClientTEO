package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class EditEvent extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4946099623438005761L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Edit Calendar");
	JButton createEvent = new JButton("Create event");
	JButton deleteEvent = new JButton("Delete Event");
	JButton addNote = new JButton("Add Note");
	
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
		addNote.setBounds(138, 375, 124, 47);
		
		panel.add(HD);
		panel.add(createEvent);
		panel.add(deleteEvent);
		panel.add(addNote);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		createEvent.addActionListener(new ActionCreateEvent());
		deleteEvent.addActionListener(new ActionDeleteEvent());
		addNote.addActionListener(new ActionAddNote());
		
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
		
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
			AddNote addnote = new AddNote();
			addnote.setVisible(true);
		}
	}

	

}
