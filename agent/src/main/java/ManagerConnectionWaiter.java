import java.net.ServerSocket;
import java.net.Socket;

public class ManagerConnectionWaiter implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Agent.agentPort);
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(10000);

            Thread dataCollector = new Thread(new DataCollector(serverSocket, socket));
            Thread dataSender = new Thread(new DataSender(serverSocket, socket));

            dataCollector.start();
            dataSender.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
