package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class EditNote extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8234179413158268770L;
	JPanel panel = new JPanel();
	JLabel HD = new JLabel("Note");
	JButton addNote = new JButton("Add note");
	JButton deleteNote = new JButton("Delete note");
	
	public EditNote(){
		super("Note");
		setSize(500, 250);
		setLocation(500, 280);
		panel.setLayout(null);
		HD.setFont(new Font("Arial", Font.BOLD, 30));
		HD.setHorizontalAlignment(SwingConstants.CENTER);
		HD.setForeground(new Color(0, 0, 128));
		
		HD.setBounds(189, 18, 136, 33);
		addNote.setBounds(125, 107, 124, 47);
		deleteNote.setBounds(274, 107, 124, 47);
		
		panel.add(HD);
		panel.add(addNote);
		panel.add(deleteNote);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(closeOperation());
		setVisible(true);
		
		addNote.addActionListener(new ActionAddNote());
		deleteNote.addActionListener(new ActionDeleteNote());
		
		
	}
	public int closeOperation(){
		setVisible(false);
		return 1;
		
	}


	public class ActionAddNote implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e){
		AddNote addnote = new AddNote();
		addnote.setVisible(true);
	}
		
	}
	
	public class ActionDeleteNote implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DeleteNote deletenote = new DeleteNote();
			deletenote.setVisible(true);
			
		}
		
	}
}