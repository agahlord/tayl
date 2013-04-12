package org.agahlord.tayl.ui.util;

/**
 *
 * @author john.vasquez
 */
public class TextUtils {

    private TextUtils(){}
    
    public static String clipText(String text, int maximumChars, boolean fromStart) {
        if( maximumChars < 10 ) {
            throw new IllegalArgumentException("maximumChars must be greater than 10.");
        }
        
        String returnedText = null;
        if( text != null && text.length() > maximumChars ) {
            if( fromStart ) {
                returnedText = "..." + text.substring(text.length() - (maximumChars - 2), text.length());
            } else {
                returnedText = text.substring(0, maximumChars - 3) + "...";
            }
        } else {
            returnedText = text;
        }
        
        return returnedText;
    } 
    
}
