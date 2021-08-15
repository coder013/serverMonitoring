import vo.AgentVo;

import java.net.InetSocketAddress;
import java.net.Socket;

public class AgentConnectionCheckThread implements Runnable {

    private final AgentVo agentVo;

    public AgentConnectionCheckThread(AgentVo agentVo) {
        this.agentVo = agentVo;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(agentVo.getIp(), agentVo.getPort()), 10000);
            socket.setSoTimeout(10000);

            Thread dataReceiver = new Thread(new DataReceiver(socket));
            dataReceiver.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
