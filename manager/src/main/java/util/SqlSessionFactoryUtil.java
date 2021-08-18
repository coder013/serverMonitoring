package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactoryUtil() {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(SqlSessionFactoryUtil.class.getClassLoader().getResourceAsStream("mybatis/mybatis-config.xml"));
        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
