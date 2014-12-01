package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.ServerConnection;
import shared.DeleteCalendarObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.Font;
import java.awt.Color;

public class DeleteCalendar extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5034047532157719179L;
	
	String msg = "";
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Delete Calendar");
	JLabel UN = new JLabel("Insert your username: ");
	JLabel CD = new JLabel("Please type in the calendar you want to delete: ");
	JTextField username = new JTextField();
	JTextField calToDelete = new JTextField();
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	
	public DeleteCalendar(){
		super("Delete Calendar");
		setSize(500, 250);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 30));
		
		HD.setBounds(145, 18, 234, 29);
		UN.setBounds(29, 89, 150, 20);
		CD.setBounds(29, 136, 295, 20);
		username.setBounds(336, 85, 144, 29);
		calToDelete.setBounds(336, 132, 144, 29);
		delete.setBounds(413, 176, 67, 29);
		cancel.setBounds(334, 176, 67, 29);
		
		panel.add(HD);
		panel.add(UN);
		panel.add(CD);
		panel.add(username);
		panel.add(calToDelete);
		panel.add(delete);
		panel.add(cancel);
		
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		
		delete.addActionListener(new ActionDelete());
		cancel.addActionListener(new ActionCancel());
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
		
	}
	
	public class ActionDelete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String uname = username.getText();
			String caldel = calToDelete.getText();
			DeleteCalendarObject deletecalobject = new DeleteCalendarObject();
			deletecalobject.setuserID(uname);
			deletecalobject.setCalendarToDelete(caldel);
			Gson gson = new Gson();
			String jsonString = gson.toJson(deletecalobject);
			ServerConnection connection = new ServerConnection();
			try {
				msg = connection.connectToServerAndSendReturnObject(jsonString);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
				JOptionPane.showMessageDialog(null, msg);
				calToDelete.setText("");
				calToDelete.requestFocus();
		}
	}
	
	public class ActionCancel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
			
		}
		
	}
	

