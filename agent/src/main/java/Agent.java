import vo.AgentVo;
import vo.DataVo;

import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Agent {

    static AgentVo agentVo;
    static boolean isConnected = false;
    static BlockingQueue<DataVo> dataQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        try {
            agentVo = new AgentVo(InetAddress.getLocalHost().getHostAddress(), 10119);
            // Get agent info from file

            Thread managerConnectionWaiter = new Thread(new ManagerConnectionWaiter());
            managerConnectionWaiter.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
