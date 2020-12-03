package ee.valiit.javatrainer.controller;

import ee.valiit.javatrainer.AnswerSet;
import ee.valiit.javatrainer.service.SubmitAnswerResponse;
import ee.valiit.javatrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {
        @Autowired
    TrainerService trainerService;

    @CrossOrigin
    @PostMapping("trainer/newQuestionSet")
    public String pushQuestion(@RequestBody QuestionRequest questionRequest) {

        return trainerService.newQuestionSet(questionRequest);

    }

    @CrossOrigin
    @GetMapping("trainer/question/{id}")
    // tagastada küsimuse (objekt) ja vastuse variandid (list)
    public String getNewQuestion(@PathVariable("id") Long q_id) {
        return trainerService.getNewQuestion(q_id);

    }

    @CrossOrigin
    @GetMapping("trainer/allAnswers")       //  toob kogu vastuste andmebaasi (rowmapper)
    public List getAnswers() {
        return trainerService.getAnswers();
    }

    @CrossOrigin
    @GetMapping("trainer/getresults")
    public List getResults() {
        return trainerService.getResults();

    }

    @CrossOrigin
    @GetMapping("trainer/AnswersAndId/{id}")       //  toob vastused koos id-ga (rowmapper)
    public List getAnswersAndId(@PathVariable("id") Long q_id) {
        return trainerService.getAnswersAndId(q_id);
    }

    @CrossOrigin
    @GetMapping("trainer/answers/{id}")       //  toob ühe küsimuse vastusevariandid
    public List getAnswersForQuestion(@PathVariable("id") Long q_id) {
        return trainerService.getAnswersForQuestion(q_id);
    }

    @CrossOrigin
    @GetMapping("trainer/questionfromtopic/{nr}")       // see toob suvalise küsimuse koos vastutega etteantud teemast
    public List<String> getQuestionFromTopic(@PathVariable("nr") Long t_id) {
        return trainerService.getQFromTopic(t_id);
    }

    @CrossOrigin
    @GetMapping("trainer/testpackage") //toob kõikidest teemadest ühe küsimuse koos vastustega
    public List<AnswerSet> getFullPackage() {
        return trainerService.createFullPackage();
    }




    @CrossOrigin
    @PostMapping("trainer/submitAnswer") //toob frondist vastatud küsimuse tagasi ja saadab answer_log'i
    public SubmitAnswerResponse submitAnswer(@RequestBody ResultBack result) {
        return trainerService.submitAnswer(result);



    }

}
