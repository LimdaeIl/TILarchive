package com.ex03;

import java.util.*;
import java.util.stream.Collectors;

public class Ex03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = new int[10];

        // 세 개의 숫자를 입력 받음
        String input = scanner.nextLine();
        String[] numbers = input.split(",");

        // 숫자들을 곱함
        int product = 1;
        for (String number : numbers) {
            product *= Integer.parseInt(number.trim());
        }

        // 곱한 결과를 한 글자씩 분리하여 리스트에 저장
        List<String> resultList = new ArrayList<>();
        String productString = String.valueOf(product);
        for (char digit : productString.toCharArray()) {
            resultList.add(String.valueOf(digit));
        }

        // 결과 배열에 저장
        for (String digit : resultList) {
            array[Integer.parseInt(digit)] += 1;
        }

        // 결과 출력
        for (int i : array) {
            System.out.println(i);
        }
    }
}
