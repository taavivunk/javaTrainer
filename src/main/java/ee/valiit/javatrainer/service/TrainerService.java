package ee.valiit.javatrainer.service;


import ee.valiit.javatrainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

@Autowired
    private TrainerRepository trainerRepository;

}
