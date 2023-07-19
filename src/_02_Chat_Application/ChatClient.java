package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChatClient {
	private String ip;
	private int port;

	Socket clientconnection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start() {
		try {

			clientconnection = new Socket(ip, port);

			os = new ObjectOutputStream(clientconnection.getOutputStream());
			is = new ObjectInputStream(clientconnection.getInputStream());

			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

		while (clientconnection.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, is.readObject());
				System.out.println(is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void sendMessage(JTextField field) {

		try {
			if (os != null) {
				os.writeObject(field.getText());
				field.setText("");
				os.flush();

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
