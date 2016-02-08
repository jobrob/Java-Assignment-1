import java.io.*;
import java.net.*;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private BufferedReader server;
  private NoughtsCrosses oxo;
  

  ClientReceiver(BufferedReader server) {
    this.server = server;
  }

  public void run() {
    // Print to the user whatever we get from the server:
    try {
      while (true) {
        int x = server.read();
        int y = server.read();
        
        if (x<3 && y<3)
          oxo.play(x,y);
        else {
          server.close(); // Probably no point.
          throw new IOException("Got null from server"); // Caught below.
        }
      }
    }
    catch (IOException e) {
      System.out.println("Server seems to have died " + e.getMessage());
      System.exit(1); // Give up.
    }
  }
}
