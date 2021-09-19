import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.DataDto;
import vo.AgentVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DataReceiver implements Runnable {

    private final AgentVo agentVo;
    private final Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    public DataReceiver(AgentVo agentVo, Socket socket) {
        this.agentVo = agentVo;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            while (true) {
                DataDto dataDto = new Gson().fromJson(reader.readLine(), DataDto.class);
                int unitGB = 1024 * 1024 * 1024;

                System.out.println("==========================");
                System.out.println("Manager <= Agent : DataReceiver");

                dataDto.setAgentId(agentVo.getId());
                Manager.dataQueue.add(dataDto);

                System.out.print("Date : " + dataDto.getDate());
                System.out.print(",  Agent ID : " + dataDto.getAgentId());
                System.out.printf(",  CPU Usage : %.2f %%", dataDto.getCpuUsage() * 100);
                System.out.printf(",  Total Memory : %.2f GB", (double) dataDto.getTotalMemory() / unitGB);
                System.out.printf(",  Free Memory : %.2f GB", (double) dataDto.getFreeMemory() / unitGB);
                System.out.printf(",  Total Space : %.2f GB", (double) dataDto.getTotalSpace() / unitGB);
                System.out.printf(",  Usable Space : %.2f GB%n", (double) dataDto.getUsableSpace() / unitGB);
                // Receive data from agent

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("connection", true);
                writer.println(jsonObject);
                writer.flush();

                System.out.println("==========================");
                System.out.println("Manager => Agent : DataReceiver");
                // Send connection status to agent
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
