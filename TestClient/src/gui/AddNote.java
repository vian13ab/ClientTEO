package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.ServerConnection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.SaveNoteObject;
import shared.SaveNoteReturnObject;
import java.awt.Font;
import java.awt.Color;

public class AddNote extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -723347384797988705L;
	
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Add note");
	JLabel username = new JLabel("Please enter your username: ");
	JLabel content = new JLabel("Note content: ");
	JLabel eventName = new JLabel("Please enter the event you want to add your note to: ");
	JTextField name = new JTextField();
	JTextArea con = new JTextArea();
	JTextField eventN = new JTextField();
	JButton addNote = new JButton("Add note");
	JButton cancel = new JButton("Cancel");
	
	public AddNote(){
		super("Add note");
		setSize(500, 350);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		
		HD.setBounds(211,16,91,20);
		username.setBounds(6,65,180,20);
		content.setBounds(6,129,150,20);
		eventName.setBounds(6,97,331,20);
		name.setBounds(344,65,150,20);
		con.setBounds(109,131,228,139);
		con.setWrapStyleWord(true);
		con.setLineWrap(true);
		eventN.setBounds(344,97,150,20);
		addNote.setBounds(246, 282, 91, 29);
		cancel.setBounds(167, 282, 67, 29);
		
		panel.add(HD);
		panel.add(username);
		panel.add(content);
		panel.add(eventName);
		panel.add(name);
		panel.add(con);
		panel.add(eventN);
		panel.add(addNote);
		panel.add(cancel);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		
		addNote.addActionListener(new ActionAddNote());
		cancel.addActionListener(new ActionCancel());
		
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
	}

	public class ActionAddNote implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String uname = name.getText();
			String cont = con.getText();
			String nameE = eventN.getText();
			SaveNoteObject savenoteobject = new SaveNoteObject();
			savenoteobject.setUserEmail(uname);
			savenoteobject.setNoteContent(cont);
			savenoteobject.setEventName(nameE);
			Gson gson = new Gson();
			String jsonString = gson.toJson(savenoteobject);
			ServerConnection connection = new ServerConnection();
			SaveNoteReturnObject savenotereturn = new SaveNoteReturnObject();
			try {
				savenotereturn = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), SaveNoteReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if(savenotereturn.isUpdated()){
				JOptionPane.showMessageDialog(null, savenotereturn.getMessage());
				dispose();
			}else{
				
				JOptionPane.showMessageDialog(null, savenotereturn.getMessage());
				name.setText("");
				con.setText("");
				eventN.setText("");
				name.requestFocus();
				
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
