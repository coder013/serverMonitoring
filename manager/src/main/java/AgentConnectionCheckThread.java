import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class AgentConnectionCheckThread implements Runnable {

    private Socket socket;
    private String ip;
    private Integer port;

    public AgentConnectionCheckThread(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(ip, port);
            socket.connect(socketAddress, 10000);

            System.out.println("Connection success");

            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("managerVo", Manager.managerVo);

            writer.println(new Gson().toJson(jsonObject));
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) { socket.close(); }
            } catch (Exception e) { }
        }
    }
}
