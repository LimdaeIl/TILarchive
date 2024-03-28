package com.practice;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine()); // 점수 갯수
        int[] scores = new int[count]; // 점수 저장
        int result = 0;

        String[] stringSplitArrays = scanner.nextLine().split(" "); // 점수 String 배열에 저장

        String firstObstruction = scanner.nextLine().toUpperCase(); // 첫번째 방해물
        String secondObstruction = scanner.nextLine().toUpperCase(); // 두번째 방해물

        for (int i = 0; i <count; i++) { // 점수 int 배열에 저장
            scores[i] = Integer.parseInt(stringSplitArrays[i]);
        }

        for (int i = 0; i < 10; i++) { // 총 점수
            if (firstObstruction.charAt(i) == secondObstruction.charAt(i) && secondObstruction.charAt(i) == 'O' && firstObstruction.charAt(i) == 'O') {
                result += scores[i];
            }
        }

        System.out.println("총 점수");
        System.out.println(result);

    }
}
