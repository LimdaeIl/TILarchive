package com.ex01;

import java.util.Scanner;

public class Ex01 {
    private static final String MESSAGE = "Hello MyCompany!"; // 출력내용

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt(); // 반복횟수

        while (input != 0) { // 반복횟수가 0 이면 while 문 벗어난다.
            System.out.println(MESSAGE);
            input--;
        }
    }
}
