package com.spurvago.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean isEmpty(String string) {
        return (string == null || string.isBlank());
    }

    public boolean haveLength(String string, int maxLength) {
        return (string.length() <= maxLength);
    }

    public boolean haveLength(String string, int minLength, int maxLength) {
        if (minLength == 0 && isEmpty(string)) {
            return false;
        }
        return (string.length() >= minLength && string.length() <= maxLength);
    }

    public static boolean isEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z\\d_-]+(\\.[A-Za-z\\d_-]+)*@"
                + "[^-][A-Za-z\\d-]+(\\.[A-Za-z\\d-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
