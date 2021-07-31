import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class StatusCheckThread implements Runnable {

    private Socket socket;
    private String ip;
    private Integer port;

    public StatusCheckThread(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(ip, port);
            socket.connect(socketAddress, 10000);

            System.out.println("Connection success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) { socket.close(); }
            } catch (Exception e) { }
        }
    }
}
