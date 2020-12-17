package main.service;

import main.dao.GymDao;
import main.model.Gym;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GymService {

    private final GymDao gymDao;

    public GymService(GymDao gymDao) {
        this.gymDao = gymDao;
    }

    public Optional<Gym> getGym(Integer id) {
        return gymDao.findById(id);
    }
}
