import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    private Counter total = null;
    private Socket socket = null;

    public ServerThread(Counter total, Socket socket) {
	super("ServerThread");
	this.total = total;
	this.socket = socket;
    }

    public void run() {
	DataOutputStream toClient;
	DataInputStream  fromClient;

	try {
	    toClient = new DataOutputStream(socket.getOutputStream());
	    fromClient  = new DataInputStream(socket.getInputStream());

	    int i;

            // The message 0 means "end" (simple protocol)
	    while ((i = fromClient.readInt()) != 0) {
                total.add(i);
		toClient.writeInt(total.get());
	    }

	    toClient.close();
	    fromClient.close();
	    socket.close();
	} 
	catch (IOException e) {
	    return;
	}
    }
}
