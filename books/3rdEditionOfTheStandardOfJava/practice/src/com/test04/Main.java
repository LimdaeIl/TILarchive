package com.test04;

public class Main {
    public static void main(String[] args) {
        Airplane airplane = new Airplane("L777", 1000); // 인스턴스 생성으로 이름, 연료 크기 제공
        Cargoplane cargoplane = new Cargoplane("C50", 1000);

        String message = """
                Plane fuelSize
                -------------------
                """;

        System.out.print(message);
        System.out.println(airplane.getPlaneName() + " " + airplane.getFuelSize()); // airplane 으로 get 메서드 수행
        System.out.println(cargoplane.getPlaneName() + " " + cargoplane.getFuelSize()); // cargoplane 으로 get 메서드 수행
        System.out.println("100 운항\n");

        System.out.print(message);
        airplane.flight(100); // flight 추상 메서드
        cargoplane.flight(100);
        System.out.println(airplane.getPlaneName() + " " + airplane.getFuelSize());
        System.out.println(cargoplane.getPlaneName() + " " + cargoplane.getFuelSize());
        System.out.println("200 주유\n");

        System.out.print(message);
        airplane.refuel(200); // refuel 메서드
        cargoplane.refuel(200);
        System.out.println(airplane.getPlaneName() + " " + airplane.getFuelSize());
        System.out.println(cargoplane.getPlaneName() + " " + cargoplane.getFuelSize());

    }
}