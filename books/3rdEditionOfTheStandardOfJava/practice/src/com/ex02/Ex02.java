package com.ex02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> integerList = new ArrayList<>(); // Integer 타입 리스트
        int sum = 0;

        String inputString = scanner.nextLine();
        String[] splitString = inputString.split(","); // ,  로 구분해서 String 배열에 저장

        for (String s : splitString) { // 문자열 배열 -> Integer 리스트 변환
            if (Integer.parseInt(s) % 2 == 1) {
                integerList.add(Integer.parseInt(s)); // Integer 리스트에 홀수를 저장
                sum += Integer.parseInt(s); // 모든 홀수의 합 변수에 저장
            }
        }

        if (integerList.isEmpty()) {
            System.out.println("-1"); // 홀수가 없다면, -1
        } else {
            int minimumValue = Collections.min(integerList); // 최소값
            System.out.println(sum);
            System.out.println(minimumValue);
        }

    }
}
