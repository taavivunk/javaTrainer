package ee.valiit.javatrainer.controller;

import ee.valiit.javatrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerController {
@Autowired
    TrainerService trainerService;

//    @CrossOrigin
//    @PostMapping("trainer/newQuestionSet")
//    public void createQuestion(@RequestBody()){
//        return ;
//    }
}
