package main.dao;

import main.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDAO extends CrudRepository<Team,Integer> {
    Team findByLeader_Id(Integer id);
}
