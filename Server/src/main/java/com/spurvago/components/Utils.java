package com.spurvago.components;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Metody ogólne
 */
public class Utils {
    public static boolean validateEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);


        return matcher.matches();
    }

    /**
     * String na query SQL like
     * @param string
     * @return
     */
    public static String asLikeQuery(String string) {
        return "%" + string + "%";
    }

    /**
     * Pusty string
     * @param string
     * @return
     */
    public static boolean isNullOrBlank(String string) {
        return (string == null || string.isBlank());
    }

    /**
     * Zwraca obj jako JSON
     * @param obj
     * @return
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
