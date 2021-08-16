import com.sun.management.OperatingSystemMXBean;
import vo.DataVo;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

public class DataCollector implements Runnable {

    final private ServerSocket serverSocket;
    final private Socket socket;

    public DataCollector(ServerSocket serverSocket, Socket socket) {
        this.serverSocket = serverSocket;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            File path = new File("C:");
            //

            while (!socket.isClosed()) {
                Agent.dataQueue.add(new DataVo(new Timestamp(System.currentTimeMillis()), osBean.getCpuLoad(), osBean.getTotalMemorySize(), osBean.getFreeMemorySize(), path.getTotalSpace(), path.getUsableSpace()));
                // Add data

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null) { socket.close(); }
                if (serverSocket != null) { serverSocket.close(); }
            } catch (Exception e) { }

            Agent.dataQueue.clear();

            Thread connectionWaiter = new Thread(new ManagerConnectionWaiter());
            connectionWaiter.start();
        }
    }
}
