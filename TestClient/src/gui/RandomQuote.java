package gui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import logic.ServerConnection;
import shared.QuoteObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class RandomQuote extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711262288421058178L;
	
	JPanel panel = new JPanel();
	JLabel author = new JLabel();
	JLabel subject = new JLabel();
	JTextArea quote = new JTextArea();
	JLabel HD = new JLabel("Quote of the day");
	JButton thankYou = new JButton("Thank you!");
	JLabel lblQuote = new JLabel("Quote:");
	JScrollPane scrollPane = new JScrollPane();
	
	public RandomQuote(){
		super("Random quote");
		setSize(350, 300);
		setLocation(500, 280);
		panel.setLayout(null);
		
		author.setBounds(162, 38, 168, 33);
		subject.setBounds(162, 70, 168, 24);
		thankYou.setBounds(131, 239, 104, 33);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		HD.setBounds(97, 6, 168, 33);
		scrollPane.setBounds(17, 138, 313, 96);
		
		panel.add(scrollPane);
		scrollPane.setViewportView(quote);
		quote.setWrapStyleWord(true);
		quote.setLineWrap(true);
		quote.setEditable(false);
		panel.add(HD);
		panel.add(thankYou);
		panel.add(author);
		panel.add(subject);
		
		getContentPane().add(panel);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(108, 46, 53, 16);
		panel.add(lblAuthor);
		
		JLabel lblTopic = new JLabel("Topic:");
		lblTopic.setBounds(108, 74, 44, 16);
		panel.add(lblTopic);
		lblQuote.setBounds(158, 110, 44, 16);
		
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

