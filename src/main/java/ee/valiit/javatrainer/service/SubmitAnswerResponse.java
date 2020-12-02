package ee.valiit.javatrainer.service;

import ee.valiit.javatrainer.controller.AnswerRequest;

import java.util.List;

public class SubmitAnswerResponse {
    private List<AnswerRequest> answers;
    private AnswerRequest totalResult;

    public SubmitAnswerResponse(List<AnswerRequest> returnList, AnswerRequest addingScore) {
        this.answers = returnList;
        this.totalResult = addingScore;
    }

    public List<AnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequest> answers) {
        this.answers = answers;
    }

    public AnswerRequest getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(AnswerRequest totalResult) {
        this.totalResult = totalResult;
    }
}
