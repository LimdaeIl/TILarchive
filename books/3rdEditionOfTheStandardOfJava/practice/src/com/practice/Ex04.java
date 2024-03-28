package com.practice;

import java.util.*;


public class Ex04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 암기 가능한 단어 개수
        int W = scanner.nextInt(); // 단어의 수

        scanner.nextLine(); // 개행 문자 처리

        String[] words = scanner.nextLine().split(" "); // 영어단어 리스트

        Map<String, Integer> memory = new LinkedHashMap<>(); // 암기한 단어와 그 시점을 저장할 맵
        int time = 0; // 총 걸린 시간

        for (String word : words) {
            if (memory.containsKey(word)) { // 이미 암기한 단어인 경우
                memory.put(word, time); // 해당 단어의 시점을 갱신
                time += 1; // 1초 소요됨
            } else {
                if (memory.size() < N) { // 빈 자리가 있을 경우
                    memory.put(word, time); // 새로운 단어를 암기
                    time += 3; // 3초 소요됨
                } else {
                    int oldestTime = Integer.MAX_VALUE;
                    String oldestWord = "";
                    for (Map.Entry<String, Integer> entry : memory.entrySet()) {
                        if (entry.getValue() < oldestTime) {
                            oldestTime = entry.getValue();
                            oldestWord = entry.getKey();
                        }
                    }
                    memory.remove(oldestWord); // 가장 오래된 단어를 잊음
                    memory.put(word, time); // 새로운 단어를 암기
                    time += 3; // 3초 소요됨
                }
            }
        }

        System.out.println(time); // 총 걸린 시간 출력
    }
}