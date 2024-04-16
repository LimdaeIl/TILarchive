package com.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class chap02 {
    // 1 + 2 + 6 + 8 + 8 + 6 + 3 + 4 + 4 + 3 + 2 + 1 = 48
    int Line = 1;
    int calorie = 0;
    int myLocation = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> contour = Arrays.stream(sc.nextLine().split("")).toList();

        // 1. 등고선의 시작 확인하기
        if (!contour.getFirst().equals("(") && !contour.getFirst().equals("#")) {
            System.out.println("등고선은 ( 또는 # 으로 시작해야 합니다!");
        }

        // 2. 등고선의 올라가기, 내려가기, 급경사 갯수 확인하기
        int upTemp = 0;
        int downTemp = 0;
        int scarpTemp = 0;
        int index = 0;
        int scarpStartIndex = 0;


        for (String s : contour) {
            switch (s) {
                case "(" -> upTemp++;
                case ")" -> downTemp++;
                case "#" -> scarpTemp++;
            }

            if (s.equals("#")) {
                int trigger = 1;

                if (index == 0) {
                    for (int i = 1; i < contour.size(); i++) {
                        if (!contour.get(i).equals("(") && !contour.get(i).equals("#")) {
                            trigger = 0;
                            break;
                        }
                    }

                } else {
                    String tempStr = contour.get(index - 1);

                    for (int i = index + 1; i < contour.size(); i++) {
                        if (!tempStr.equals(contour.get(i)) && !contour.get(i).equals("#")) {
                            trigger = 0;
                            break;
                        }
                    }
                }

                if (trigger == 1) {
                    System.out.println("급경사는 올라가기만 또는 내려가기만 할 수 없습니다!");
                } else {
                    break;
                }
            }
            index++;
        }
        if (upTemp == downTemp && index != contour.size() - 1) {
            System.out.println("등고선은 모두 이어진 하나의 산이어야 합니다!");
        }

        if (upTemp != downTemp) {
            System.out.println("등고선의 올라가기와 내려가기 개수는 동일해야 합니다!");
        }
    }
}