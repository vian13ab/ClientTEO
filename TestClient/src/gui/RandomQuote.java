package gui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.ServerConnection;
import shared.QuoteObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomQuote extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711262288421058178L;
	
	JPanel panel = new JPanel();
	JTextField author = new JTextField();
	JTextField subject = new JTextField();
	JTextArea quote = new JTextArea();
	JLabel HD = new JLabel("Quote of the day");
	JButton thankYou = new JButton("Thank you!");
	
	public RandomQuote(){
		super("Random quote");
		setSize(450, 300);
		setLocation(500, 280);
		panel.setLayout(null);
		
		author.setBounds(6, 375, 124, 47);
		subject.setBounds(6, 375, 124, 47);
		quote.setBounds(43, 51, 369, 160);
		thankYou.setBounds(185, 223, 104, 33);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		HD.setBounds(148, 6, 168, 33);
		
		panel.add(quote);
		panel.add(HD);
		panel.add(thankYou);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(closeOperation());
		
		setVisible(true);
		
		showQuote();
		thankYou.addActionListener(new ActionThankYou());
	}
	
	public int closeOperation(){
		setVisible(false);
		return 1;
		
	}
	
	public void showQuote(){
		String getQuote = "getQuote";
		QuoteObject quoteobject = new QuoteObject();
		Gson gson = new Gson();
		ServerConnection connection = new ServerConnection();
		try {
			quoteobject = gson.fromJson(connection.connectToServerAndSendReturnObject(getQuote), QuoteObject.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		quote.setText(quoteobject.getQuote());
		subject.setText(quoteobject.getSubject());
		author.setText(quoteobject.getAuthor());
		
		}
		
	public class ActionThankYou implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}

