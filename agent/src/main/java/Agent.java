import vo.AgentVo;
import vo.DataVo;

import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Agent {

    static AgentVo agentVo;
    static BlockingQueue<DataVo> dataQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        try {
            agentVo = new AgentVo(InetAddress.getLocalHost().getHostAddress(), 10119);
            // Get agent info from file

            Thread connectionWaiter = new Thread(new ConnectionWaiter());

            connectionWaiter.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
