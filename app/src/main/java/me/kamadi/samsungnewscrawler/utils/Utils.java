package me.kamadi.samsungnewscrawler.utils;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class Utils {
    public static String decapitalize(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }
}
