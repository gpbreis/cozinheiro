package dev.gpbreis.cozinheiro.utils;

public class UtilsString {

    public static boolean emptyString(String string) {
        return string == null || string.trim().length() == 0;
    }
}
