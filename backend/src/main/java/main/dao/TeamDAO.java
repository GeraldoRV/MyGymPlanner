package main.dao;

import main.model.Team;
import main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamDAO extends CrudRepository<Team, Integer> {
    Optional<Team> findByLeader_Id(Integer id);

    Boolean existsByLeader_Id(Integer id);

    Boolean existsByMembersContains(User user);

}
