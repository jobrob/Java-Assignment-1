import java.net.*;
import java.io.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
  private String myClientsName;
  private BufferedReader myClient;
  private ClientTable clientTable;
  private GameTable clientNameTable; 
  String move = "move";
  String quit = "quit";

  public ServerReceiver(String n, BufferedReader c, ClientTable t, GameTable g) {
    myClientsName = n;
    myClient = c;
    clientTable = t;
    clientNameTable = g;
  }

  public void run() {
    try {
      //while (true) {
        boolean running = true;
        while (running)
        {
            String recipient = myClient.readLine();
            String command = myClient.readLine();
            //int x = myClient.read();
            //int y = myClient.read();
            //String text = myClient.readLine();
            if (recipient != null &&  command != null)
            {
                if (command.equals(move))
                {
                    int x = myClient.read();
                    int y = myClient.read();
                    NoughtsCrosses oxo = clientNameTable.getGame(recipient);
                    if (oxo != null)
                    {
                        oxo.turn(x,y);
                        clientNameTable.replace(recipient,oxo);

                    }
                    else
                    {
                      NoughtsCrosses newoxo = new NoughtsCrosses();
                      newoxo.turn(x,y);
                      clientNameTable.replace(recipient,newoxo);

                    }  
                    Message move = new Message(myClientsName,"move");
                    MessageQueue recipientQueue = clientTable.getQueue(recipient);
                    recipientQueue.offer(move);
                    System.out.println("sending a move");
                }
                if (command.equals(quit))
                {
                    Message quit = new Message(myClientsName,"move");
                    MessageQueue recipientQueue = clientTable.getQueue(myClientsName);
                    recipientQueue.offer(quit);

                    running = false;
                    System.out.println("quiting");
                }

                
                else
                {
                    System.err.println("Message for unexistent client " 
                                    + recipient );
                }
            }
            else
            {
                myClient.close();
                return;
            }
        }
    }
    catch (IOException e) {
      System.err.println("Something went wrong with the client " 
                       + myClientsName + " " + e.getMessage()); 
      // No point in trying to close sockets. Just give up.
      // We end this thread (we don't do System.exit(1)).
    }
  }
}
