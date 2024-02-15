package com.test05;

public class Sales extends Employee implements Bonus {

    public Sales() {
    }

    public Sales(String name, int number, String department, int salary) {
        super(name, number, department, salary);
    }

    @Override
    public double tax() { // 세금
        return getSalary() * 0.13;
    }

    @Override
    public void incentive(int pay) { // 인센티브 지급
        int addSalary = getSalary() + (int)(pay * 1.2);
        setSalary(addSalary);
    }
}
