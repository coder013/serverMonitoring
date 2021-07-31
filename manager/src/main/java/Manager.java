public class Manager {

    public static void main(String[] args) {
        try {
            Runnable sc = new StatusChecker();
            Thread statusChecker = new Thread(sc);

            statusChecker.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
