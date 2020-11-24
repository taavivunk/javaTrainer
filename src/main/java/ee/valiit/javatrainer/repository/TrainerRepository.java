package ee.valiit.javatrainer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerRepository {

@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;







}
