// Usage:
//         java Client hostName portNumber

import java.io.*;
import java.net.*;

public class Client {
    // Communication:
    private Socket           server;
    private DataOutputStream toServer;
    private DataInputStream  fromServer;

    // Command line arguments:
    private String [] args;

    // Error code convention for System.exit():
    static final int badUsage                = 1;
    static final int ioError                 = 2;
    static final int connectionBroke         = 3;
    static final int couldNotCloseConnection = 4;
    static final int unknownHost             = 5;


    // The constructor only saves a 2-element array.
    // args[0] is supposed to be the host name, and 
    // args[1] is supposed to be the port number. 
    // See establishConnection() below.

    Client (String [] args) {
	assert(args.length == 2);
	this.args = args;
    }

    // Sample use of this class:
    public static void main(String[] args)  {
        
        Client c = new Client(args);

	c.run();
    }


    // This is what this class does:
    public void run()  {
        
        establishConnection();

        clientLoop(); // That's one thing you need to change!

        closeConnection();
    }
    

    // Helper method:
    private Socket getSocket() 
        throws UnknownHostException, IOException, IllegalArgumentException
    {
        if (args.length != 2)
            throw new IllegalArgumentException
                         ("Usage: java Client hostName portNumber");

        String serverName = args[0];
        int serverPort = 0;

        try {
            serverPort = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException
                         ("Second argument must be an integer");
        }

        return new Socket(serverName, serverPort);
    }


    // Exits if fails:
    private void establishConnection() {
        try {
            server = getSocket();
        }
        catch (IllegalArgumentException e) {
            System.err.println(e);
            System.err.println("Usage: java Client serverName serverPort");
            System.exit(badUsage);
        }
        catch (UnknownHostException e) {
            System.err.println("Unknown host");
            System.err.println(e);
            System.exit(unknownHost);
        } 
        catch (IOException e) {
            System.err.println("IO error while connecting to server");
            System.err.println(e);
            System.exit(ioError);
        } 

        try {
            toServer = new DataOutputStream(server.getOutputStream());
            fromServer = new DataInputStream(server.getInputStream());
        } 
        catch (IOException e) {
            System.err.println("connectionBroke");
            System.err.println(e);
            System.exit(connectionBroke);
        }
    }


    private void closeConnection() {
        try {
            toServer.close();   
            fromServer.close();
            server.close();
        }
        catch (IOException e) {
            System.err.println("Couldn't gracefully close connection");
            System.err.println(e);
            System.exit(couldNotCloseConnection);
        }
    }


    private void clientLoop () {
        try {
	    BufferedReader fromUser = 
		new BufferedReader(new InputStreamReader(System.in));

	    PrintStream toUser = System.out;

 	    String userInput;

            while ((userInput = fromUser.readLine()) != null) {

                // Try to get a number from user

                if (userInput.equals("")) // end of client activity
                   break;

                int userNumber = 0;
		
                try {
                    userNumber = Integer.parseInt(userInput);
                }
                catch (NumberFormatException e) {
                    toUser.println("Please enter an integer");
                    continue;
                }
            
                if (userNumber == 0) // 0 also signals end
                    break;
		
                // Successfully got the number from User

                toServer.writeInt(userNumber);
                toUser.println("sent " + userNumber + " to server");
            
                int serverNumber = fromServer.readInt();
                toUser.println("got " + serverNumber + " from server");
            }

            // End of loop

            toServer.writeInt(0); // Tell the server we are done.
        }
        catch (IOException e) {
            System.err.println("Connection with server broke");
            System.err.println(e);
            System.exit(connectionBroke);
        }
    }
}
