package mapper;

import org.apache.ibatis.session.SqlSession;

public class TableMapper {

    public int selectTableCount(SqlSession session, String date) {
        return session.selectOne("mapper.selectTableCount", date);
    }
}
