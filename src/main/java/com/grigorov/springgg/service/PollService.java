package com.grigorov.springgg.service;

import com.grigorov.springgg.entity.Poll;
import com.grigorov.springgg.entity.PollResult;
import com.grigorov.springgg.entity.Teacher;
import com.grigorov.springgg.exception.PollCaseNotStartedException;
import com.grigorov.springgg.repo.PollRepo;
import com.grigorov.springgg.repo.PollResultRepo;
import com.grigorov.springgg.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class PollService {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private PollRepo pollRepo;
    @Autowired
    private PollResultRepo pollResultRepo;
    private List<Teacher> teachers;
    private List<Teacher> pollCase;
    private Poll poll;


    public void nextPollCase(String index, Model model){
        if (pollCase == null) throw new PollCaseNotStartedException();
        int intIndex = Integer.parseInt(index);

        if(teachers.size() == 0) {
            poll.setEndDate(new Date());
            pollRepo.save(poll);
            PollResult pollResult = new PollResult();
            pollResult.setTeacher(pollCase.get(intIndex));
            model.addAttribute("winner", pollCase.get(intIndex));
            pollResult.setPoll(poll);
            pollResultRepo.save(pollResult);
            return;
        }
        Random random = new Random();
        int tmp = random.nextInt(pollCase.size());
        while(intIndex == tmp) tmp = random.nextInt(pollCase.size());
        intIndex = tmp;
        pollCase.remove(intIndex);
        Teacher teacher = teachers.get(random.nextInt(teachers.size()));
        pollCase.add(intIndex, teacher);
        teachers.remove(teacher);
        model.addAttribute("first", pollCase.get(0));
        model.addAttribute("second", pollCase.get(1));

    }

    public void startPollCase(String name, String description, Model model){
        teachers = (ArrayList<Teacher>)teacherRepo.findAll();
        pollCase = new ArrayList<>(2);
        poll = new Poll();
        poll.setBeginDate(new Date());
        poll.setName(name);
        poll.setDescription(description);
        Random random = new Random();
        while (pollCase.size() < 2) {
            Teacher teacher = teachers.get(random.nextInt(teachers.size()));
            pollCase.add(teacher);
            teachers.remove(teacher);
        }
        model.addAttribute("first", pollCase.get(0));
        model.addAttribute("second", pollCase.get(1));
    }
}
