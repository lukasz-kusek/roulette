package com.github.lukaszkusek.roulette.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

/**
 * Converts amount between long and String.
 * Allowed amount:
 * - is positive
 * - is less then MAX_LONG / 100
 * - has maximum 2 digits after comma.
 */
public class AmountConverter {

    private static final long MIN_VALUE = 0;
    private static final int MAX_FRACTIONAL_PART_LENGTH = 2;
    private static final int INTEGER_PART_FACTOR = 100;

    private AmountConverter() {
    }

    public static long convert(String value) {
        Iterable<String> split = Splitter.on(".").split(value);
        long integerPart = extractIntegerPart(split);
        int fractionalPart = extractFractionalPart(split);

        long longValue = integerPart * INTEGER_PART_FACTOR + fractionalPart;

        if (longValue <= MIN_VALUE) {
            return reportAmountIsLessThanMinimum(value);
        }

        return longValue;
    }

    private static long reportAmountIsLessThanMinimum(String value) {
        throw new IllegalArgumentException(
                "Amount is less than minimum: " + value
                        + ". Amount must be greater than " + MIN_VALUE + ".");
    }

    private static long extractIntegerPart(Iterable<String> split) {
        long integerPart = getIntegerPart(split);

        if (integerPart > Long.MAX_VALUE / INTEGER_PART_FACTOR) {
            return reportAmountIsTooBig(integerPart);
        }

        return integerPart;
    }

    private static long getIntegerPart(Iterable<String> split) {
        String integerPartString = null;
        long integerPart;
        try {
            integerPartString = Iterables.get(split, 0);
            integerPart = Long.valueOf(integerPartString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(integerPartString + " is not a correct long value.");
        }
        return integerPart;
    }

    private static long reportAmountIsTooBig(long integerPart) {
        throw new IllegalArgumentException(
                "Amount is too big: " + integerPart
                        + ". Maximum amount is " + Long.MAX_VALUE / INTEGER_PART_FACTOR + ".");
    }

    private static int extractFractionalPart(Iterable<String> split) {
        String fractionalPart = Iterables.get(split, 1, "0");

        if (fractionalPart.length() > MAX_FRACTIONAL_PART_LENGTH) {
            return reportFractionalPartIsTooLong(fractionalPart);
        }

        fractionalPart = "0." + fractionalPart;
        Float floatValue = Float.valueOf(fractionalPart) * INTEGER_PART_FACTOR;

        return floatValue.intValue();
    }

    private static int reportFractionalPartIsTooLong(String fractionalPart) {
        throw new IllegalArgumentException(
                "Fractional part of amount value is too long: " + fractionalPart.length()
                        + ". Maximum length is " + MAX_FRACTIONAL_PART_LENGTH + ".");
    }

    public static String convert(long amount) {
        return String.valueOf(((Long) amount).doubleValue() / 100);
    }
}
