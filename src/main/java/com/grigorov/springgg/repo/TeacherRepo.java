package com.grigorov.springgg.repo;

import com.grigorov.springgg.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepo extends CrudRepository<Teacher, Long> {
}
