import dto.DataDto;
import enums.TableEnum;
import mapper.DataMapper;
import mapper.TableMapper;
import org.apache.ibatis.session.SqlSession;
import sql.DataSQL;
import util.SqlSessionFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataProcessor implements Runnable {

    SqlSessionFactoryUtil sqlSessionFactoryUtil = new SqlSessionFactoryUtil();

    DataSQL dataSQL = new DataSQL();

    TableMapper tableMapper = new TableMapper();
    DataMapper dataMapper = new DataMapper();

    @Override
    public void run() {
        SqlSession session = null;

        while (true) {
            try {
                if (Manager.dataQueue.size() > 0) {
                    session = sqlSessionFactoryUtil.getSqlSession();

                    String tableName = TableEnum.Data.getName() + new SimpleDateFormat("yyyyMMdd").format(new Date());

                    if (tableMapper.selectTableCount(session, tableName) == 0) {
                        tableMapper.createTable(session, dataSQL.getCreateDataTable().replace("{tableName}", tableName));
                    }

                    StringBuilder strDataList = new StringBuilder();

                    while (!Manager.dataQueue.isEmpty()) {
                        DataDto dto = Manager.dataQueue.poll();

                        if (strDataList.length() > 0) {
                            strDataList.append(",");
                        }
                        strDataList.append("('").append(dto.getDate()).append("'").append(",")
                                .append(dto.getAgentId()).append(",")
                                .append(dto.getCpuUsage()).append(",")
                                .append(dto.getTotalMemory()).append(",")
                                .append(dto.getFreeMemory()).append(",")
                                .append(dto.getTotalSpace()).append(",")
                                .append(dto.getUsableSpace())
                                .append(")");
                    }

                    DataDto dataDto = new DataDto();
                    dataDto.setTableName(tableName);
                    dataDto.setStrDataList(strDataList.toString());

                    dataMapper.insertData(session, dataDto);
                    session.commit();
                }
                // Insert data
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (session != null) { session.close(); }
                try { Thread.sleep(10000); } catch (Exception e) {}
            }
        }
    }
}
