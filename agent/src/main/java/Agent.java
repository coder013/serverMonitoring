public class Agent {

    public static void main(String[] args) {
        try {
            Runnable dc = new DataCollector();
            Thread dataCollector = new Thread(dc);

            dataCollector.start();
        } catch (Exception e) {
        }
    }
}
