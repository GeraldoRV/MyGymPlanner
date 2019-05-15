package main.service;

import main.dao.GymDao;
import main.model.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GymService {
    @Autowired
    private GymDao gymDao;

    public Optional<Gym> getGym(Integer id) {
        return gymDao.findById(id);
    }
}
