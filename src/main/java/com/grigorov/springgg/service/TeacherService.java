package com.grigorov.springgg.service;

import com.grigorov.springgg.entity.Teacher;
import com.grigorov.springgg.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;

    public void getAllTeachers(Model model) {
        model.addAttribute ("teachers", teacherRepo.findAll());
    }

}
