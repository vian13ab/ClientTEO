package gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Calendar extends JFrame{
	public static void main (String []args){
		Calendar frameTabel = new Calendar();
	}
		JLabel welcome = new JLabel("Welcome to CBS Calendar");
		JPanel panel = new JPanel();
		JButton addCalendar = new JButton("Add Calendar");
		JButton addEvent = new JButton("Add Event");
		JButton addNote = new JButton("Add Note");
		JButton share = new JButton("Share...");
		
		Calendar(){
			super("CBS Calendar");
			setSize(1000,1000);
			setLocation(500,280);
			getContentPane().setLayout(null);
			panel.setBounds(0, 0, 1000, 978);
			panel.setLayout(null);
			welcome.setForeground(SystemColor.textHighlight);
			welcome.setFont(new Font("Apple Braille", Font.PLAIN, 28));
			
			welcome.setBounds(310,-3,392,160);
			panel.add(welcome);
			
			addCalendar.setBounds(25, 140, 105, 39);
			panel.add(addCalendar);
			
			addEvent.setBounds(25, 200, 105, 39);
			panel.add(addEvent);
			
			
			addNote.setBounds(25, 260, 105, 39);
			panel.add(addNote);
			
			share.setBounds(25, 320, 105, 39);
			panel.add(share);
			
			getContentPane().add(panel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
//			actionCalendar action = new actionCalendar();
//			addCalendar.addActionListener(action);
//			addEvent.addActionListener(action);
//			addNote.addActionListener(action);
//			share.addActionListener(action);
			
		}
	
		public void actionPerformed(ActionEvent event){
			addCalendar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
			JOptionPane.showMessageDialog(null, "Wrong Password or Username");
					}
			});
		}

}


		


		
			
		
			
	

