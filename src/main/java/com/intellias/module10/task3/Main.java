package com.intellias.module10.task3;

import com.intellias.module10.FileUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static Map<String, Long> count(List<String> sentences) {
        return sentences.stream()
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static void main(String[] args) {
    List<String> sentences = FileUtils.readFileLines("words.txt");

    count(sentences)
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

}
