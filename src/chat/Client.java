package chat;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

	private Socket socket;
	private String host;
	private int port;
	private Server server;
	private DataOutputStream dout;
	private DataInputStream din;


	// this one is used to make a connection to a peer
	public Client(String host, int port, Server server) {

		this.host = host;
		this.port = port;
		this.server = server;

		try {
			this.socket = new Socket(host, port);
			this.din = new DataInputStream(socket.getInputStream());
			this.dout = new DataOutputStream(socket.getOutputStream());


		} catch (IOException ie) {
			System.out.println(ie);
		}
		
				start();
	}
	
	
   //When we receive a connection from a peer
	
	public Client(Socket soc, Server server) {
		try {
			
			this.socket = soc;
			this.server = server;
			this.host = soc.getInetAddress().getHostAddress();
			this.port = soc.getPort();
			this.din = new DataInputStream(socket.getInputStream());
			this.dout = new DataOutputStream(socket.getOutputStream());


		} catch (IOException ie) {
			System.out.println(ie);
		}
						start();

	}

	//to have the messages, and to keep track of the connection if it lost
	// we close it and delete it from the list
	
	
	@Override
	public void run() {
		try {
			
			while (true) {

				String message = din.readUTF();
				System.out.println("Message received from " + socket.getInetAddress().getHostAddress() + " :");
				System.out.println("\" " + message + "\"");
			}
		} catch (EOFException ie) {
		} catch (IOException ie) {

		} finally {
	
			//if there is no connections we will delete the connection from the list
	
			try{
				
			for (int i = 0; i < this.server.clientsList.size(); i++) {

				if (this.server.clientsList.get(i).getHost().equals(socket.getInetAddress().getHostAddress()) && this.server.clientsList.get(i).getPort() == socket.getPort() ) {
					this.server.clientsList.remove(i);
				}
			}

			if(socket != null)
			{
			System.out.println("The connection to " + socket.getInetAddress().getHostAddress() + " is terminated");
			}
			
		} catch (Exception e6) {}
		
		
			this.interrupt();
		}
	}

	//To send the message to this peer
	
	public void processMessage(String message) {
		try {
			dout.writeUTF(message);
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}

	public Socket getSocket() {
		return socket;
	}
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
}