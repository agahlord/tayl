package org.agahlord.tayl.tester;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author john.vasquez
 */
public class LogbackWriter implements Writer {
    private Logger logger = LoggerFactory.getLogger(Writer.class);
    
    public String getFileName() {
        return "test.log";
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
