import com.google.gson.Gson;
import org.json.simple.JSONObject;
import vo.DataVo;
import vo.ManagerVo;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class DataSender implements Runnable {

    private ManagerVo managerVo;
    private Socket socket;

    public DataSender(ManagerVo managerVo) {
        this.managerVo = managerVo;
    }

    @Override
    public void run() {
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(managerVo.getIp(), managerVo.getPort());
            socket.connect(socketAddress);

            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            while (true) {
                if (socket.isConnected() && !socket.isClosed()) {
                    DataVo dataVo = Agent.dataQueue.take();

                    System.out.println("==========================");
                    System.out.println("Agent => Manager : DataSender");
                    System.out.println("Data : " + dataVo);

                    writer.println(new Gson().toJson(dataVo));
                    writer.flush();
                }
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
