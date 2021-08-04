import com.sun.management.OperatingSystemMXBean;
import vo.DataVo;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;

public class DataCollector implements Runnable {

    @Override
    public void run() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            File cDrive = new File("C:");

            Integer unitGB = 1024 * 1024 * 1024;

            while (true) {
                DataVo vo = new DataVo(new Timestamp(System.currentTimeMillis()), osBean.getCpuLoad(), osBean.getTotalMemorySize(), osBean.getFreeMemorySize(), cDrive.getTotalSpace(), cDrive.getUsableSpace());

                Agent.dataQueue.add(vo);
                // Add data

                /* System.out.println("==========================");
                System.out.printf("CPU Usage : %.2f %%%n", vo.getCpuUsage() * 100);
                System.out.printf("Total Memory : %.2f GB%n", (double) vo.getTotalMemory() / unitGB);
                System.out.printf("Free Memory : %.2f GB%n", (double) vo.getFreeMemory() / unitGB);
                System.out.printf("Total Space : %.2f GB%n", (double) vo.getTotalSpace() / unitGB);
                System.out.printf("Usable Space : %.2f GB%n", (double) vo.getUsableSpace() / unitGB); */

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            Agent.dataQueue.clear();
            Thread connectionWaiter = new Thread(new ConnectionWaiter());
            connectionWaiter.start();
        }
    }
}
