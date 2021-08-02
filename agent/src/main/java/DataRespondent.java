import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DataRespondent implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(4432);

            while (true) {
                socket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // PrintWriter writer = new PrintWriter(socket.getOutputStream());

                System.out.println(reader.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) { socket.close(); }
            } catch (Exception e) { }
        }
    }
}
