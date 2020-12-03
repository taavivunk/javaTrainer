package ee.valiit.javatrainer;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ResultRowMapper implements RowMapper<ResultList> {

    @Override
    public ResultList mapRow(ResultSet resultSet, int i) throws SQLException {
        ResultList result = new ResultList();
        result.setName(resultSet.getString("student_id"));
        result.setResult(resultSet.getLong("result"));
        result.setResultId(resultSet.getLong("r_l_id"));
        result.setTimestamp(resultSet.getTimestamp("timestamp"));
        return result;
    }

}
