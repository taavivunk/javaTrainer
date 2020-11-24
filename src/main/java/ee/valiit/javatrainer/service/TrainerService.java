package ee.valiit.javatrainer.service;


import ee.valiit.javatrainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;


}
