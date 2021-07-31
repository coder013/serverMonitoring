import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;

public class DataCollector implements Runnable {

    @Override
    public void run() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            File cDrive = new File("C:");

            Integer unitGB = 1024 * 1024 * 1024;

            while (true) {
                System.out.println("====================");
                System.out.printf("CPU Usage : %.2f %%%n", osBean.getCpuLoad() * 100);
                System.out.printf("Total Memory : %.2f GB%n", (double) osBean.getTotalMemorySize() / unitGB);
                System.out.printf("Free Memory : %.2f GB%n", (double) osBean.getFreeMemorySize() / unitGB);
                System.out.printf("Total Space : %.2f GB%n", (double) cDrive.getTotalSpace() / unitGB);
                System.out.printf("Free Space : %.2f GB%n", (double) cDrive.getFreeSpace() / unitGB);
                System.out.printf("Usable Space : %.2f GB%n", (double) cDrive.getUsableSpace() / unitGB);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
