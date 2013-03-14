package org.agahlord.tayl.ssh;

import org.agahlord.tayl.TailConfiguration;
import org.agahlord.tayl.ssh.authentication.Authentication;
import org.agahlord.tayl.ssh.authentication.PasswordAuthentication;
import org.agahlord.tayl.util.Strings;

/**
 * Base configuration for {@link SshTailReader}s.
 * @author John Vásquez
 */
public class SshTailConfiguration extends TailConfiguration {

    private String host;
    private int port;
    
    private Authentication authentication;
        
    /**
     * Creates a default configuration using the given file path in the given 
     * host.
     * @param filePath the path to the file to be read.
     * @param host the host where the file resides.
     */
    public SshTailConfiguration(String filePath, String host) {
        super(filePath);
        
        if( Strings.isBlank(host) ) {
            throw new IllegalArgumentException("host cannot be null or empty.");
        }
        
        this.host = host;
        this.port = 22;
        this.authentication = new PasswordAuthentication( System.getProperty("user.name") );
    }

    /**
     * Gets the remote SSH host address.
     * @return the remote host address.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the remote SSH host address.
     * @param host the remote host address.
     */
    public void setHost(String host) {
        if( Strings.isBlank(host) ) {
            throw new IllegalArgumentException("host cannot be null or empty.");
        }
        
        this.host = host;
    }

    /**
     * Gets the remote SSH port.
     * @return the remote port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the remote SSH port.
     * @param port the remote port.
     */
    public void setPort(int port) {
        if( port < 1 || port > 65535 ) {
            throw new IllegalArgumentException("port cannot be less than 1 and greater than 65535.");
        }
        
        this.port = port;
    }

    /**
     * Gets the remote SSH authentication method.
     * @return the remote authentication method.
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * Sets the remote SSH authentication method.
     * @param authentication the remote authentication method.
     */
    public void setAuthentication(Authentication authentication) {
        if( authentication == null ) {
            throw new IllegalArgumentException("authentication cannot be null.");
        }
        
        this.authentication = authentication;
    }
    
}
