package ee.valiit.javatrainer;

public class Answer {

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    private Long answerId;

    public Answer(String answer, Long answerId) {
        this.answer = answer;
        this.answerId = answerId;
    }
}
