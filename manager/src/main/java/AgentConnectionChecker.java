import dto.AgentDto;
import mapper.AgentMapper;
import org.apache.ibatis.session.SqlSession;
import util.SqlSessionFactoryUtil;
import vo.AgentVo;

public class AgentConnectionChecker implements Runnable {

    SqlSessionFactoryUtil sqlSessionFactoryUtil = new SqlSessionFactoryUtil();

    AgentMapper agentMapper = new AgentMapper();

    @Override
    public void run() {
        SqlSession session = sqlSessionFactoryUtil.getSqlSession();

        try {
            while (true) {
                AgentDto agentDto = new AgentDto();
                agentDto.setManagerIp(Manager.managerIp);
                agentDto.setManagerPort(Manager.managerPort);

                for (AgentVo agentVo : agentMapper.selectAgentList(session, agentDto)) {
                    Thread agentConnectionCheckThread = new Thread(new AgentConnectionCheckThread(agentVo));
                    agentConnectionCheckThread.start();
                }
                // Get agent list from database

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { session.close(); }
        }
    }
}
