package com.codegym.customermanager.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private static final  String REGEX_NAME = "^[A-Za-z][A-Za-z ]{7,19}$";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";

    public static boolean isNameValid(String name) {
        return Pattern.matches(REGEX_NAME, name);
    }
    public static boolean isEmailValid(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
}
