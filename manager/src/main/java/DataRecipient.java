import java.net.ServerSocket;
import java.net.Socket;

public class DataRecipient implements Runnable {

    private ServerSocket serverSocket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Manager.managerVo.getPort());

            while (true) {
                Socket socket = serverSocket.accept();

                Thread dataRecipientThread = new Thread(new DataRecipientThread(socket));
                dataRecipientThread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (serverSocket != null) { serverSocket.close(); }
            } catch (Exception e) { }
        }
    }
}
