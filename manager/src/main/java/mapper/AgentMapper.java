package mapper;

import dto.AgentDto;
import org.apache.ibatis.session.SqlSession;
import vo.AgentVo;

import java.util.List;

public class AgentMapper {

    public List<AgentVo> selectAgentList(SqlSession session, AgentDto agentDto) {
        return session.selectList("mapper.selectAgentList", agentDto);
    }
}
