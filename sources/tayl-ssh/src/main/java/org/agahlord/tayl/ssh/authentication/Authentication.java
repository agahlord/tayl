package org.agahlord.tayl.ssh.authentication;

import com.trilead.ssh2.Connection;
import java.io.IOException;
import org.agahlord.tayl.util.Strings;

/**
 * SSH authentication processor. Authenticates a SSH connection given a 
 * remote user name.
 * @author John Vásquez
 */
public abstract class Authentication {
    
    /**
     * User name to be authenticated.
     */
    protected String username;
    
    /**
     * Creates a new authentication for the given user.
     * @param username the remote user name.
     */
    public Authentication(String username) {
        if( Strings.isEmpty(username) ) {
            throw new IllegalArgumentException("username cannot be null nor empty.");
        }
        
        this.username = username;
    }
    
    /**
     * Authenticates the SSH connection.
     * @param connection the SSH connection.
     * @return {@code true} if the connection could be authenticated, 
     * {@code false} otherwise. 
     * @throws IOException if any IO error occurs.
     */
    public abstract boolean authenticate(Connection connection) throws IOException;
    
}
