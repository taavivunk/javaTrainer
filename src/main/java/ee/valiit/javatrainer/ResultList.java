package ee.valiit.javatrainer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultList {

    private String name;
    private Long resultId;
    private Long result;
    private LocalDateTime timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
