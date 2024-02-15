package com.test04;

public class Airplane extends Plane {
    public Airplane() {
        super();
    }

    // com.test04.Airplane 생성자로 com.test04.Plane 생성자를 호출
    public Airplane(String planeName, int fuelSize) {
        super(planeName, fuelSize);
    }

    @Override
    public void flight(int distance) {
        int airplaneFuelSize = this.getFuelSize(); // 현재 연료 크기
        this.setFuelSize(airplaneFuelSize - distance * 3); //  10 운항 시 연료 30 감소 -> 1 운항 시 연료 3 감소
    }
}
