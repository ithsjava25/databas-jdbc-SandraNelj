package com.example.service;

import com.example.model.MoonMission;
import com.example.repository.MoonMissionRepository;
import java.util.List;

public class MoonMissionService {
    private final MoonMissionRepository repo;

    /**
     * Create a MoonMissionService backed by the provided repository.
     *
     * @param repo the repository used for MoonMission data access
     */
    public MoonMissionService(MoonMissionRepository repo) {
        this.repo = repo;
    }
    /**
     * Retrieve all spacecraft names.
     *
     * @return a list of spacecraft names.
     */
    public List<String> listSpacecraft() {
        return repo.listSpacecraftNames();
    }
    /**
     * Retrieves the MoonMission with the specified identifier.
     *
     * @param id the mission identifier
     * @return the MoonMission with the given id
     */
    public MoonMission getById(int id) {
        return repo.getById(id);
    }

    /**
     * Count MoonMission records that occurred in the specified calendar year.
     *
     * @param year the calendar year to count missions for
     * @return the number of missions in the specified year
     */
    public int countByYear (int year) {
        return repo.countByYear(year);
    }
}