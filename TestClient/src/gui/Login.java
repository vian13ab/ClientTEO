package gui;
import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.LogInObject;
import shared.LogInReturnObject;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import logic.ServerConnection;



public class Login  extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3505735372539163059L;
	
		JButton btnlogin = new JButton("Login");
		JPanel panel = new JPanel();
		JTextField txtUser = new JTextField(15);
		JPasswordField password = new JPasswordField(15);
		JLabel HD = new JLabel("Welcome to CBS Calendar");
		JLabel UN = new JLabel("Username");
		JLabel PW = new JLabel ("Password");
		public Login(){
			super("Login Autentification");
			setSize(500,250);
			setLocation(500,280);
			panel.setLayout(null);
			
			
			txtUser.setBounds(235,97,150,20);
			password.setBounds(235,129,150,20);
			btnlogin.setBounds(305,183,80,20);
			HD.setForeground(new Color(0, 0, 128));
			HD.setFont(new Font("Arial", Font.BOLD, 20));
			HD.setBounds(125, 27, 260, 20);
			UN.setBounds(125, 100, 90, 15);
			PW.setBounds(125, 129, 69, 20);
			
			
			panel.add(btnlogin);
			panel.add(txtUser);
			panel.add(password);
			panel.add(HD);
			panel.add(UN);
			panel.add(PW);
			
			getContentPane().add(panel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			btnlogin.addActionListener(new ActionLogin());
			
		}
		public class ActionLogin implements ActionListener{
			
					@Override
					public void actionPerformed(ActionEvent e) {
						String uname = txtUser.getText();
						String pword = password.getText();
						LogInObject login = new LogInObject();
						login.setAuthUsername(uname);
						login.setAuthPassword(pword);
						login.setIsAdmin(false);
						Gson gson = new Gson();
						String jsonString = gson.toJson(login);
						ServerConnection connection = new ServerConnection();
						LogInReturnObject loginreturn = new LogInReturnObject();
						try {
							loginreturn = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), LogInReturnObject.class);
						} catch (JsonSyntaxException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						if(loginreturn.isLogOn()){
							Calendar calendar= new Calendar();
							calendar.setVisible(true);
							dispose();
						}else{
							
							JOptionPane.showMessageDialog(null, loginreturn.getExplanation());
							txtUser.setText("");
							password.setText("");
							txtUser.requestFocus();
						}
				}
		}


}
