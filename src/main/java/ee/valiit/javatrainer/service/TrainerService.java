package ee.valiit.javatrainer.service;


import ee.valiit.javatrainer.AnswerSet;
import ee.valiit.javatrainer.controller.*;
import ee.valiit.javatrainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.util.ArrayList;
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


//      TEINE VARIANT FOR TSÜKLIKS
//      for(int i = 0; i < questionRequest.getAnswers().size(); i++){
//      AnswerRequest answer = questionRequest.getAnswers().get(i);
//      answer.getAnswer();
//      answer.isCorrect();


    public String getNewQuestion(Long q_id) {
        String getNew = trainerRepository.getNewQuestion(q_id);
        return getNew;
    }


    public List getAnswers() {      // toob kogu answerite tabeli (4 tulpa) - mitte kasutada äriloogikaks!
        List<AnswerRequest> result = trainerRepository.getAnswers();
        return result;
    }


    public List getAnswersAndId(Long q_id) {  //toob answerite tabelist vastused+id vastavalt ette antud küsimuse id-le
        List<AnswerAndIdRequest> answerTableall = trainerRepository.getAnswersAndIds(q_id);
        return answerTableall;
    }


    public List getAnswersForQuestion(Long q_id) {
        List<String> result = trainerRepository.uusrepofunktsioon(q_id);
        return result;
    }

    public List getQFromTopic(long t_id) {
        List<String> topicQuestions = trainerRepository.topicQuestions(t_id);//küsime siin repost kõik vastava teema küsimused
        int qList = topicQuestions.size();
        double random = Math.random();
        int randomQnumber = (int) (random * qList); //random selectime neist ühe
        String randomQuestion = topicQuestions.get(randomQnumber); //saame suvalise küsimuse küsitupostgresd teema listist
        long questionId = trainerRepository.getQuestionId(randomQuestion); // küsime repost valitud küsimuse id
        List<AnswerAndIdRequest> answerTableall = trainerRepository.getAnswersAndIds(questionId); //küsime repost küsimused kood nende id-dega

        List<AnswerSet> wholeSet = new ArrayList<>();
        AnswerSet answerSet = new AnswerSet(answerTableall, randomQuestion, questionId);
        wholeSet.add(answerSet);
        return wholeSet;

//        Map questionWithAnswers = new HashMap (); // teeme tagastamiseks uue mapi, mille sees on String + List
//        questionWithAnswers.put("question",randomQuestion );
//        questionWithAnswers.put("answers", answerTableall); //vastused koos id-dega
//        questionWithAnswers.put("q_id", questionId); //lisame ka küsimuse id
//        return questionWithAnswers;
    }

    public List<AnswerSet> createFullPackage() {

        List<AnswerSet> fullPackage = new ArrayList<>();

        for (int i = 1; i < 8; i++) {

            List<AnswerSet> temporary = new ArrayList<>();
            temporary = getQFromTopic(i);

            fullPackage.addAll(temporary);
        }

        return fullPackage;
    }

    // TODO: kontrollida vastuseid
    public boolean answerCheck(long a_id) {
        boolean trueOrFalse = trainerRepository.trueOrFalse(a_id); //see toob vastavad a_id boolean vastuse
//        String result = String.valueOf(trueOrFalse);
        return trueOrFalse;
    }

    public List submitAnswer(ResultBack result) {
        String name = result.getStudentName();
        List<ResultObjects> list = result.getResultObject();
        List<AnswerRequest> returnList = new ArrayList<AnswerRequest>();
        long questionId = 0;
        long answerId = 0;
        double resultCount = 0.0;
        for (int i = 0; i < list.size(); i++) {
            questionId = list.get(i).getQuestionId();
            answerId = list.get(i).getAnswerId();
            String subAns = trainerRepository.submitAnswer(questionId, answerId, name);
            boolean isCorrect = answerCheck(answerId);
            if(isCorrect){
                resultCount = resultCount + 1;
            }
            AnswerRequest reply = new AnswerRequest();
            reply.setAnswerId(answerId);
            reply.setQuestionId(questionId);
            reply.setCorrect(isCorrect);
            returnList.add(reply);
        }
        double listSize = (double) list.size();
        double finalResult = resultCount / listSize  * 100;
        int testScore = (int) finalResult;
        AnswerRequest addingScore = new AnswerRequest();
        addingScore.setTestScore(testScore);
        returnList.add(addingScore);
        // frondis näidata testi tulemust (Küsimus: x, vastasid: y, see oli õige/vale, tulemus: pass/fail)
        return returnList;
        //kirjutada skoor result_list

    }

}
