package ee.valiit.javatrainer.service;


import ee.valiit.javatrainer.AnswerSet;
import ee.valiit.javatrainer.ResultList;
import ee.valiit.javatrainer.User;
import ee.valiit.javatrainer.controller.*;
import ee.valiit.javatrainer.repository.TrainerRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(User user) {
        String encodedPassword = trainerRepository.loginRequest(user.getUserName()); //DB salasõna hash tagasi
        if (passwordEncoder.matches(user.getUserPassword(), encodedPassword)
        ) {
            Date now = new Date();
            JwtBuilder builder = Jwts.builder()
                    .setExpiration(new Date(now.getTime() + 1000*60*60*24)) // küsi selle kohta SIIMU KÄEST 04.12 klassis!!!
                    .setIssuedAt(now)
                    .setIssuer("issuer")
                    .signWith(SignatureAlgorithm.HS256,
                            "secret")

                    .claim("userName", user.getUserName());
            String jwt = builder.compact();

            return jwt;
        }
        else {
            throw new IllegalArgumentException();
        }
}

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

    public String getNewQuestion(Long q_id) {
        String getNew = trainerRepository.getNewQuestion(q_id);
        return getNew;
    }

    public List getAnswers() {      // toob kogu answerite tabeli (4 tulpa) - mitte kasutada äriloogikaks!
        List<AnswerRequest> result = trainerRepository.getAnswers();
        return result;
    }

    public List getResults() {
        List<ResultList> result = trainerRepository.getResults();
        return result;
    }

    public List getAnswersAndId(Long q_id) {  //toob answerite tabelist vastused+id vastavalt ette antud küsimuse id-le
        List<AnswerAndIdRequest> answerTableall = trainerRepository.getAnswersAndIds(q_id);
        return answerTableall;
    }

    public List<String> getAnswersForQuestion(Long q_id) {
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

    public boolean answerCheck(long a_id) {
        boolean trueOrFalse = trainerRepository.trueOrFalse(a_id); //see toob vastavad a_id boolean vastuse
        return trueOrFalse;
    }

    public SubmitAnswerResponse submitAnswer(ResultBack result) {
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
            if (isCorrect) {
                resultCount = resultCount + 1;
            }
            AnswerRequest reply = new AnswerRequest();
            reply.setAnswerId(answerId);
            reply.setQuestionId(questionId);
            reply.setCorrect(isCorrect);
            returnList.add(reply);
        }
        double listSize = (double) list.size();
        double finalResult = resultCount / listSize * 100;
        int testScore = (int) finalResult;
        AnswerRequest addingScore = new AnswerRequest();
        addingScore.setTestScore(testScore);
        trainerRepository.submitFinalresult(testScore, name); //kirjutab tulemuse result_listi
        return new SubmitAnswerResponse(returnList, addingScore);
    }

    public String createNewUser(User user) {
        String hash = passwordEncoder.encode(user.getUserPassword());
        String result = trainerRepository.createNewUser(user.getUserName(), user.getUserClass(), hash);

        return result;
    }
}
