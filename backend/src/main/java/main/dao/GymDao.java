package main.dao;

import main.model.Gym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymDao extends CrudRepository<Gym,Integer> {
}
