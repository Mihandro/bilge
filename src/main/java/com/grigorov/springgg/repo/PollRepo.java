package com.grigorov.springgg.repo;

import com.grigorov.springgg.entity.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepo extends CrudRepository<Poll, Long> {
}
