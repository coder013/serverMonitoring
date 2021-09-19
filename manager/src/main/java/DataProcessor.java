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
        SqlSession session = sqlSessionFactoryUtil.getSqlSession();

        try {
            while (true) {
                if (Manager.dataQueue.size() > 0) {
                    String tableName = TableEnum.Data.getName() + new SimpleDateFormat("yyyyMMdd").format(new Date());

                    if (tableMapper.selectTableCount(session, tableName) == 0) {
                        tableMapper.createTable(session, dataSQL.getCreateDataTable().replace("{tableName}", tableName));
                    }

                    String strDataList = "";

                    while (Manager.dataQueue.isEmpty() == false) {
                        DataDto dto = Manager.dataQueue.poll();

                        if (strDataList.length() > 0) {
                            strDataList += ",";
                        }
                        strDataList += "("
                                + "'" + dto.getDate() + "'" + ","
                                + dto.getAgentId() + ","
                                + dto.getCpuUsage() + ","
                                + dto.getTotalMemory() + ","
                                + dto.getFreeMemory() + ","
                                + dto.getTotalSpace() + ","
                                + dto.getUsableSpace()
                                + ")";
                    }

                    DataDto dataDto = new DataDto();
                    dataDto.setTableName(tableName);
                    dataDto.setStrDataList(strDataList);

                    dataMapper.insertData(session, dataDto);
                    // dataQueue를 사용하여 Bulk insert 해야함.
                    // 방법 1. Queue => List로 변경 후 동적 쿼리 작성
                    // 방법 2. MyBatis forEach에서 Queue 사용 가능한지 확인, thread safety 한지도 확인
                }
                // Insert data

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { session.close(); }
        }
    }
}
