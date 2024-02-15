package com.test05;

import java.util.HashMap;

public class Company {
    public static void main(String[] args) {
        HashMap<Integer, Employee> map = new HashMap<>();
        Secretary secretary = new Secretary("홍길동", 1, "Secretary", 800);
        Sales sales = new Sales("이순신", 2, "Sales", 1200);

        map.put(1, secretary);
        map.put(2, sales);

        System.out.println("name\t\t\tdepartment\t\tsalary" + "\n-----------------------------------------");
        for (Integer i : map.keySet()) {
            System.out.printf("%4s %18s %12d", map.get(i).getName(), map.get(i).getDepartment(), map.get(i).getSalary());
            System.out.println();
        }

        System.out.println();
        System.out.println("인센티브100지급");
        System.out.println();

        secretary.incentive(100);
        sales.incentive(100);

        System.out.println("name\t\t\tdepartment\t\tsalary\ttax" + "\n-----------------------------------------");
        for (Integer i : map.keySet()) {
            System.out.printf("%4s %18s %12d %.2f",
                    map.get(i).getName(), map.get(i).getDepartment(), map.get(i).getSalary(), map.get(i).tax());
            System.out.println();
        }

    }
}
