package com.intellias.module10.task1;

import com.intellias.module10.FileUtils;

public class Main {
    public static void main(String[] args) {
        NumberReader numberReader = new NumberReader(FileUtils.readFileLines("file.txt"));

        System.out.println(numberReader.getValidNumbers());
    }
}
