import com.google.gson.Gson;
import vo.AgentVo;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class AgentConnectionCheckThread implements Runnable {

    private AgentVo agentVo;
    private Socket socket;

    public AgentConnectionCheckThread(AgentVo agentVo) {
        this.agentVo = agentVo;
    }

    @Override
    public void run() {
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(agentVo.getIp(), agentVo.getPort());
            socket.connect(socketAddress, 10000);

            System.out.println("==========================");
            System.out.println("Manager => Agent : AgentConnectionChecker");
            System.out.println("Agent Info - IP : " + agentVo.getIp() + ", Port : " + agentVo.getPort());

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(new Gson().toJson(Manager.managerVo));
            writer.flush();
        } catch (Exception e) {
            System.out.println("==========================");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) { socket.close(); }
            } catch (Exception e) { }
        }
    }
}
