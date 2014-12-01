package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class EditCalendar extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822685275169969878L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Edit Calendar");
	JButton createCalendar = new JButton("Create calendar");
	JButton deleteCalendar = new JButton("Delete calendar");
	
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
		
		panel.add(HD);
		panel.add(createCalendar);
		panel.add(deleteCalendar);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		createCalendar.addActionListener(new ActionCreateCalendar());
		deleteCalendar.addActionListener(new ActionDeleteCalendar());
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
		
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

}
