import java.net.ServerSocket;
import java.net.Socket;

public class ManagerConnectionWaiter implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Agent.agentVo.getPort());
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(10000);

            Agent.isConnected = true;

            Thread dataCollector = new Thread(new DataCollector());
            Thread dataSender = new Thread(new DataSender(serverSocket, socket));

            dataCollector.start();
            dataSender.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
