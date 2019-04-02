package main.dao;

import main.model.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<Test,Integer> {
}
