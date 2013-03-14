package org.agahlord.tayl.ssh.reader;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import org.agahlord.tayl.TailHandler;
import org.agahlord.tayl.reader.AbstractTailReader;
import org.agahlord.tayl.ssh.SshTailConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link org.agahlord.tayl.TailReader}'s SSH implementation, which allows to 
 * tail remote files.
 * @author John Vásquez
 * @since 1.0
 */
public class SshTailReader extends AbstractTailReader<SshTailConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(SshTailReader.class);
    private static AtomicInteger counter = new AtomicInteger();
    
    private Connection connection;
    private Session session;
    private BufferedInputStream input;
    
    /**
     * Creates a reader using the given configuration, and delegating the data 
     * events to the given handler.
     * @param handler the data handler.
     * @param configuration the reader's configuration.
     * @throws FileNotFoundException if the file to be read does not exist.
     */
    public SshTailReader(TailHandler handler, SshTailConfiguration configuration) throws FileNotFoundException {
        super(handler, configuration);
    }

    @Override
    protected void init() throws IOException {
        
    }

    @Override
    protected void stop() throws IOException {
        try {
            if( input != null ) {
                input.close();
            }
        } finally {
            try {
                if( session != null ) {
                    session.close();
                }
            } finally {
                if( connection != null ) {
                    connection.close();
                }
            }
        }
    }
    
    @Override
    protected Runnable buildRunner() {
        return new SshTailRunner();
    }

    @Override
    protected String buildThreadName() {
        return "SshTailReader-" + counter.getAndIncrement();
    }

    private class SshTailRunner implements Runnable {
        public void run() {
            try {
                connection = new Connection( configuration.getHost(), configuration.getPort() );
                connection.connect(); 

                if( !configuration.getAuthentication().authenticate(connection) ) {
                    throw new IOException("Could not authenticate on remote server.");
                }

                session = connection.openSession();
                session.startShell();

                StringBuilder command = new StringBuilder("tail");
                command.append(" -n ").append(configuration.getLines());
                command.append(" -F ").append(configuration.getFilePath());

                PrintWriter writer = new PrintWriter( session.getStdin() );
                writer.println(command);
                writer.flush();

                input = new BufferedInputStream( session.getStdout() );

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
