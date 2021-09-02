import entity.DataEntity;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Agent {

    static String agentIp;
    static Integer agentPort;
    static BlockingQueue<DataEntity> dataQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        try {
            InputStream inputStream = Agent.class.getClassLoader().getResourceAsStream("agent.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            agentIp = InetAddress.getLocalHost().getHostAddress();
            agentPort = Integer.parseInt(properties.getProperty("port"));
            // Get agent info from file

            Thread managerConnectionWaiter = new Thread(new ManagerConnectionWaiter());
            managerConnectionWaiter.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
