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
    @GetMapping("trainer/getQuestion")
    public String getNewQuestion(@RequestParam("topic") Long t_id,
                              @RequestParam("question") Long q_id) {

        return trainerService.getNewQuestion(t_id, q_id);


    }
    @CrossOrigin
    @GetMapping("trainer/getAnswers")
    public List getAnswers() {
        return trainerService.getAnswers();
    }





}
