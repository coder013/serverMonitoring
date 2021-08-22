package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentDto {

    private String ip;
    private Integer port;

    private String managerIp;
    private Integer managerPort;
}
