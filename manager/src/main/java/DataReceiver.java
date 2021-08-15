import com.google.gson.JsonObject;

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
                System.out.println("Data : " + reader.readLine());

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
