public class AgentConnectionChecker implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                String ip = "127.0.0.1";
                Integer port = 10119;
                // Get agent list from database

                Thread agentConnectionCheckThread = new Thread(new AgentConnectionCheckThread(ip, port));
                agentConnectionCheckThread.start();

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
