package org.agahlord.tayl.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.agahlord.tayl.TailConfiguration;
import org.agahlord.tayl.TailHandler;
import org.agahlord.tayl.TailReader;
import org.agahlord.tayl.util.ByteSequence;

/**
 * Abstract implementation of a {@link TailReader}, which adds thread-safety to 
 * the reading methods, and span threads accordingly to the given configuration. 
 * @author John Vásquez
 * @since 1.0
 */
public abstract class AbstractTailReader<T extends TailConfiguration> extends TailReader<T> {

    /**
     * Buffer to hold the read line data prior to apply the character set 
     * encoding.
     */
    protected ByteSequence buffer;
    
    /**
     * Reading thread reference, when thread spanning is allowed.
     */
    protected Thread readingThread;
    
    /**
     * Creates a reader using the given configuration, and delegating the data 
     * events to the given handler.
     * @param handler the data handler.
     * @param configuration the reader's configuration.
     * @throws FileNotFoundException if the file to be read does not exist.
     */
    public AbstractTailReader(TailHandler handler, T configuration) throws FileNotFoundException {
        super(handler, configuration);
        
        buffer = new ByteSequence();
    }
    
    @Override
    public void startReading() throws IOException {
        synchronized(this) {
            // Start reading only if the reader is stopped
            if( stopped ) {
                stopped = false;

                init();
                
                if( configuration.isSpanThread() ) {
                    readingThread = new Thread( buildRunner(), buildThreadName() );
                    readingThread.setDaemon( true );
                    readingThread.setPriority( Thread.MIN_PRIORITY );
                    readingThread.start();
                } else {
                    buildRunner().run();
                }
            }
        }
    }
    
    @Override
    public void stopReading() throws IOException {
        synchronized(this) {
            stopped = true;
            stop();
        }
    }
    
    /**
     * Allows the subclasses to initialize resources before start reading.
     * @throws IOException if any IO problem occurs.
     */
    protected abstract void init() throws IOException;
    
    /**
     * Allows the subclasses to stop, destroy and clean up resources after the 
     * reading has been stopped.
     * @throws IOException if any IO problem occurs.
     */
    protected abstract void stop() throws IOException;
    
    /**
     * Allows the subclasses to specify the reading thread must behave.
     * @return the reading thread implementation. 
     */
    protected abstract Runnable buildRunner();
    
    /**
     * Allows the subclasses to specify the reading thread name.
     * @return the reading thread name.
     */
    protected abstract String buildThreadName();
    
}
