package main.dao;

import main.model.TypeClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeClassDAO extends CrudRepository<TypeClass,Integer> {
}
