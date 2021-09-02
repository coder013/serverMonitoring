import dto.DataDto;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Manager {

    static String managerIp;
    static Integer managerPort;
    static BlockingQueue<DataDto> dataQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        try {
            InputStream inputStream = Manager.class.getClassLoader().getResourceAsStream("manager.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            managerIp = InetAddress.getLocalHost().getHostAddress();
            managerPort = Integer.parseInt(properties.getProperty("port"));
            // Get manager info from file

            Thread agentConnectionChecker = new Thread(new AgentConnectionChecker());
            agentConnectionChecker.start();

            Thread dataProcessor = new Thread(new DataProcessor());
            dataProcessor.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
