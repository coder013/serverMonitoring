import vo.DataVo;

import java.util.LinkedList;
import java.util.Queue;

public class Agent {

    static Queue<DataVo> dataQueue = new LinkedList<>();

    public static void main(String[] args) {
        try {
            Runnable dc = new DataCollector();
            Runnable dr = new DataRespondent();
            Thread dataCollector = new Thread(dc);
            Thread dataRespondent = new Thread(dr);

            dataCollector.start();
            dataRespondent.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
