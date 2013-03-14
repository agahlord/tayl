package org.agahlord.tayl.reader;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;
import org.agahlord.tayl.TailConfiguration;
import org.agahlord.tayl.TailHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    
/**
 * {@link org.agahlord.tayl.TailReader}'s pure Java implementation.
 * @author John Vásquez
 * @since 1.0
 */
public class JavaTailReader extends AbstractTailReader<TailConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(JavaTailReader.class);
    private static AtomicInteger counter = new AtomicInteger();
    
    private RandomAccessFile file;
    
    private long filePointer = 0;
    
    /**
     * Creates a reader using the given configuration, and delegating the data 
     * events to the given handler.
     * @param handler the data handler
     * @param configuration the reader's configuration
     * @throws FileNotFoundException if the file to be read does not exist.
     */
    public JavaTailReader(TailHandler handler, TailConfiguration configuration) throws FileNotFoundException {
        super(handler, configuration);
        
        File f = new File(configuration.getFilePath());
        
        if( !f.exists() ) {
            throw new FileNotFoundException();
        }
    }
        
    private void peekLines() throws IOException {
        try {
            file = new RandomAccessFile(configuration.getFilePath(), "r");

            int peekedLines = 0;

            long cursor;
            long startPosition;

            // Go to the end of file
            cursor = startPosition = file.length();

            // Start peeking lines
            while( cursor > 0 && peekedLines < configuration.getLines() ) {

                // Update the file cursor
                file.seek(--cursor);

                // We found an endline
                if( file.readByte() == '\n' ) {
                    peekedLines++;
                }
            }

            // If at least one line was peeked or we found the file start
            if( peekedLines > 0 || cursor == 0 ) {
                // Read the peeked lines bytes
                byte[] data = new byte[ (int)(startPosition - (cursor == 0 ? 0 : ++cursor)) ];
                file.seek(cursor);
                file.read(data);

                // Handle the lines
                handler.lineRead( new String(data, configuration.getCharset()) );
                filePointer = startPosition;
            }
        } finally {
            file.close();
        }
    }

    @Override
    protected void init() throws IOException {
        peekLines();
    }
    
    @Override
    protected void stop() throws IOException {
        file.close();
    }

    @Override
    protected Runnable buildRunner() {
        return new JavaTailRunner();
    }

    @Override
    protected String buildThreadName() {
        return "JavaTailReader-" + counter.getAndIncrement();
    }
    
    private class JavaTailRunner implements Runnable {

        @Override
        public void run() {
            try { 
                while( !stopped ) {
                    try {
                        // Whe need to open and close the file everytime, to 
                        // prevent this Java process to lock file renamings
                        file = new RandomAccessFile(configuration.getFilePath(), "r");

                        // The file probably was truncated or rotated
                        if( filePointer > file.length() ) {
                            handler.fileTruncated();
                            file.seek(0);
                            
                            filePointer = file.getFilePointer();
                        }

                        // Read line by line
                        while( filePointer < file.length() ) {
                            boolean eof = false;

                            byte data = file.readByte();
                            while( data != '\n' ) {
                                buffer.write(data);
                                try{
                                    data = file.readByte();
                                } catch( EOFException eofex ) {
                                    eof = true;
                                    break;
                                }
                            }

                            if( !eof ) {
                                buffer.write(data);
                            }

                            handler.lineRead( new String(buffer.read(),configuration.getCharset()) );
                            buffer.reset(false);
                            
                            filePointer = file.getFilePointer();
                        }

                        file.close();
                        
                        try {
                            Thread.sleep( configuration.getDelay() );
                        } catch (InterruptedException iex) { }       
                    } catch (FileNotFoundException fnfex) {
                        handler.exceptionThrown(fnfex);
                    } finally {
                        file.close();
                    }
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
