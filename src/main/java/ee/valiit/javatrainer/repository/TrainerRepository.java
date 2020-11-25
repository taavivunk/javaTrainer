package ee.valiit.javatrainer.repository;

import ee.valiit.javatrainer.controller.AnswerRequest;
import ee.valiit.javatrainer.service.AnswerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrainerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Integer addNewQuestion(Long topicId, String question) {
        // adding new question to questions db
        String sql = "INSERT INTO questions (t_id, question) VALUES (:var1, :var2)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var1", topicId);
        paramMap.put("var2", question);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("q_id");
    }

    public void addNewAnswer(Integer questionId, String answer, boolean correct) {
        // adding new answer to answers db
        String sql = "INSERT INTO answers (q_id, answer, is_correct) VALUES (:var1, :var2, :var3 )";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var1", questionId);
        paramMap.put("var2", answer);
        paramMap.put("var3", correct);
        jdbcTemplate.update(sql, paramMap);
    }

    public String getNewQuestion(Long topicId, Long questionId) {
        // getting new question from questions db
        // select question from questions where q_id= 3;
        String sql = "SELECT question from questions where q_id = :var2";
        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("var1", topicId);
        paramMap.put("var2", questionId);
        String question = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return question;
    }


    public List<AnswerRequest> getAnswers() {

        String getAnswers = "SELECT * FROM answers";
        Map paraMap = new HashMap();
        List<AnswerRequest> result = jdbcTemplate.query(getAnswers, paraMap, new AnswerRowMapper());
        return result;

    }
 }
