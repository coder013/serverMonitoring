import mapper.TableMapper;
import org.apache.ibatis.session.SqlSession;
import util.SqlSessionFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataProcessor implements Runnable {

    SqlSessionFactoryUtil sqlSessionFactoryUtil = new SqlSessionFactoryUtil();

    TableMapper tableMapper = new TableMapper();

    @Override
    public void run() {
        SqlSession session = sqlSessionFactoryUtil.getSqlSession();

        try {
            if (tableMapper.selectTableCount(session, new SimpleDateFormat("yyyyMMdd").format(new Date())) == 0) {
                // create table
            }
            // data processing
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { session.close(); }
        }
    }
}
