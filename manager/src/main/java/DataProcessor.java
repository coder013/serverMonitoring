import enums.TableEnum;
import mapper.TableMapper;
import org.apache.ibatis.session.SqlSession;
import sql.AgentLogSQL;
import util.SqlSessionFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataProcessor implements Runnable {

    SqlSessionFactoryUtil sqlSessionFactoryUtil = new SqlSessionFactoryUtil();

    AgentLogSQL agentLogSQL = new AgentLogSQL();

    TableMapper tableMapper = new TableMapper();

    @Override
    public void run() {
        SqlSession session = sqlSessionFactoryUtil.getSqlSession();

        try {
            String tableName = TableEnum.AGENT_LOG.getName() + new SimpleDateFormat("yyyyMMdd").format(new Date());

            if (tableMapper.selectTableCount(session, tableName) == 0) {
                String sql = agentLogSQL.getCreateAgentLog().replace("{tableName}", tableName);

                tableMapper.createTable(session, sql);
            }
            // data processing
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { session.close(); }
        }
    }
}
