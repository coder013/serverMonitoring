public class Agent {

    public static void main(String[] args) {
        try {
            Runnable dc = new DataCollector();
            Runnable ds = new DataSender();
            Thread dataCollector = new Thread(dc);
            Thread dataSender = new Thread(ds);

            dataCollector.start();
            dataSender.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
