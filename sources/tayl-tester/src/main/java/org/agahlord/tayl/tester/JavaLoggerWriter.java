package org.agahlord.tayl.tester;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 *
 * @author john.vasquez
 */
public class JavaLoggerWriter implements Writer {
    private final Handler handler;
    private final Logger logger;

    public JavaLoggerWriter() throws IOException {
        handler = new java.util.logging.FileHandler("test.%g.log", 10240, 5);
        handler.setFormatter( new java.util.logging.SimpleFormatter() );
        
        logger = Logger.getLogger("tayl-test-writer");
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }
    
    public String getFileName() {
        return "test.0.log";
    }
    
    public void run() {
        try {
            Random random = new Random();
            for( ;; ) {
                logger.info("Adding lines");
                Thread.sleep( random.nextInt(100) );
            }
        } catch (InterruptedException ex) {
            logger.info("Interrupted");
        }
    }
    
}
