package gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Calendar extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8277958209048953251L;
	
		JLabel welcome = new JLabel("Welcome to CBS Calendar");
		JPanel panel = new JPanel();
		JButton editCalendar = new JButton("Edit Calendar");
		JButton editEvent = new JButton("Edit Event");
		JButton btnRandomQuote = new JButton("Random quote");
		JButton btnForecast = new JButton("Forecast");
		JButton logOut = new JButton("Log out");
		
		public Calendar(){
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
			
			editCalendar.setBounds(25, 140, 105, 39);
			panel.add(editCalendar);
			
			editEvent.setBounds(25, 191, 105, 39);
			panel.add(editEvent);
			
			btnRandomQuote.setBounds(25, 242, 105, 39);
			panel.add(btnRandomQuote);
			
			btnForecast.setBounds(25, 293, 105, 39);
			panel.add(btnForecast);
			
			logOut.setBounds(895, 6, 105, 39);
			panel.add(logOut);
			
			getContentPane().add(panel);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			editCalendar.addActionListener(new ActionEditCalendar());
			editEvent.addActionListener(new ActionEditEvent());
			btnRandomQuote.addActionListener(new ActionRandomQuote());
			btnForecast.addActionListener(new ActionForecast());
			logOut.addActionListener(new ActionLogOut());
			
		}
			
		public class ActionEditCalendar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
					EditCalendar editcalendar = new EditCalendar();
					editcalendar.setVisible(true);
					}
			}
		
		public class ActionEditEvent implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
					EditEvent editevent = new EditEvent();
					editevent.setVisible(true);
					}
		}
			
		public class ActionRandomQuote implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
					RandomQuote randomquote = new RandomQuote();
					randomquote.setVisible(true);
					
				}
				
			}
		
		public class ActionForecast implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				ForecastDay forecast = new ForecastDay();
				forecast.setVisible(true);
				
			}
			
		}
		
		public class ActionLogOut implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Login login = new Login();
					login.setVisible(true);
					dispose();
					}
			}
}
