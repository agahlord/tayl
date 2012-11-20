package org.agahlord.tayl.util;

/**
 * Utility class to work with {@code String} objects.
 * @author John Vásquez
 */
public class Strings {

    private Strings(){}
    
    /**
     * Tells if the given {@code String} is null, has zero length, or only 
     * contains white spaces.
     * @param s the {@code String}
     * @return if the given {@code String} is null, has zero length, or only 
     * contains white spaces
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty(); 
    }
    
    /**
     * Tells if the given {@code String} is null or has zero length.
     * @param s the {@code String}
     * @return if the given {@code String} is null or has zero length
     */
    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty(); 
    }
    
}
