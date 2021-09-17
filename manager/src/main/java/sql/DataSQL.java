package sql;

import lombok.Getter;

@Getter
public class DataSQL {

    private final String createData = "create table {tableName} (" +
            "date           timestamp," +
            "agent_id       integer," +
            "cpu_usage      long not null," +
            "total_memory   long not null," +
            "free_memory    long not null," +
            "total_space    long not null," +
            "usable_space   long not null," +
            "primary key(date, agent_id)" +
            ")";
}
