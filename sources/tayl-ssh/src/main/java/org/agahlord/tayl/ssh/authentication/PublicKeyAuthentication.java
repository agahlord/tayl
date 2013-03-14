package org.agahlord.tayl.ssh.authentication;

import com.trilead.ssh2.Connection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.agahlord.tayl.util.CharacterSequence;

/**
 * SSH authentication using a public key challenge.
 * @see <a href="http://tools.ietf.org/html/rfc4251">The Secure Shell (SSH) Protocol Architecture - RFC 4251</a>
 * @author John Vásquez
 * @since 1.0
 */
public class PublicKeyAuthentication extends Authentication {

    private char[] privateKey;
    private String passphrase;
    
    /**
     * Creates an authentication using the default {@code ~/.ssh/id_rsa} 
     * private key, with no key pass phrase. 
     * @param username the remote user name.
     * @throws FileNotFoundException if the {@code ~/.ssh/id_rsa} file could not be found.
     * @throws IOException if any IO error occurs.
     */
    public PublicKeyAuthentication(String username) throws FileNotFoundException, IOException {
        this(username, null, false);
    }
    
    /**
     * Creates an authentication using the default {@code ~/.ssh/id_rsa} or 
     * {@code ~/.ssh/id_dsa} private key, with no key pass phrase. 
     * @param username the remote user name.
     * @param dsa {@code true} if the key is the default DSA key, or {@code false} if it is the default RSA key.
     * @throws FileNotFoundException if the {@code ~/.ssh/id_rsa} file or the {@code ~/.ssh/id_dsa} file could not be found.
     * @throws IOException if any IO error occurs.
     */
    public PublicKeyAuthentication(String username, boolean dsa) throws FileNotFoundException, IOException {
        this(username, null, dsa);
    }
    
    /**
     * Creates an authentication using the PEM encoded private key 
     * {@code privateKey}, with no key pass phrase. 
     * @param username the remote user name.
     * @param privateKey the PEM encoded private key.
     */
    public PublicKeyAuthentication(String username, char[] privateKey) {
        super(username);
        
        this.privateKey = privateKey;
    }
    
    /**
     * Creates an authentication using the PEM encoded private key file
     * {@code privateKey}, with no key pass phrase. 
     * @param username the remote user name.
     * @param privateKey the PEM encoded private key file.
     * @throws FileNotFoundException if the {@code privateKey} file could not be found.
     * @throws IOException if any IO error occurs.
     */
    public PublicKeyAuthentication(String username, File privateKey) throws FileNotFoundException, IOException {
        this(username, privateKey, null);
    }
    
    /**
     * Creates an authentication using the default {@code ~/.ssh/id_rsa} 
     * private key, with the given key pass phrase. 
     * @param username the remote user name.
     * @param passphrase the key pass phrase.
     * @throws FileNotFoundException if the {@code ~/.ssh/id_rsa} file could not be found.
     * @throws IOException if any IO error occurs.
     */
    public PublicKeyAuthentication(String username, String passphrase) throws FileNotFoundException, IOException {
        this(username, passphrase, false);
    }
    
    /**
     * Creates an authentication using the default {@code ~/.ssh/id_rsa} or 
     * {@code ~/.ssh/id_dsa} private key, with the given key pass phrase. 
     * @param username the remote user name.
     * @param passphrase the key pass phrase.
     * @param dsa {@code true} if the key is the default DSA key, or {@code false} if it is the default RSA key.
     * @throws FileNotFoundException if the {@code ~/.ssh/id_rsa} file or the {@code ~/.ssh/id_dsa} file could not be found.
     * @throws IOException if any IO error occurs.
     */
    public PublicKeyAuthentication(String username, String passphrase, boolean dsa) throws FileNotFoundException, IOException {
        this(username, new File(System.getProperty("user.home") + "/.ssh/id_" + (dsa ? "dsa" : "rsa")), passphrase);
    }
    
    /**
     * Creates an authentication using the PEM encoded private key 
     * {@code privateKey}, with the given key pass phrase.
     * @param username the remote user name.
     * @param privateKey the PEM encoded private key.
     * @param passphrase the private key pass phrase.
     */
    public PublicKeyAuthentication(String username, char[] privateKey, String passphrase) {
        super(username);
        
        this.privateKey = privateKey;
        this.passphrase = passphrase;
    }
    
    /**
     * Creates an authentication using the PEM encoded private key file 
     * {@code privateKey}, with the given key pass phrase.
     * @param username the remote user name.
     * @param privateKey the PEM encoded private key file.
     * @param passphrase the private key pass phrase.
     * @throws FileNotFoundException if the {@code privateKey} file could not be found.
     * @throws IOException if any IO error occurs.
     */
    public PublicKeyAuthentication(String username, File privateKey, String passphrase) throws FileNotFoundException, IOException {
        super(username);
        
        if( !privateKey.exists() ) {
            throw new IllegalArgumentException("private key file does not exists.");
        }
        
        int readChars;
        char[] buffer = new char[512];
        BufferedReader reader = new BufferedReader( new FileReader(privateKey) );
        
        // 1766 is the char count of a 2048 RSA Private key size
        CharacterSequence chars = new CharacterSequence(1766, 100);
        
        while( (readChars = reader.read(buffer)) != -1 ) {
            chars.write(buffer, 0, readChars);
        }
        
        this.privateKey = chars.read();
        this.passphrase = passphrase;
    }
    
    @Override
    public boolean authenticate(Connection connection) throws IOException {
        return connection.authenticateWithPublicKey(username, privateKey, passphrase);
    }
    
}
