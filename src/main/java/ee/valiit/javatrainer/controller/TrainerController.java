package ee.valiit.javatrainer.controller;

import ee.valiit.javatrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerController {
@Autowired
    TrainerService trainerService;


}
