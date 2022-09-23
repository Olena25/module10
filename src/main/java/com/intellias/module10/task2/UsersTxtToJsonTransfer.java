package com.intellias.module10.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellias.module10.FileUtils;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// task 2
@AllArgsConstructor
public class UsersTxtToJsonTransfer {

    private String fileNameFrom;
    private String fileNameTo;

    public void transfer() {
        if(fileNameFrom.isEmpty() || fileNameTo.isEmpty()) {
            throw new IllegalArgumentException("File names should be filled");
        }
        List<User> users = readUsersFromFile();
        writeUsersInJsonFormat(users);
    }

    private List<User> readUsersFromFile() {
        List<String> userData = FileUtils.readFileLines(fileNameFrom);

        return userData.stream()
                .skip(1)
                .map(line -> line.split(" "))
                .filter(lineElements -> lineElements.length == 2)
                .map(UsersTxtToJsonTransfer::mapLineToUser)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private void writeUsersInJsonFormat(List<User> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Main.class.getClassLoader().getResource(fileNameTo).getPath());
        try {
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Optional<User> mapLineToUser(String[] userLineElements) {
        String ageElement = userLineElements[1];

        try {
            int age = Integer.parseInt(ageElement);

            return Optional.of(new User(userLineElements[0], age));

        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
