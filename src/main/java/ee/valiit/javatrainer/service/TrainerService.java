package ee.valiit.javatrainer.service;


import ee.valiit.javatrainer.controller.AnswerRequest;
import ee.valiit.javatrainer.controller.QuestionRequest;
import ee.valiit.javatrainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String getNewQuestion(Long q_id) {
        String getNew = trainerRepository.getNewQuestion(q_id);
        return getNew;
    }


    public List getAnswers() {          //  see toob kogu answerite tabeli (4 tulpa) - mitte kasutada äriloogikaks!

        List<AnswerRequest> result = trainerRepository.getAnswers();
        return result;
    }

    public List getAnswersForQuestion(Long q_id) {

        List<String> result = trainerRepository.uusrepofunktsioon(q_id);



        return result;
    }

    public Map getQFromTopic(long t_id) {

        //List<Object> getQandTopic = new ArrayList();

        //todo küsime siin repost kõik vastava teema küsimused
        List<String> topicQuestions = trainerRepository.topicQuestions(t_id);

        //todo 2 random selectime neist ühe
        int qList = topicQuestions.size();
        int randomQnumber = (int) (Math.random()*qList); //võtsime +1 ära (int) eest
        String randomQuestion = topicQuestions.get(randomQnumber); //saame suvalise küsimuse küsitud teema listist
        long questionId = trainerRepository.getQuestionId(randomQuestion); // küsime repost valitud küsimuse id
        List<String> result = trainerRepository.uusrepofunktsioon(questionId); //küsisme vastused vastavalt küsimuse id-le ja saime listi stringidest
        Map questionWithAnswers = new HashMap (); // Teeme tagastamiseks uue mapi kus on String+List
        questionWithAnswers.put("question",randomQuestion );
        questionWithAnswers.put("answers", result);

        return questionWithAnswers;



    }

}
