package chatBot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class gui extends JFrame{
	
	//Initializing variables here that will be used throughout various methods
	int botCounter = 0;
	static int i = 0;
	static int i2 = 0;
	static JTextField textField = new JTextField(50);
	static String fName = "";
	JFrame chat = new JFrame();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	/*The ArrayLists will store user input and predetermined bot output which varies
	according to the input from the user
	*/
	static ArrayList<String> user = new ArrayList<String>();
	static ArrayList<String> bot = new ArrayList<String>();
	static JTextArea ta = new JTextArea(38, 76);
	
	
	public gui() {
		//Creating initial defaults required for an operational JFrame
		chat.setSize(1200,900);
		chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Centering the window on the screen
		chat.setLocationRelativeTo(null);
		
		//Formatting the bottom end of the GUI
		JLabel sendLabel = new JLabel("Enter message here:");
		JButton send = new JButton("SEND");
		JButton clear = new JButton("CLEAR");
		
		//Action listener for when the "SEND" button is pressed
		send.addActionListener( (e) -> {
			send();
		});
		
		//Action listener for when the "CLEAR" button is pressed
		clear.addActionListener( (e) -> {
			clear();
		});
		
		//More formatting for the bottom end of the GUI
		panel1.add(sendLabel);
		panel1.add(textField);
		panel1.add(send);
		panel1.add(clear);
		
		//Not allowing the textArea to be edited by the user
		ta.setEditable(false);
		
		//Adding scroll bars to the textArea which will come into view when they are required
		JScrollPane scroll = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		panel2.add(scroll);
		
		//Coding where each panel of variables will go on the GUI
		chat.getContentPane().add(BorderLayout.SOUTH, panel1);
		chat.getContentPane().add(BorderLayout.CENTER, panel2);
		
		//This helps with allowing the user to press enter in order to send their message
		chat.getRootPane().setDefaultButton(send);
		
		//Setting everything visible to user and starting the cursor in the textField box immediately
		chat.setVisible(true);
		textField.requestFocusInWindow();
	}
	
	static boolean send() {
		//This prevents the user from sending empty messages
		if(textField.getText().trim().equals("")) {
			clear();
			textField.requestFocusInWindow();
			return false;
		}
		
		//Storing user input into our user ArrayList
		user.add(textField.getText());
		//System.out.println(user.get(i));
		if(fName.length() != 0) {
			ta.setText(ta.getText() + " USER ("+fName+"): " +  user.get(i) + "\n");
		}else {
		ta.setText(ta.getText() + " USER (Add name here later): " +  user.get(i) + "\n");
		}
		i++;
		
		//Clearing the textField box after the message has been sent
		clear();
		textField.requestFocusInWindow();
		return true;
	}
	
	public static boolean botSend() {
		ta.setText(ta.getText() + " BOT: " +  bot.get(i2) + "\n");
		i2++;
		
		textField.requestFocusInWindow();
		return true;
	}
	
	static void clear() {
		textField.setText("");
	}
	
	//This method will specifically be used for easy integration into our already programmed 'chatbot'
	public static String retrieveUserInput(int index) {
		String temp;
		boolean tf = false;;
		
		while(!tf) {
			try {				
				temp = user.get(index);
				tf = true;
				return temp;
			} catch(IndexOutOfBoundsException e) {}
		}
		return null;
	}
	
	public static boolean botOutput (String str) {
		bot.add(str);
		//System.out.println(str);
		
		try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		return botSend();
	}
	
	//This allows user to press enter to send their message
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER) {
			send();
		}
	}
	//Sets first name of patient for GUI
	public void setName(String name) {
		fName = name;
	}
	
	public static void main(String[] args) {
		
		new gui();
		
	}

}
