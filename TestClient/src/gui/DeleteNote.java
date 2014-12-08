package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.DeleteNoteObject;
import shared.DeleteNoteReturnObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import logic.ServerConnection;

public class DeleteNote extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7236495971658627391L;
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Delete note");
	JLabel event = new JLabel("Please type in the event you want to delete note from:");
	JLabel user = new JLabel("Enter your username:");
	JTextField eventId = new JTextField();
	JTextField userId = new JTextField();
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	
	public DeleteNote(){
		super("Delete Note");
		setSize(550, 250);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		HD.setForeground(new Color(0, 0, 128));
		HD.setHorizontalAlignment(SwingConstants.CENTER);
		
		HD.setBounds(186, 18, 188, 29);
		event.setBounds(30, 126, 344, 20);
		user.setBounds(30, 77, 295, 20);
		eventId.setBounds(377, 122, 144, 29);
		userId.setBounds(377, 73, 144, 29);
		delete.setBounds(454, 163, 67, 29);
		cancel.setBounds(375, 163, 67, 29);
		
		panel.add(HD);
		panel.add(event);
		panel.add(user);
		panel.add(eventId);
		panel.add(userId);
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
			String eventname = event.getText();
			String username = user.getText();
			DeleteNoteObject deletenoteobject = new DeleteNoteObject();
			deletenoteobject.setEventID(eventname);
			deletenoteobject.setAuthNote(username);
			Gson gson = new Gson();
			String jsonString = gson.toJson(deletenoteobject);
			ServerConnection connection = new ServerConnection();
			DeleteNoteReturnObject deletenotereturnobject = new DeleteNoteReturnObject();
			try {
				deletenotereturnobject = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), DeleteNoteReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(deletenotereturnobject.isDeleted()){
				JOptionPane.showMessageDialog(null, deletenotereturnobject.getMessage());
				dispose();
			}else{
				
				JOptionPane.showMessageDialog(null, deletenotereturnobject.getMessage());
				event.setText("");
				event.requestFocus();
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
