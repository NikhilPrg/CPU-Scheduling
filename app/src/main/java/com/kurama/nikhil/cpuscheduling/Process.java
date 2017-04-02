package com.kurama.nikhil.cpuscheduling;

/**
 * Created by Nikhil on 02/04/17.
 */
public class Process {
    private String name;
    private int burstTime;
    private int arrivalTime;

    public Process(String name, int bustTime, int arrivalTime) {
        this.name = name;
        this.burstTime = bustTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBustTime(int bustTime) {
        this.burstTime = bustTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
