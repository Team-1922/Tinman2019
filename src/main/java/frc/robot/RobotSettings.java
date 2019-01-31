/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotSettings {
    private int m_frontLeft;
    private int m_frontRight;
    private int m_rearLeft;
    private int m_rearRight;


    /**
     * @return the m_frontLeft
     */
    public int getM_frontLeft() {
        return m_frontLeft;
    }

    /**
     * @return the m_frontRight
     */
    public int getM_frontRight() {
        return m_frontRight;
    }

    /**
     * @return the m_rearLeft
     */
    public int getM_rearLeft() {
        return m_rearLeft;
    }

    /**
     * @return the m_rearRight
     */
    public int getM_rearRight() {
        return m_rearRight;
    }

    /**
     * @param m_frontLeft the m_frontLeft to set
     */
    public void setM_frontLeft(int m_frontLeft) {
        this.m_frontLeft = m_frontLeft;
    }

    /**
     * @param m_frontRight the m_frontRight to set
     */
    public void setM_frontRight(int m_frontRight) {
        this.m_frontRight = m_frontRight;
    }

    /**
     * @param m_rearLeft the m_rearLeft to set
     */
    public void setM_rearLeft(int m_rearLeft) {
        this.m_rearLeft = m_rearLeft;
    }

    /**
     * @param m_rearRight the m_rearRight to set
     */
    public void setM_rearRight(int m_rearRight) {
        this.m_rearRight = m_rearRight;
    }

    
}
