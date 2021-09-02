package dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DataDto {

    private Timestamp date;
    private Integer id;
    private double cpuUsage;
    private long totalMemory;
    private long freeMemory;
    private long totalSpace;
    private long usableSpace;
}
