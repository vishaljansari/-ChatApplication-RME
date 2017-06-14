import java.io.*;
import java.net.*;
public class Server
{
  public static void main(String[] args) throws Exception
  {
	  args[0]="3000";
	  String ipAddress=args[0];
	  int portNumber = Integer.parseInt(args[0]);
	  
	  ServerSocket sersock = new ServerSocket(portNumber);
      
	  System.out.println("Server Up");
      Socket sock = sersock.accept( );                          
      
      // reading from keyboard 
      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                   
      // sending to client 
      OutputStream ostream = sock.getOutputStream();
      PrintWriter pwrite = new PrintWriter(ostream, true);
 
                          
      // receiving from server 
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
 
      String receiveMessage, sendMessage;              
      while(true)
      {
        if((receiveMessage = receiveRead.readLine()) != null)  
        {
           System.out.println(receiveMessage);        
        }        
        sendMessage = keyRead.readLine();
        pwrite.println(sendMessage);            
        pwrite.flush();
      }              
    }                    
}                        