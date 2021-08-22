package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AgentVo {

    private Integer id;
    private String agentName;
    private String ip;
    private Integer port;
}
