package com.example.repository;
import com.example.model.MoonMission;
import java.util.List;


public interface MoonMissionRepository {
    List<String> listSpacecraftNames();
    MoonMission getById(int missionId);
    int countByYear(int year);
}
