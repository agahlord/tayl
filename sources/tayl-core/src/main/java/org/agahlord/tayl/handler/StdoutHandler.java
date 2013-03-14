package org.agahlord.tayl.handler;

import org.agahlord.tayl.TailHandler;

/**
 * Basic {@link TailHandler} implementation which writes the events using the
 * standard system outputs ({@code System.out} and {@code System.err}).
 * @author John Vásquez
 * @since 1.0
 */
public class StdoutHandler implements TailHandler {

    public void lineRead(String line) {
        System.out.print(line);
    }

    public void fileTruncated() {
        System.out.println("-- FILE TRUNCATED --");
    }

    public void exceptionThrown(Exception exception) {
        exception.printStackTrace();
    }
    
}
