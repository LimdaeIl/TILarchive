package com.test04;

public class Cargoplane extends Plane {
    public Cargoplane() {
        super();
    }

    public Cargoplane(String planeName, int fuelSize) {
        super(planeName, fuelSize);
    }


    @Override
    public void flight(int distance) {
        int cargoPlaneFuelSize = this.getFuelSize();
        this.setFuelSize(cargoPlaneFuelSize - distance * 5); //  10운항시 연료50감소
    }
}
