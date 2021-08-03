import vo.ManagerVo;

import java.net.InetAddress;

public class Manager {

    static ManagerVo managerVo;

    public static void main(String[] args) {
        try {
            managerVo = new ManagerVo(InetAddress.getLocalHost().getHostAddress(), 10118);
            // Get manager info from file

            Thread agentConnectionChecker = new Thread(new AgentConnectionChecker());
            Thread dataRecipient = new Thread(new DataRecipient());

            agentConnectionChecker.start();
            dataRecipient.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
