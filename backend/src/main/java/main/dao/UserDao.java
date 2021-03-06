package main.dao;

import main.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    Optional<User> findByUserNameAndPassword(String userName, String password);

    List<User> findAllByNameContainingAndRole(String name, String role);

    Optional<User> findByIdAndRole(Integer id, String role);

    @Query(nativeQuery = true, value = "SELECT u.* FROM user u LEFT JOIN " +
            "team t on u.id = t.leader_id WHERE t.leader_id IS NULL AND u.role= 'monitor';")
    List<User> findAllMonitorNotLeaders();

    @Query(nativeQuery = true, value = "SELECT u.* FROM db_gym.user u LEFT JOIN " +
            "db_gym.team_members t on u.id = t.members_id WHERE u.role= 'monitor' AND t.members_id IS NULL;")
    List<User> findAllMonitorsNotInATeam();
}
