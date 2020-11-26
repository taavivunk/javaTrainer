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
    @GetMapping("trainer/allAnswers")       //  toob kogu vastuste andmebaasi (rowmapper)
    public List getAnswers() {
        return trainerService.getAnswers();
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
    public Map getQuestionFromTopic(@PathVariable("nr") Long t_id) {
        return trainerService.getQFromTopic(t_id);
    }


    //TODO TÖÖ POOLELI!
    @CrossOrigin
    @PostMapping("trainer/submitAnswer")
    public String submitAnswer(@RequestParam("qid") long q_id,
                               @RequestParam("aid") long a_id,
                               @RequestParam("sid") String student_id) {

        return trainerService.submitAnswer(q_id, a_id, student_id);


    }

}
