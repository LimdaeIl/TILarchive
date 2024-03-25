package com.ex04;

import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.nextLine().toCharArray(); // 한 글자씩 배열에 저장
        int asciiNumber = 0; // 문자 -> 아스키 변환된 숫자 저장
        int temp = 0; // 알파벳 아스키 범위 벗어나면 계산을 저장할 변수

        for (char aChar : chars) { // 배열에서 문자 1개씩 가져와서
            asciiNumber = aChar + 4; // 문자 + 4

            if (asciiNumber >= 97 && asciiNumber <= 122) { // 소문자 아스키 코드 구간이면,

                System.out.print((char) asciiNumber); // 'A' - 'Z' 속하면 그대로 asciiNumber 를 문자로 출력

            } else if (asciiNumber > 122) { // 'A' - 'Z' 범위 벗어나면,
                temp = asciiNumber - 122 + 97 - 1; // 예를 들어, 125 - 122 = 3 계산하고 3 + 97 = 100 계산. d 가 나옴
                System.out.print((char) temp);
            } else if (asciiNumber >= 65 && asciiNumber <= 90) { // 대문자 아스키 코드 구간이면, 이하 동일

                System.out.print((char) asciiNumber);

            } else if (asciiNumber > 90) {
                temp = asciiNumber - 90 + 65 - 1;
                System.out.print((char) temp);
            }
        } // HkRAuKq
    }
}
