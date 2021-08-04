import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DataRecipientThread implements Runnable {

    private Socket socket;

    public DataRecipientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("==========================");
                System.out.println("Manager <= Agent : DataRecipientThread");
                System.out.println("Data : " + reader.readLine());
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
