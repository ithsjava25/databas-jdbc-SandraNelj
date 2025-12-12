package com.example.service;

import com.example.model.MoonMission;
import com.example.repository.MoonMissionRepository;
import java.util.List;

public class MoonMissionService {
    private final MoonMissionRepository repo;

    public MoonMissionService(MoonMissionRepository repo) {
        this.repo = repo;
    }
    public List<String> listSpacecraft() {
        return repo.listSpacecraftNames();
    }
    public MoonMission getById(int id) {
        return repo.getById(id);
    }

    public int countByYear (int year) {
        return repo.countByYear(year);
    }
}
