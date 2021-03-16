package innso.test.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import innso.test.api.entity.Channel;

/* This repository is going to deal with Channel-Entity */
@Repository
public interface ChannelRepository extends CrudRepository<Channel, Long> {}
