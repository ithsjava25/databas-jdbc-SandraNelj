package com.example.model;

import java.sql.Date;

public class MoonMission {
    private int missionId;
    private String spacecraft;
    private Date launchDate;
    private String carrierRocket;
    private String operator;
    private String missionType;
    private String outcome;

    /**
     * Create a MoonMission populated with the provided mission attributes.
     *
     * @param missionId     unique identifier for the mission
     * @param spacecraft    name or designation of the spacecraft
     * @param launchDate    launch date of the mission
     * @param carrierRocket name of the carrier rocket used for launch
     * @param operator      organization responsible for operating the mission
     * @param missionType   classification of the mission (e.g., crewed, uncrewed)
     * @param outcome       recorded outcome or result of the mission
     */
    public MoonMission(int missionId, String spacecraft, Date launchDate,
                       String carrierRocket, String operator, String missionType, String outcome) {
        this.missionId = missionId;
        this.spacecraft = spacecraft;
        this.launchDate = launchDate;
        this.carrierRocket = carrierRocket;
        this.operator = operator;
        this.missionType = missionType;
        this.outcome = outcome;
    }
    /**
 * Gets the mission's unique identifier.
 *
 * @return the mission identifier
 */
public int getMissionId() { return missionId; }
    /**
 * Gets the spacecraft name for the mission.
 *
 * @return the spacecraft name
 */
public String getSpacecraft() { return spacecraft; }
    /**
 * Retrieves the mission's launch date.
 *
 * @return the launch date of the mission
 */
public Date getLaunchDate() { return launchDate; }
    /**
 * Retrieves the carrier rocket used for the mission.
 *
 * @return the carrier rocket used for the mission
 */
public String getCarrierRocket() { return carrierRocket; }
    /**
 * The organization or entity responsible for operating the spacecraft.
 *
 * @return the operator of the mission
 */
public String getOperator() { return operator; }
    /**
 * Retrieve the mission's type.
 *
 * @return the mission type
 */
public String getMissionType() { return missionType; }
    /**
 * The mission's outcome description.
 *
 * @return the mission outcome as a String
 */
public String getOutcome() { return outcome; }
}