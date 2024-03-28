package com.practice;

import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("시작수: ");
        int start = Integer.parseInt(scanner.nextLine());
        System.out.print("끝수: ");
        int end = Integer.parseInt(scanner.nextLine());
        System.out.print("배수: ");
        int multiple = Integer.parseInt(scanner.nextLine());
        int result = 0;

        if (start < multiple) { // 8의 배수 : 8, 16, 24, ...  1 부터 시작해도 결국 8의 배수는 8부터 시작.
            start = multiple;
        }

        while (start < end) {
            result += start;
            start *= 2;
        }
        System.out.println(result);
    }
}
