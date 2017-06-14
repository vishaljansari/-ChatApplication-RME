package chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//we can have all the methods from the class commands by extending it in this class
public class Server extends Commands {

	private ServerSocket soc;

	public Server(int port) throws IOException, InterruptedException {
		listen(port);
	}
	private void listen(int port) throws IOException, InterruptedException {

		//We create the serversocket using the ip address of the computer and the set port number
		InetAddress add = InetAddress.getByName(getIP());
		soc = new ServerSocket(port, 0, add);

		//we set this server to the variable serverSocket which is in the commands class
		serverSocket = this;

		System.out.println("Listening on IP " + soc.getInetAddress().getHostAddress() + ", Port " + soc.getLocalPort());

		// we wait for incomming connections and then we add them to the connections list
		while (true) {
			Socket socket = soc.accept();
			System.out.println("The connection to peer  " + socket.getInetAddress().getHostAddress() + " is successfully established ");
			clientsList.add(new Client(socket, this));
		}
	}

	public ArrayList < Client > clientsList() {
		return clientsList;
	}

}