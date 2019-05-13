package com.grigorov.springgg.controller;


import com.grigorov.springgg.entity.Poll;
import com.grigorov.springgg.entity.PollResult;
import com.grigorov.springgg.entity.Teacher;
import com.grigorov.springgg.repo.PollRepo;
import com.grigorov.springgg.repo.PollResultRepo;
import com.grigorov.springgg.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class MainController {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private PollRepo pollRepo;
    @Autowired
    private PollResultRepo pollResultRepo;
    private List<Teacher> teachers;
    private Teacher first;
    private Teacher second;
    private Poll poll;

    @GetMapping("/")
    public String index(Model model) {
        poll = new Poll();
        poll.setBeginDate(new Date());
        poll.setName("Poll");
        poll.setDescription("lorem ipsum");
        Random random = new Random();
        teachers = (ArrayList<Teacher>)teacherRepo.findAll();
        first = teachers.get(random.nextInt(teachers.size()));
        teachers.remove(first);
        second = teachers.get(random.nextInt(teachers.size()));
        teachers.remove(second);

        model.addAttribute("first", first);
        model.addAttribute("second", second);
        return "poll";
    }

    @GetMapping("next/{index}")
    public String next(@PathVariable String index, Model model){
        int intIndex = Integer.parseInt(index);
        if(teachers.size() == 0) {
            poll.setEndDate(new Date());
            pollRepo.save(poll);
            PollResult pollResult = new PollResult();
            if(intIndex == 0) pollResult.setTeacher(second);
            else if(intIndex == 1) pollResult.setTeacher(first);
            pollResult.setPoll(poll);
            pollResultRepo.save(pollResult);
            return "index";
        }

        Random random = new Random();
        Teacher next = teachers.get(random.nextInt(teachers.size()));
        teachers.remove(next);
        if(intIndex == 0) {
            first = next;
        } else if(intIndex == 1) {
            second = next;
        }
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        return "poll";
    }

    @GetMapping("/teachers")
    public String teachers(Model model){
        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }
}
