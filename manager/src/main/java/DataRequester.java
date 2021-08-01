public class DataRequester implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                String ip = "127.0.0.1";
                Integer port = 4432;
                // Get IP & Port list from database

                Runnable drt = new DataRequesterThread(ip, port);
                Thread dataRequesterThread = new Thread(drt);
                dataRequesterThread.start();

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
