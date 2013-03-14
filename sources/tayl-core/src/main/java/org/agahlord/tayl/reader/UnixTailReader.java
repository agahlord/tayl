package org.agahlord.tayl.reader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.agahlord.tayl.TailConfiguration;
import org.agahlord.tayl.TailHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link org.agahlord.tayl.TailReader}'s implementation that relies on the 
 * actual Unix {@code tail} utility, executing it as a subprocess.
 * @author John Vásquez
 */
public class UnixTailReader extends AbstractTailReader<TailConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(UnixTailReader.class);
    private static AtomicInteger counter = new AtomicInteger();
        
    private File file;
    private Process process;
    private BufferedInputStream input;
    
    /**
     * Creates a reader using the given configuration, and delegating the data 
     * events to the given handler.
     * @param handler the data handler
     * @param configuration the reader's configuration
     * @throws FileNotFoundException if the file to be read does not exist.
     */
    public UnixTailReader(TailHandler handler, TailConfiguration configuration) throws FileNotFoundException {
        super(handler, configuration);
        this.file = new File(configuration.getFilePath());
        
        if( !file.exists() ) {
            throw new FileNotFoundException();
        }
    }
    
    @Override
    protected void init() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void stop() throws IOException {
        try {
            if( input != null ) {
                input.close();
            }
        } finally {
            if( process != null ) {
                process.destroy();
            }
        }
    }

    @Override
    protected Runnable buildRunner() {
        return new UnixTailRunner();
    }

    @Override
    protected String buildThreadName() {
        return "UnixTailReader-" + counter.getAndIncrement();
    }

    private class UnixTailRunner implements Runnable {
        public void run() {
            try {
                StringBuilder command = new StringBuilder("tail");
                command.append(" -n ").append(configuration.getLines());
                command.append(" -F ").append(file.getAbsolutePath());

                process = Runtime.getRuntime().exec( command.toString() );
                input = new BufferedInputStream( process.getInputStream() );

                while( !stopped ) {
                    byte data;
                    while( (data = (byte) input.read()) != -1 ) {
                        buffer.write(data);

                        if ( data == '\n' ) {
                            handler.lineRead( new String(buffer.read(),configuration.getCharset()) );
                            buffer.reset(false);
                        }        
                    }

                    try {
                        Thread.sleep( configuration.getDelay() );
                    } catch (InterruptedException ex) { }
                }
            } catch (IOException ioex) {
                try {
                    stopReading();
                } catch (IOException ex) {
                    logger.error("Could not stop tail reader " + this, ex);
                } finally {
                    handler.exceptionThrown(ioex);
                }
            } finally {
                try {
                    if( !stopped ) {
                        stopReading();
                    }
                } catch (IOException ex) {
                    logger.error("Could not stop tail reader " + this, ex);
                }
            }
        }
    }
    
}
