package innso.test.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import innso.test.api.entity.Message;

/* This repository is going to deal with Message-Entity */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {}
