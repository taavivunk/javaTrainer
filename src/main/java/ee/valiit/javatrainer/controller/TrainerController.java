package ee.valiit.javatrainer.controller;

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
    @GetMapping("trainer/allAnswers")       // see toob kogu vastuste andmebaasi
    public List getAnswers() {
        return trainerService.getAnswers();
    }

    @CrossOrigin
    @GetMapping("trainer/answers")       // see toob ühe küsimuse vastusevariandid
    public List getAnswersForQuestion() {
        return trainerService.getAnswersForQuestion();
    }



}
