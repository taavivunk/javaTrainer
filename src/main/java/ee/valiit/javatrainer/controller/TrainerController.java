package ee.valiit.javatrainer.controller;

import ee.valiit.javatrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("trainer/allAnswers")       //  toob kogu vastuste andmebaasi
    public List getAnswers() {
        return trainerService.getAnswers();
    }

    @CrossOrigin
    @GetMapping("trainer/answers/{id}")       //  toob ühe küsimuse vastusevariandid
    public List getAnswersForQuestion(@PathVariable("id") Long q_id) {
        return trainerService.getAnswersForQuestion(q_id);
    }

    @CrossOrigin
    @GetMapping("trainer/questionfromtopic/{nr}")       // see toob suvalise küsimuse koos vastutega etteantud teemast
    public Map getQuestionFromTopic(@PathVariable("nr") Long t_id) {
        return trainerService.getQFromTopic(t_id);
    }

}
