package vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentVo {

    private Integer id;
    private String agentName;
    private String ip;
    private Integer port;
}
