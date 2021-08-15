 import vo.AgentVo;

public class AgentConnectionChecker implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                AgentVo agentVo = new AgentVo("127.0.0.1", 10119);
                // Get agent list from database

                Thread agentConnectionCheckThread = new Thread(new AgentConnectionCheckThread(agentVo));
                agentConnectionCheckThread.start();

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
