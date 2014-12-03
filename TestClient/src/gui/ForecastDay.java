package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import shared.Forecast;
import shared.WeatherReturObject;
import logic.ServerConnection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;

public class ForecastDay extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1761070361083302999L;

	JPanel panel = new JPanel();
//	JTextArea description = new JTextArea();
	JTextArea description = new JTextArea();
	JLabel HD = new JLabel("Forecast");
	JButton thankYou = new JButton("Thank you!");
	private final JScrollPane scrollPane = new JScrollPane();
	
	public ForecastDay(){
		super("Forecast");
		setSize(350, 300);
		setLocation(500, 280);
		panel.setLayout(null);
		thankYou.setBounds(121, 235, 104, 33);
		HD.setForeground(new Color(0, 0, 128));
		HD.setFont(new Font("Arial", Font.BOLD, 20));
		HD.setBounds(123, 6, 92, 33);
		scrollPane.setBounds(16, 40, 315, 183);
		
		panel.add(scrollPane);
		panel.add(HD);
		panel.add(thankYou);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(closeOperation());
		
		setVisible(true);
		
		forecast();
		thankYou.addActionListener(new ActionThankYou());
	}
	
	public int closeOperation(){
		setVisible(false);
		return 1;
		
	}
	
	public void forecast(){
		String getForecast = "getClientForecast";
		WeatherReturObject weather = new WeatherReturObject();
		Gson gson = new Gson();
		ServerConnection connection = new ServerConnection();
		try {
			weather = gson.fromJson(connection.connectToServerAndSendReturnObject(getForecast), WeatherReturObject.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String weatherDay = "";
		for(Forecast i: weather.getWeather()){
				weatherDay = weatherDay.concat("Day: " + i.getDate());
				weatherDay = weatherDay.concat("\n");
				weatherDay = weatherDay.concat("Celsius " + i.getCelsius());
				weatherDay = weatherDay.concat("\n");
				weatherDay = weatherDay.concat(i.getDesc());
				weatherDay = weatherDay.concat("\n" + "\n");
			}
		scrollPane.setViewportView(description);
		description.setText(weatherDay);
		}
		
	public class ActionThankYou implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
}
