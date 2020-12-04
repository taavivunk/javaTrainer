package ee.valiit.javatrainer.controller;

import ee.valiit.javatrainer.AnswerSet;
import ee.valiit.javatrainer.User;
import ee.valiit.javatrainer.service.SubmitAnswerResponse;
import ee.valiit.javatrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    @CrossOrigin            // saadab uue kasutaja andmed users andmebaasi
    @PostMapping("trainer/newUser")
    public String createUser(@RequestBody User user) {
        return trainerService.createNewUser(user);
    }

    @CrossOrigin            // saadab kasutaja nime ja salasõna kontrolli
    @GetMapping("trainer/login")
    public String login(@RequestBody User user) {
        return trainerService.login(user);
    }

    @CrossOrigin            // saadab küsimuse ja vastused questions ja answers andmebaasi
    @PostMapping("trainer/newQuestionSet")
    public String pushQuestion(@RequestBody QuestionRequest questionRequest) {
        return trainerService.newQuestionSet(questionRequest);
    }

    @CrossOrigin            // toob küsimuse (objekt) ja vastuse variandid (list)
    @GetMapping("trainer/question/{id}")
    public String getNewQuestion(@PathVariable("id") Long q_id) {
        return trainerService.getNewQuestion(q_id);
    }

    @CrossOrigin            // toob kogu vastuste andmebaasi (rowmapper)
    @GetMapping("trainer/allAnswers")
    public List getAnswers() {
        return trainerService.getAnswers();
    }

    @CrossOrigin            // toob result_listi
    @GetMapping("trainer/getresults")
    public List getResults() {
        return trainerService.getResults();
    }

    @CrossOrigin            // toob vastused koos id-ga (rowmapper)
    @GetMapping("trainer/AnswersAndId/{id}")
    public List getAnswersAndId(@PathVariable("id") Long q_id) {
        return trainerService.getAnswersAndId(q_id);
    }

    @CrossOrigin            // toob ühe küsimuse vastusevariandid
    @GetMapping("trainer/answers/{id}")
    public List getAnswersForQuestion(@PathVariable("id") Long q_id) {
        return trainerService.getAnswersForQuestion(q_id);
    }

    @CrossOrigin            // toob suvalise küsimuse koos vastutega etteantud teemast
    @GetMapping("trainer/questionfromtopic/{nr}")
    public List<String> getQuestionFromTopic(@PathVariable("nr") Long t_id) {
        return trainerService.getQFromTopic(t_id);
    }

    @CrossOrigin            // toob kõikidest teemadest ühe küsimuse koos vastustega
    @GetMapping("trainer/testpackage")
    public List<AnswerSet> getFullPackage() {
        return trainerService.createFullPackage();
    }

    @CrossOrigin            // saadab vastuse answer_log andmebaasi
    @PostMapping("trainer/submitAnswer")
    public SubmitAnswerResponse submitAnswer(@RequestBody ResultBack result) {
        return trainerService.submitAnswer(result);
    }

}
