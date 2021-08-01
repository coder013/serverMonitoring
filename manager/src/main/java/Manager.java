public class Manager {

    public static void main(String[] args) {
        try {
            Runnable dr = new DataRequester();
            Thread dataRequester = new Thread(dr);

            dataRequester.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
