import vo.ManagerVo;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

public class Manager {

    static ManagerVo managerVo;

    public static void main(String[] args) {
        try {
            InputStream inputStream = Manager.class.getClassLoader().getResourceAsStream("manager.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            managerVo = new ManagerVo(InetAddress.getLocalHost().getHostAddress(), Integer.parseInt(properties.getProperty("port")));
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
