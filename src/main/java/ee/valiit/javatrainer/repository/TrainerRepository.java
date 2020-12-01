package ee.valiit.javatrainer.repository;

import ee.valiit.javatrainer.controller.AnswerAndIdRequest;
import ee.valiit.javatrainer.controller.AnswerRequest;
import ee.valiit.javatrainer.service.AnswerAndIdRowMapper;
import ee.valiit.javatrainer.service.AnswerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    public String getNewQuestion(Long questionId) {
        // getting new question from questions db
        // select question from questions where q_id= 3;
        String sql = "SELECT question from questions where q_id = :var2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var2", questionId);
        String question = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return question;
    }
    public long getQuestionId(String question) {
        String sql = "SELECT q_id from questions where question = :var1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var1", question);
        long questionId = jdbcTemplate.queryForObject(sql, paramMap, Long.class);
        return questionId;
    }

    public List<AnswerRequest> getAnswers() {

        String getAnswers = "SELECT * FROM answers";
        Map paraMap = new HashMap();
        List<AnswerRequest> result = jdbcTemplate.query(getAnswers, paraMap, new AnswerRowMapper());
        return result;
    }

    public List<AnswerAndIdRequest> getAnswersAndIds(long qId) {

        String getAnswersAndIds = "SELECT * FROM answers where q_id =:var";
        Map paraMap = new HashMap();
        paraMap.put("var", qId);
        List<AnswerAndIdRequest> result = jdbcTemplate.query(getAnswersAndIds, paraMap, new AnswerAndIdRowMapper());
        return result;
    }


    public List<String> uusrepofunktsioon(long qId) {

        String getAnswersToQuestion = "SELECT answer FROM answers where q_id = :var";
        Map paraMap = new HashMap();
        paraMap.put("var", qId);
        List<String> result = jdbcTemplate.queryForList(getAnswersToQuestion, paraMap, String.class);
        return result;
    }

    public List <String> topicQuestions (long t_id){
        String sql = "SELECT question FROM questions where t_id =:var";
        Map paramap = new HashMap();
        paramap.put("var",t_id);
        List<String> result = jdbcTemplate.queryForList(sql, paramap, String.class);
        return result;
    }

    public String submitAnswer(long q_id, long a_id, String student_id){
        String sql = "INSERT INTO answer_log (q_id, a_id, student_id, timestamp) VALUES (:var1, :var2, :var3, :var4)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("var1", q_id);
        paraMap.put("var2", a_id);
        paraMap.put("var3", student_id);
        paraMap.put("var4", new Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update(sql, paraMap);

        return "Küsimus ja vastus lisatud logisse (tabelisse answer_log)!";


    }

    public boolean trueOrFalse(long a_id) {
        String sql = "SELECT is_correct from answers where a_id = :var1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("var1", a_id);
        boolean result = jdbcTemplate.queryForObject(sql, paramMap, Boolean.class);
        return result;



    }


 }
