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
	
//	public static void main(String[]args){
//		Login frameTabel = new Login();
//		
//	}
		JButton btnlogin = new JButton("Login");
		JPanel panel = new JPanel();
		JTextField txtUser = new JTextField(15);
		JPasswordField password = new JPasswordField(15);
		JLabel UN = new JLabel("Username");
		JLabel PW = new JLabel ("Password");
		public Login(){
			super("Login Autentification");
			setSize(1000,1000);
			setLocation(500,280);
			panel.setLayout(null);
			
			
			txtUser.setBounds(440,108,150,20);
			password.setBounds(440,140,150,20);
			btnlogin.setBounds(473,195,80,20);
			UN.setBounds(255, 111, 90, 15);
			PW.setBounds(255, 135, 115, 30);
			
			
			panel.add(btnlogin);
			panel.add(txtUser);
			panel.add(password);
			panel.add(UN);
			panel.add(PW);
			
			getContentPane().add(panel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			actionlogin();
			
		}
			public void actionlogin(){
				btnlogin.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
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
						} catch (JsonSyntaxException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
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
				});
			}


}
