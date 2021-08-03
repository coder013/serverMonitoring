import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionWaiter implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Agent.agentVo.getPort());
            socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(reader.readLine());

            Thread dataCollector = new Thread(new DataCollector());
            // Thread dataSender = new Thread(new DataSender());

            dataCollector.start();
            // dataSender.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (serverSocket != null) { serverSocket.close(); }
                if (socket != null) { socket.close(); }
            } catch (Exception e) { }
        }
    }
}
