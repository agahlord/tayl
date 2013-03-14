package org.agahlord.tayl;

/**
 * Handler of the reading events done by the associated {@link TailReader}s.
 * @author John Vásquez
 */
public interface TailHandler {
    
    /**
     * Handles when a new line was read. Most of the time, the line will contain
     * the line separator; but in some specific circumstances, and depending on
     * how the file is written on, it could contain multiple line separators, or
     * no separators at all.
     * @param line 
     */
    public void lineRead(String line);
    
    /**
     * Handles when the read file has been truncated. Truncation could be done 
     * by replace, rotation or elimination.
     * <br/><br/>
     * NOTE: There is no guarantee that a {@link TailReader} will tell when a 
     * file is truncated, so this method could never be called.
     */
    public void fileTruncated();
    
    /**
     * Handles when an exception has occurred while reading the file. The 
     * exception could or could not stop the file reading, so checking the 
     * reading with {@link TailReader#isReading()} is advised.
     * @param exception the exception thrown
     */
    public void exceptionThrown(Exception exception);
    
}
