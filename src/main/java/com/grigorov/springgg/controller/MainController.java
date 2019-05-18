package com.grigorov.springgg.controller;

import com.grigorov.springgg.repo.PollResultRepo;
import com.grigorov.springgg.service.PollResultService;
import com.grigorov.springgg.service.PollService;
import com.grigorov.springgg.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@Controller
public class MainController {
    @Autowired
    private PollService pollService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PollResultService pollResultService;

    @PostMapping("poll")
    public String poll(@RequestParam(required = false) String name, @RequestParam(required = false) String description, Model model) {
        pollService.startPollCase(name, description, model);

        return "poll";
    }

    @GetMapping("next/{index}")
    public String next(@PathVariable String index, Model model){
        pollService.nextPollCase(index, model);
        if(((HashMap<String, ?>) model).size() < 2) return "finish";
        return "poll";
    }

    @GetMapping("/teachers")
    public String teachers(Model model){
        teacherService.getAllTeachers(model);
        return "teachers";
    }

    @GetMapping("/start")
    public String start(){
        return "start";
    }

    @GetMapping("/winners")
    public String winners(Model model){
        List<?> results = pollResultService.findAll();
        model.addAttribute("results", results);
        return "winners";
    }
}
