import com.google.gson.Gson;
import org.json.simple.JSONObject;
import vo.ManagerVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class DataRequesterThread implements Runnable {

    private Socket socket;
    private String ip;
    private Integer port;

    public DataRequesterThread(String ip, Integer port) {
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

            // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            ManagerVo vo = new ManagerVo(InetAddress.getLocalHost().getHostAddress(), 4432);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("vo", vo);

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
