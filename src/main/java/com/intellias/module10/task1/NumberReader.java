package com.intellias.module10.task1;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

//task 1
@AllArgsConstructor
public class NumberReader {

    private List<String> phoneNumbers;

    public List<String> getValidNumbers() {
        return phoneNumbers.stream()
                .filter(number -> isFirstTypeNumber(number) || isSecondTypeNumber(number))
                .collect(Collectors.toList());
    }

    private boolean isAllDigits(String phoneNumber) {
        String preparedNumber = phoneNumber
                .replaceAll("-", "")
                .replaceAll(" ", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");

        char[] digits = preparedNumber.toCharArray();
        for (Character digit : digits) {
            if (digits.length == 10 && Character.isDigit(digit)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFirstTypeNumber(String phoneNumber) {
        return phoneNumber.length() == 12
                && phoneNumber.charAt(3) == '-'
                && phoneNumber.charAt(7) == '-'
                && isAllDigits(phoneNumber);
    }

    private boolean isSecondTypeNumber(String phoneNumber) {
        return phoneNumber.length() == 14
                && phoneNumber.charAt(0) == '('
                && phoneNumber.charAt(4) == ')'
                && phoneNumber.charAt(5) == ' '
                && phoneNumber.charAt(9) == '-'
                && isAllDigits(phoneNumber);
    }

}
