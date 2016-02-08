import java.io.*;


// Repeatedly reads recipient's nickname and text from the user in two
// separate lines, sending them to the server (read by ServerReceiver
// thread).

public class ClientSender extends Thread {

  private String nickname;
  private PrintStream server;
  private Move move;

  ClientSender(String nickname, PrintStream server) {
    this.nickname = nickname;
    this.server = server;
    
  }

  public void run() {
    // So that we can use the method readLine:
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    try {
      // Tell the server what my nickname is:
      server.println(nickname);

      // Then loop forever sending messages to recipients via the server:
      while (true) {
        String recipient = user.readLine();
        int x = user.read();
        int y = user.read();
        server.println(recipient);
        server.println(x);
        server.println(y);
      }
    }
    catch (IOException e) {
      System.err.println("Communication broke in ClientSender" 
                        + e.getMessage());
      System.exit(1);
    }
  }
}

