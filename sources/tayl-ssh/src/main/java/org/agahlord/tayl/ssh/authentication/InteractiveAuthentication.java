package org.agahlord.tayl.ssh.authentication;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.InteractiveCallback;
import java.io.IOException;

/**
 * SSH authentication using a {@code keyboard-interactive} wrapper.
 * @see <a href="http://tools.ietf.org/html/rfc4251">The Secure Shell (SSH) Protocol Architecture - RFC 4251</a>
 * @author John Vásquez
 */
public abstract class InteractiveAuthentication extends Authentication implements InteractiveCallback {
    
    /**
     * Creates an authentication for the given user.
     * @param username the remote user name.
     */
    public InteractiveAuthentication(String username) {
        super(username);
    }
       
    @Override
    public boolean authenticate(Connection connection) throws IOException {
        return connection.authenticateWithKeyboardInteractive(username, this);
    }
    
    /**
     * Replies to the challenges made by the SSH remote host. Implementations 
     * must build the responses to the prompts according to the challenge.
     * @param name the challenge name.
     * @param instruction the challenge instruction.
     * @param numPrompts the challenge number of prompts.
     * @param prompt the prompts sent by the remote host.
     * @param echo if the prompts echoes the responses.
     * @return the prompts responses.
     * @throws Exception if any exception occurs during the reply.
     */
    public abstract String[] replyToChallenge(String name, String instruction, int numPrompts, String[] prompt, boolean[] echo) throws Exception;
    
}
