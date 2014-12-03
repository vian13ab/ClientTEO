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
import javax.swing.SwingConstants;

public class RandomQuote extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711262288421058178L;
	
	JPanel panel = new JPanel();
	JLabel author = new JLabel();
	JLabel subject = new JLabel();
	JLabel quote = new JLabel();
	JLabel HD = new JLabel("Quote of the day");
	JButton thankYou = new JButton("Thank you!");
	private final JLabel lblQuote = new JLabel("Quote:");
	
	public RandomQuote(){
		super("Random quote");
		setSize(1000, 250);
		setLocation(500, 280);
		panel.setLayout(null);
		
		author.setBounds(340, 44, 168, 33);
		subject.setBounds(568, 44, 178, 33);
		quote.setHorizontalAlignment(SwingConstants.CENTER);
		quote.setBounds(6, 125, 988, 25);
		thankYou.setBounds(453, 177, 104, 33);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		HD.setBounds(420, 6, 168, 33);
		
		panel.add(quote);
		panel.add(HD);
		panel.add(thankYou);
		panel.add(author);
		panel.add(subject);
		
		getContentPane().add(panel);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(290, 52, 53, 16);
		panel.add(lblAuthor);
		
		JLabel lblTopic = new JLabel("Topic:");
		lblTopic.setBounds(524, 52, 44, 16);
		panel.add(lblTopic);
		lblQuote.setBounds(477, 97, 44, 16);
		
		panel.add(lblQuote);
		
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
		subject.setText(quoteobject.getTopic());
		author.setText(quoteobject.getAuthor());
		
		}
		
	public class ActionThankYou implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}

