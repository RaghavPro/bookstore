package com.raghavpro.bookhive.functions;

import java.util.List;

/**
 * Custom function to check if a list contains the provided object.
 *
 * @author Raghav
 */
public class Contains {

    /**
     * To check if the a contains the object.
     * @param list The list to check in
     * @param i The int to check
     * @return {@code true} if contains or, {@code false} if not.
     */
    public static boolean contains(List list, Integer i) {
        return list.contains(i);
    }
}
