import java.net.*;
import java.io.*;

public class Server {

public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    
    Counter total = new Counter();

    try {
        serverSocket = new ServerSocket(4444);
    } 
    catch (IOException e) {
        System.err.println("Could not listen on port: 4444.");
        System.exit(1);
    }

    try {
        while (true) {
            Socket s = serverSocket.accept(); // Stuck until somebody connects
            ServerThread t = new ServerThread(total, s);
            t.start();
        }
    } 
    catch (Exception e) {
        serverSocket.close(); 
    }
}
}
