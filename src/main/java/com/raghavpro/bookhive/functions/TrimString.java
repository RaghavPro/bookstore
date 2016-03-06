package com.raghavpro.bookhive.functions;

/**
 * Trims the string and appends ellipsis after it so it does not mess the html
 * .
 * @author Raghav
 */
public class TrimString {

    /**
     * Trims the string if it's longer than desired character limit and appends ellipsis (three dots).
     * @param str The string
     * @param chars The desired character limit.
     * @return Trimmed string
     */
    public static String trim(String str, int chars) {
        // If the title, author etc. are long, we get first 40 chars and append("...") at the end
        if (str.length() > chars)
            str = str.substring(0, chars) + "...";
        return str;
    }

}
