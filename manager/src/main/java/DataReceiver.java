import com.google.gson.Gson;
import com.google.gson.JsonObject;
import vo.DataVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DataReceiver implements Runnable {

    final private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public DataReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            while (true) {
                System.out.println("==========================");
                System.out.println("Manager <= Agent : DataReceiver");

                DataVo dataVo = new Gson().fromJson(reader.readLine(), DataVo.class);
                int unitGB = 1024 * 1024 * 1024;

                System.out.print("Date : " + dataVo.getDate());
                System.out.printf(",  CPU Usage : %.2f %%", dataVo.getCpuUsage() * 100);
                System.out.printf(",  Total Memory : %.2f GB", (double) dataVo.getTotalMemory() / unitGB);
                System.out.printf(",  Free Memory : %.2f GB", (double) dataVo.getFreeMemory() / unitGB);
                System.out.printf(",  Total Space : %.2f GB", (double) dataVo.getTotalSpace() / unitGB);
                System.out.printf(",  Usable Space : %.2f GB%n", (double) dataVo.getUsableSpace() / unitGB);

                System.out.println("==========================");
                System.out.println("Manager => Agent : DataReceiver");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("connection", true);
                writer.println(jsonObject);
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) { reader.close(); }
                if (writer != null) { writer.close(); }
                if (socket != null) { socket.close(); }
            } catch (Exception e) { }
        }
    }
}
