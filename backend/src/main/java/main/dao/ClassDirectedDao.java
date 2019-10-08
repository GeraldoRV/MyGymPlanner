package main.dao;

import main.model.ClassDirected;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDirectedDao extends CrudRepository<ClassDirected, Integer> {
    List<ClassDirected> findAllByGym_Id(Integer id);
}
