package org.agahlord.tayl;

import java.nio.charset.Charset;
import org.agahlord.tayl.util.Strings;

/**
 * Base configuration for {@link TailReader}s.
 * @author John Vásquez
 * @since 1.0
 */
public class TailConfiguration {
    
    private String filePath;
    
    private int lines;
    private Charset charset;
    
    private boolean spanThread;
    private long delay;
            
    /**
     * Creates a default configuration using the given file path.
     * @param filePath the path to the file to be read
     */
    public TailConfiguration(String filePath) {
        if( Strings.isBlank(filePath) ) {
            throw new IllegalArgumentException("filePath cannot be null or empty.");
        }
        
        this.lines = 10;
        this.charset = Charset.forName("UTF-8");
        
        this.spanThread = true;
        this.delay = 1000;
        
        this.filePath = filePath;
    }

    /**
     * Gets the number of lines to be peeked from the end of the file (this is, 
     * the file's tail). By default, 10 lines are peeked. 
     * @return  the number of lines to be peeked
     */
    public int getLines() {
        return lines;
    }

    /**
     * Sets the number of lines to be peeked from the end of the file (this is, 
     * the file's tail). By default, 10 lines are peeked. 
     * @param lines the number of lines to be peeked
     */
    public void setLines(int lines) {
        if( lines < 0 || lines > 5000 ) {
            throw new IllegalArgumentException("lines cannot be less than 0 and greater than 5000.");
        }
        
        this.lines = lines;
    }

    /**
     * Gets the file's encoding character set to be used while reading lines. 
     * By default, UTF-8 encoding is used. 
     * @return the character set
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * Sets the file's encoding character set to be used while reading lines. 
     * By default, UTF-8 encoding is used. 
     * @param charset the character set
     */
    public void setCharset(String charset) {
        if( !Charset.isSupported(charset) ) {
            throw new IllegalArgumentException("charset '" + charset + "' is not supported.");
        }
        
        this.charset = Charset.forName(charset);
    }
    
    /**
     * Sets the file's encoding character set to be used while reading lines. 
     * By default, UTF-8 encoding is used. 
     * @param charset the character set
     */
    public void setCharset(Charset charset) { 
        if( charset == null ) {
            throw new IllegalArgumentException("charset cannot be null.");
        }
        
        this.charset = charset;
    }

    /**
     * Tells if the reader must span a new thread to avoid blocking while 
     * reading. By default, a new thread must be spanned.
     * @return {@code true} if the reader should span a new thread
     */
    public boolean isSpanThread() {
        return spanThread;
    }

    /**
     * Sets if the reader must span a new thread to avoid blocking while 
     * reading. By default, a new thread must be spanned.
     * @param spanThread if the reader should span a new thread or not
     */
    public void setSpanThread(boolean spanThread) {
        this.spanThread = spanThread;
    }
    
    /**
     * Gets the amount of time (in milliseconds) to delay the checking of file's
     * data changes. By default, the reader will wait 1000 milliseconds 
     * (i.e. 1 second).
     * @return the delay between checks of file's data changes.
     */
    public long getDelay() {
        return delay;
    }

    /**
     * Sets the amount of time (in milliseconds) to delay the checking of file's
     * data changes. By default, the reader will wait 1000 milliseconds 
     * (i.e. 1 second). the delay between checks of file's data changes.
     * @param delay 
     */
    public void setDelay(long delay) {
        if( delay < 0 ) {
            throw new IllegalArgumentException("sleep cannot be less than 0.");
        }
        
        this.delay = delay;
    }

    /**
     * Gets the path to the file to be read.
     * @return the path to the file to be read.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the path to the file to be read.
     * @param filePath the path to the file to be read.
     */
    public void setFilePath(String filePath) {
        if( Strings.isBlank(filePath) ) {
            throw new IllegalArgumentException("filePath cannot be null or empty.");
        }
        
        this.filePath = filePath;
    }
    
}
