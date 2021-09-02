import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DataSender implements Runnable {

    private final ServerSocket serverSocket;
    private final Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    public DataSender(ServerSocket serverSocket, Socket socket) {
        this.serverSocket = serverSocket;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            while (!socket.isClosed()) {
                writer.println(new Gson().toJson(Agent.dataQueue.take()));
                writer.flush();

                System.out.println("==========================");
                System.out.println("Agent => Manager : DataSender");
                // Send data to manager

                JsonObject jsonObject = JsonParser.parseString(reader.readLine()).getAsJsonObject();

                System.out.println("==========================");
                System.out.println("Agent <= Manager : DataSender");
                System.out.println("Connection : " + jsonObject.get("connection"));
                // Receive connection status from manager
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) { reader.close(); }
                if (writer != null) { writer.close(); }
                if (socket != null) { socket.close(); }
                if (serverSocket != null) { serverSocket.close(); }
            } catch (Exception e) { }
        }
    }
}
