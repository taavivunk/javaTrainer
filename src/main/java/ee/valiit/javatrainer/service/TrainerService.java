package ee.valiit.javatrainer.service;


import ee.valiit.javatrainer.controller.AnswerRequest;
import ee.valiit.javatrainer.controller.QuestionRequest;
import ee.valiit.javatrainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public String newQuestionSet(QuestionRequest questionRequest) {
        // insert question
        String question = questionRequest.getQuestion();
        Long topic = questionRequest.getTopicId();
        // käib repos ja lisab küsimuse aga toob ka tagasi küsimuse id q_id

        Integer questionId = trainerRepository.addNewQuestion(topic, question);

        for (AnswerRequest answer : questionRequest.getAnswers()) {
            String answer1 = answer.getAnswer();
            Boolean isCorrect1 = answer.isCorrect();
            trainerRepository.addNewAnswer(questionId, answer1, isCorrect1);


        }
        return "küsimus lisatud";
    }


//      TEINE VARIANT FOR TSÜKKLIKS
//        for(int i = 0; i < questionRequest.getAnswers().size(); i++){
//            AnswerRequest answer = questionRequest.getAnswers().get(i);
//            answer.getAnswer();
//            answer.isCorrect();
//        }

    public String getNewQuestion(Long t_id, Long q_id) {
        String getNew = trainerRepository.getNewQuestion(t_id, q_id);


        return getNew;
    }
}
