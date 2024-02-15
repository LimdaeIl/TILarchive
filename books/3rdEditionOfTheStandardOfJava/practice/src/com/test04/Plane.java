package com.test04;

public abstract class Plane {
    private String planeName;
    private int fuelSize;

    public Plane() {} // 기본 생성자

    public Plane(String planeName, int fuelSize) {
        this.fuelSize = fuelSize;
        this.planeName = planeName;
    }



    // 주유
    public void refuel(int fuel) {
        this.setFuelSize(this.getFuelSize() + fuel);
    };

    // 운항
    public abstract void flight(int distance);


// --- getter, setter 메서드 ---
    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public int getFuelSize() {
        return fuelSize;
    }

    public void setFuelSize(int fuelSize) {
        this.fuelSize = fuelSize;
    }
}
