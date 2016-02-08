import java.net.*;
import java.io.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
  private MessageQueue queue;
  private PrintStream client;
  private GameTable clientNameTable;

  public ServerSender(MessageQueue q, PrintStream c, GameTable g) {
    queue = q;   
    client = c;
    clientNameTable = g;
  }

  public void run() 
  {
    boolean running = true; 
    while (running) 
    {
      Message msg = queue.take();
      if(msg.getText().equals("move"))
      {
          client.println("move");
          //client.println(msg.getSender);
          NoughtsCrosses oxo = clientNameTable.getGame(msg.getSender());
          for(int i =0; i <3; i ++)
          {
              for(int j=0;j<3 ;j++)
              {
                client.println(oxo.get(i,j) );

              }
          }
      }
      if(msg.getText().equals("quit"))
      {
          running = false;
      }
      client.println(msg);
    }
  }
}
