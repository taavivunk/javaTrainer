package ee.valiit.javatrainer.repository;

import ee.valiit.javatrainer.controller.AnswerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrainerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addNewQuestion(Long topicId, String question) {
        // adding new question to questions db
        String sql = "INSERT INTO questions (question) VALUES (:var)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var", question);
        jdbcTemplate.update(sql, paramMap);
    }

    public void addNewAnswer(Long topicId, List<AnswerRequest> answers) {
        // adding new answer to answers db
        String sql = "INSERT INTO answers (answer) VALUES (:var)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var", answers);
        jdbcTemplate.update(sql, paramMap);

    }

}
