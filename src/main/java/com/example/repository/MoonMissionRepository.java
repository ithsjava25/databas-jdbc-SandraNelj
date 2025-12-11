package com.example.repository;
import com.example.model.MoonMission;
import java.util.List;


public interface MoonMissionRepository {
    /**
 * Retrieve the names of all spacecraft recorded in the repository.
 *
 * @return a list of spacecraft names; an empty list if no spacecraft are recorded
 */
List<String> listSpacecraftNames();
    /**
 * Retrieves the moon mission identified by the given mission identifier.
 *
 * @param missionId the identifier of the mission to retrieve
 * @return the MoonMission with the specified identifier, or null if no matching mission exists
 */
MoonMission getById(int missionId);
    /**
 * Counts missions that took place in the specified calendar year.
 *
 * @param year the calendar year to count missions for
 * @return the number of moon missions in the specified year, or 0 if none
 */
int countByYear(int year);
}