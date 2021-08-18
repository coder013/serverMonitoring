import org.apache.ibatis.session.SqlSession;
import util.SqlSessionFactoryUtil;
import vo.ManagerVo;

import java.util.List;

public class DataProcessor implements Runnable {

    SqlSessionFactoryUtil sqlSessionFactoryUtil = new SqlSessionFactoryUtil();

    @Override
    public void run() {
        SqlSession session = sqlSessionFactoryUtil.getSqlSession();
        SqlSession session2 = sqlSessionFactoryUtil.getSqlSession();

        try {
            List<ManagerVo> managerVoList = session.selectList("managerMapper.selectManagerList");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { session.close(); }
        }
        // sqlSession test#1

        try {
            List<ManagerVo> managerVoList = session2.selectList("managerMapper.selectManagerList");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session2 != null) { session2.close(); }
        }
        // sqlSession test#2
    }
}
