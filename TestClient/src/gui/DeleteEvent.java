package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.DeleteEventObject;
import shared.DeleteEventReturnObject;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import logic.ServerConnection;

public class DeleteEvent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305776104349130233L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Delete Event");
	JLabel UN = new JLabel("Insert your username: ");
	JLabel ED = new JLabel("Please type in the event you want to delete: ");
	JTextField username = new JTextField();
	JTextField eventToDelete = new JTextField();
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	
	public DeleteEvent(){
		super("Delete Calendar");
		setSize(500, 250);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 30));
		
		HD.setBounds(163, 18, 188, 29);
		UN.setBounds(29, 89, 150, 20);
		ED.setBounds(29, 136, 295, 20);
		username.setBounds(336, 85, 144, 29);
		eventToDelete.setBounds(336, 132, 144, 29);
		delete.setBounds(413, 176, 67, 29);
		cancel.setBounds(334, 176, 67, 29);
		
		panel.add(HD);
		panel.add(UN);
		panel.add(ED);
		panel.add(username);
		panel.add(eventToDelete);
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
			String eventdel = eventToDelete.getText();
			DeleteEventObject deleteeventobject = new DeleteEventObject();
			deleteeventobject.setuserID(uname);
			deleteeventobject.setEventToDelete(eventdel);
			Gson gson = new Gson();
			String jsonString = gson.toJson(deleteeventobject);
			ServerConnection connection = new ServerConnection();
			DeleteEventReturnObject deleteeventreturnobject = new DeleteEventReturnObject();
			try {
				deleteeventreturnobject = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), DeleteEventReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(deleteeventreturnobject.isDeleted()){
				JOptionPane.showMessageDialog(null, deleteeventreturnobject.getMessage());
				dispose();
			}else{
				
				JOptionPane.showMessageDialog(null, deleteeventreturnobject.getMessage());
				eventToDelete.setText("");
				eventToDelete.requestFocus();
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
