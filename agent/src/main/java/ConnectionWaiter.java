import com.google.gson.Gson;
import org.json.simple.JSONObject;
import vo.ManagerVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ConnectionWaiter implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Agent.agentVo.getPort());
            socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ManagerVo managerVo = new Gson().fromJson(reader.readLine(), ManagerVo.class);

            System.out.println("==========================");
            System.out.println("Agent <= Manager : ConnectionWaiter");
            System.out.println("Manager Info - IP : " + managerVo.getIp() + ", Port : " + managerVo.getPort());

            Thread dataCollector = new Thread(new DataCollector());
            Thread dataSender = new Thread(new DataSender(managerVo));

            dataCollector.start();
            dataSender.start();
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
