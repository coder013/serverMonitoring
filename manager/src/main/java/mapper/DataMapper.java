package mapper;

import dto.DataDto;
import org.apache.ibatis.session.SqlSession;

public class DataMapper {

    public int insertData(SqlSession session, DataDto dataDto) {
        return session.insert("mapper.insertData", dataDto);
    }
}
