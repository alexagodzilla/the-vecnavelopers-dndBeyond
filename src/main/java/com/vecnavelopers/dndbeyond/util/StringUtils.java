package com.vecnavelopers.dndbeyond.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) return input;

        return Arrays.stream(input.split(" "))
                .map(word -> word.equalsIgnoreCase("or")
                        ? "OR"
                        : word.length() > 0
                        ? word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()
                        : word)
                .collect(Collectors.joining(" "));
    }

}