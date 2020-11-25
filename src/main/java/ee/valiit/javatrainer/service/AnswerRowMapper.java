package ee.valiit.javatrainer.service;

import ee.valiit.javatrainer.controller.AnswerRequest;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerRowMapper implements RowMapper<AnswerRequest> {

    @Override
    public AnswerRequest mapRow(ResultSet resultSet, int i) throws SQLException {
        AnswerRequest answer = new AnswerRequest();
        answer.setAnswer(resultSet.getString("answer"));
        answer.setCorrect(resultSet.getBoolean("is_correct"));
        answer.setQuestionId(resultSet.getLong("q_id"));
        answer.setAnswerId(resultSet.getLong("a_id"));
        return answer;
    }


}
