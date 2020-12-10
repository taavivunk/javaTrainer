package ee.valiit.javatrainer.service;

import ee.valiit.javatrainer.controller.AnswerRequest;
import ee.valiit.javatrainer.controller.QuestionRequest;
import ee.valiit.javatrainer.controller.QuestionsAndAnswers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsAndAnswersRowMapper implements RowMapper<QuestionsAndAnswers> {

    @Override
    public QuestionsAndAnswers mapRow(ResultSet resultSet, int i) throws SQLException {
        QuestionsAndAnswers result = new QuestionsAndAnswers();
        result.setQuestionId(resultSet.getLong("q_id"));
        result.setTopicId(resultSet.getLong("t_id"));
        result.setQuestion(resultSet.getString("question"));
        result.setAnswerId(resultSet.getLong("a_id"));
        result.setAnswer(resultSet.getString("answer"));
        result.setCorrect(resultSet.getBoolean("is_correct"));
        return result;
    }
}
