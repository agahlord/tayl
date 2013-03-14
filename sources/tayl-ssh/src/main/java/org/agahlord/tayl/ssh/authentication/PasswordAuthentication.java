package org.agahlord.tayl.ssh.authentication;

import com.trilead.ssh2.Connection;
import java.io.IOException;

/**
 * SSH authentication using password. 
 * @see <a href="http://tools.ietf.org/html/rfc4251">The Secure Shell (SSH) Protocol Architecture - RFC 4251</a>
 * @author John Vásquez
 * @since 1.0
 */
public class PasswordAuthentication extends Authentication {

    private String password;

    /**
     * Creates an authentication with no password.
     * @param username the remote user name.
     */
    public PasswordAuthentication(String username) {
        super(username);
    }
    
    /**
     * Creates an authentication with the given password
     * @param username the remote user name.
     * @param password the remote user password.
     */
    public PasswordAuthentication(String username, String password) {
        super(username);
        this.password = password;
    }
    
    @Override
    public boolean authenticate(Connection connection) throws IOException {
        if( password == null ) { 
            return connection.authenticateWithNone(username);
        }
        
        return connection.authenticateWithPassword(username, password); 
    }
    
}
