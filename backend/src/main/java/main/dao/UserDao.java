package main.dao;

import main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUserNameAndPassword(String userName, String password);

    List<User> findAllByNameContainingAndRole(String name, String role);

}
