import java.net.ServerSocket;
import java.net.Socket;

public class DataSender implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(4432);

            while (true) {
                socket = serverSocket.accept();
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
