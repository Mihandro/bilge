package com.grigorov.springgg.service;

import com.grigorov.springgg.entity.PollResult;
import com.grigorov.springgg.entity.Teacher;
import com.grigorov.springgg.repo.PollResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollResultService {
    @Autowired
    private PollResultRepo pollResultRepo;

    public List<PollResult> findAll(){
        return (ArrayList<PollResult>)pollResultRepo.findAll();
    }
    public List<Teacher> getWinners() {
        List<PollResult> results = (ArrayList<PollResult>)pollResultRepo.findAll();
        List<Teacher> winners = new ArrayList<>();
        for (PollResult res: results) {
            winners.add(res.getTeacher());
        }
        return winners;
    }
}
