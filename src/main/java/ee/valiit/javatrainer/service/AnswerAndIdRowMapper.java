package ee.valiit.javatrainer.service;

import ee.valiit.javatrainer.controller.AnswerAndIdRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerAndIdRowMapper implements RowMapper<AnswerAndIdRequest> {

    @Override
    public AnswerAndIdRequest mapRow(ResultSet resultSet, int i) throws SQLException {
        AnswerAndIdRequest answer = new AnswerAndIdRequest();
        answer.setAnswer(resultSet.getString("answer"));
        answer.setAnswerId(resultSet.getLong("a_id"));
        return answer;
    }


}
