package com.practice;

import java.util.ArrayList;
import java.util.List;

public class Ex1 {
    public static void main(String[] args) {

        List<Integer> lists = new ArrayList<>();
        List<Integer> lists1 = new ArrayList<>();

        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);

        lists1.add(6);
        lists1.add(7);
        lists1.add(8);
        lists1.add(9);
        lists1.add(1);

        System.out.println(lists1.contains(1));

        lists.remove(3);

        for (Integer list : lists) {
            System.out.println(list);
        }

    }
}
