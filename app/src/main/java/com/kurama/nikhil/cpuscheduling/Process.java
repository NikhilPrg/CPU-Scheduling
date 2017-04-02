package com.kurama.nikhil.cpuscheduling;

/**
 * Created by Nikhil on 02/04/17.
 */
public class Process {
    private String name;
    private int burstTime;
    private int originalBurstTime;
    private int OriginalArrivalTime;
    private int arrivalTime;
    private int idNum;

    public Process(String name, int bustTime, int arrivalTime) {
        this.name = name;
        this.burstTime = bustTime;
        this.originalBurstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.OriginalArrivalTime = arrivalTime;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void subBurstTime(){
        burstTime--;
    }

    public void subArrivalTime() {
        arrivalTime--;
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
