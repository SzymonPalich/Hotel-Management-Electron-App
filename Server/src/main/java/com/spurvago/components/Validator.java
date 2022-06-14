package com.spurvago.components;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Walidator ogólny
 */
public class Validator {
    /**
     * Sprawdzanie czy string jest mailem
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z\\d_-]+(\\.[A-Za-z\\d_-]+)*@"
                + "[^-][A-Za-z\\d-]+(\\.[A-Za-z\\d-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Sprawdzenie czy string jest pusty
     *
     * @param string
     * @return
     */
    public boolean isEmpty(String string) {
        return (string == null || string.isBlank());
    }

    /**
     * Sprawdzenie czy string nie przekroczył dopuszczalnej długości
     * @param string
     * @param maxLength
     * @return
     */

    public boolean haveLength(String string, int maxLength) {
        return (string.length() <= maxLength);
    }

    /**
     * Sprawdzenie czy string nie przekroczył dopuszczalnej długości oraz czy ma długość minimalną
     * @param string
     * @param minLength
     * @param maxLength
     * @return
     */
    public boolean haveLength(String string, int minLength, int maxLength) {
        if (minLength == 0 && isEmpty(string)) {
            return false;
        }
        return (string.length() >= minLength && string.length() <= maxLength);
    }

    /**
     * Sprawdzenie czy liczba dziesiętna jest poprawna
     * @param decimal
     * @param maxSize
     * @param maxDecimalPlaces
     * @return
     */
    public boolean correctDecimal(BigDecimal decimal, int maxSize, int maxDecimalPlaces) {
        return decimal.compareTo(BigDecimal.ZERO) >= 0 &&
                decimal.stripTrailingZeros().scale() <= maxDecimalPlaces &&
                decimal.stripTrailingZeros().precision() - decimal.stripTrailingZeros().scale() <= maxSize;
    }

    /**
     * Sprawdzenie czy int nie przekroczył dopuszczalnej wielkości
     * @param number
     * @return
     */
    public boolean intOverflow(int number) {
        return (int) (Math.log(number) + 1) < 19;
    }
}
