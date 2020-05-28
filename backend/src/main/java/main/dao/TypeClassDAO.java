package main.dao;

import main.model.TypeClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeClassDAO extends CrudRepository<TypeClass,Integer> {
    List<TypeClass> findAllByTeam_Id(Integer id);
}
