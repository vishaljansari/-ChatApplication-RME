import java.io.*;
import java.net.*;
public class Client
{
  public static void main(String[] args) throws Exception
  {
	 args[0]="127.0.0.1";
	 args[1]="3000";
	 
	 String ipAddress=args[0];
	 int portNumber = Integer.parseInt(args[1]);
     
	 Socket sock = new Socket("127.0.0.1",portNumber);
     
	 // reading from keyboard (keyRead object)
	 BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
     
	 // sending to client (pwrite object)
     
	 OutputStream ostream = sock.getOutputStream(); 
     PrintWriter pwrite = new PrintWriter(ostream, true);

                              // receiving from server ( receiveRead  object)
     InputStream istream = sock.getInputStream();
     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

     System.out.println("Press enter to start chat ");

     String receiveMessage, sendMessage;               
     while(true)
     {
        sendMessage = keyRead.readLine();  // keyboard reading
        pwrite.println(sendMessage);       // sending to server
        pwrite.flush();                    // flush the data
        if((receiveMessage = receiveRead.readLine()) != null) //receive from server
        {
            System.out.println(receiveMessage); // displaying at DOS prompt
        }         
      }               
    }                    
}                        