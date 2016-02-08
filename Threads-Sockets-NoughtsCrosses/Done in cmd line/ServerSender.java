import java.net.*;
import java.io.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
  private MoveQueue queue;
  private PrintStream client;

  public ServerSender(MoveQueue q, PrintStream c) {
    queue = q;   
    client = c;
  }

  public void run() {
    while (true) {
      Move msg = queue.take();
      client.println(msg);
    }
  }
}
