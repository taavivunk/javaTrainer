package ee.valiit.javatrainer;

import java.util.List;

public class AnswerSet {

    private List answers;
    private String question;
    private Long q_id;

    public List getAnswers() {
        return answers;
    }

    public void setAnswers(List answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getQ_id() {
        return q_id;
    }

    public void setQ_id(Long q_id) {
        this.q_id = q_id;
    }

    public AnswerSet(List answers, String question, Long q_id) {
        this.answers = answers;
        this.question = question;
        this.q_id = q_id;
    }
}
