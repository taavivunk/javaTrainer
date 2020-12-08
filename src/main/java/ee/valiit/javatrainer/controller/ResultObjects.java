package ee.valiit.javatrainer.controller;

public class ResultObjects {

    private Long questionId;
    private Long answerId;
    private String column;
    private String direction;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public ResultObjects(Long questionId, Long answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }
}
