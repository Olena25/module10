package com.intellias.module10.task2;

public class Main {
    public static void main(String[] args) {
       UsersTxtToJsonTransfer txtToJsonTransfer = new UsersTxtToJsonTransfer("user.txt", "user.json");
       txtToJsonTransfer.transfer();
    }

}
