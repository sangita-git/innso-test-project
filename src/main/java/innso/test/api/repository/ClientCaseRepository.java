package innso.test.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import innso.test.api.entity.ClientCase;

/* This repository is going to deal with ClientCase-Entity */
@Repository
public interface ClientCaseRepository extends CrudRepository<ClientCase, Long>, 
                                              JpaSpecificationExecutor<ClientCase> {
	public List<ClientCase> findAll();
}
