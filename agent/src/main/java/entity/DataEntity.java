package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class DataEntity {

    private Timestamp date;
    private double cpuUsage;
    private long totalMemory;
    private long freeMemory;
    private long totalSpace;
    private long usableSpace;
}
