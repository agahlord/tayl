package org.agahlord.tayl;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File reader which listens for the data appended to a given file, reading it 
 * line by line, as the Unix {@code tail} utility.
 * @author John Vásquez
 */
public abstract class TailReader<T extends TailConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(TailReader.class);
    
    /**
     * Reader's configuration.
     */
    protected T configuration;
    
    /**
     * Reader's data handler.
     */
    protected TailHandler handler;
    
    /**
     * Reader's control flag.
     */
    protected volatile boolean stopped; 
    
    /**
     * Creates a reader using the given configuration, and delegating the data 
     * events to the given handler.
     * @param handler the data handler
     * @param configuration the reader's configuration
     * @throws FileNotFoundException if the file to be read does not exist.
     */
    public TailReader(TailHandler handler, T configuration) throws FileNotFoundException {
        this.handler = handler;
        this.configuration = configuration;
        this.stopped = true;
        
        // Stop the reader when the JVM shuts down
        Runtime.getRuntime().addShutdownHook( new ShutdownHandler(this) );
    }
    
    /**
     * Tells if the reader is active and reading. 
     * @return if the reader is active and reading
     */
    public boolean isReading() {
        return !stopped;
    }
    
    /**
     * Starts the file's tail reading. Implementations must ensure this method 
     * is thread-safe, and behaves accordingly to the given configuration.
     * @see TailConfiguration#isSpanThread() 
     * @throws IOException if any IO problem occurs.
     */
    public abstract void startReading() throws IOException;
    
    /**
     * Stops the file's tail reading. Implementations must ensure this method 
     * is thread-safe, and behaves accordingly to the given configuration.
     * @see TailConfiguration#isSpanThread() 
     * @throws IOException if any IO problem occurs.
     */
    public abstract void stopReading() throws IOException; 
    
    @Override
    public String toString() {
        return "TailReader{" + (configuration == null ? "[N/A]" : configuration.getFilePath()) +'}';
    }
    
    /**
     * Handles the JVM shutdown event to properly stop the associated {@link TailReader}.
     */
    private class ShutdownHandler extends Thread {
        
        private TailReader reader;

        ShutdownHandler(TailReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            try {
                reader.stopReading();
            } catch(Throwable t) {
                logger.error("Could not stop reader at shutdown: " + reader, t);
            }
        }
    }
        
}
