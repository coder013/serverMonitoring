package mapper;

import org.apache.ibatis.session.SqlSession;

public class TableMapper {

    public int selectTableCount(SqlSession session, String tableName) {
        return session.selectOne("mapper.selectTableCount", tableName);
    }

    public void createTable(SqlSession session, String sql) {
        session.selectOne("mapper.createTable", sql);
    }
}
