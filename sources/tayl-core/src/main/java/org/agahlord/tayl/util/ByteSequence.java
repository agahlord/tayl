package org.agahlord.tayl.util;

import java.util.Arrays;

/**
 * Holds a dynamic sequence of bytes, as a buffer, with resizing capabilities.
 * @author John Vásquez
 * @since 1.0
 */
public class ByteSequence {
    
    private int length;
    private int increment;
    private int initialCapacity;
    private byte[] bytes;
    
    /**
     * Creates a {@code ByteSequence} with a 1024 initial capacity and 
     * increment.
     */
    public ByteSequence() { 
        this(1024);
    }
    
    /**
     * Creates a {@code ByteSequence} with the given initial capacity and 1024 
     * bytes increment.
     * @param initialCapacity the initial byte capacity.
     */
    public ByteSequence(int initialCapacity) {
        this(initialCapacity, 1024);
    }
    
    /**
     * Creates a {@code ByteSequence} with the given initial capacity and the 
     * given increment.
     * @param initialCapacity the initial byte capacity.
     * @param increment the initial byte increment.
     */
    public ByteSequence(int initialCapacity, int increment) {
        if( initialCapacity < 1 ) {
            throw new IllegalArgumentException("initialCapacity must be greater than 0.");
        }
        
        if( increment < 1 ) {
            throw new IllegalArgumentException("increment must be greater than 0.");
        }
        
        this.length = 0;
        this.increment = increment;
        this.initialCapacity = initialCapacity;
        
        this.bytes = new byte[initialCapacity];
    }
    
    private void resize(int times) {
        bytes = Arrays.copyOf(bytes, bytes.length + (increment * times) );
    }
    
    /**
     * Writes a byte at the end of the sequence.
     * @param b the byte to be written.
     */
    public void write(byte b) {
        if(length == bytes.length) {
            resize(1);
        }
        
        bytes[length++] = b;
    }
    
    /**
     * Writes a byte array at the end of the sequence.
     * @param ba the byte array to be written.
     */
    public void write(byte[] ba) {
        write(ba, 0, ba.length);
    }
    
    /**
     * Writes {@code length} bytes from the specified byte array starting at 
     * offset {@code offset} at then end of this sequence.
     * @param ba the given byte array.
     * @param offset the start offset in the data.
     * @param length he number of bytes to write.
     */
    public void write(byte[] ba, int offset, int length) {
        // If the new size is bigger than our current byte array, resize it
        if( length + length > bytes.length ) {
            // The extra bytes added to our byte array
            int difference = (length + length) - bytes.length;
            
            // resize in a sufficient increment
            resize( (difference / increment) + 1 );
        }
        
        for( int i = offset ; i < length ; i++ ) {
            bytes[length++] = ba[i];
        }
    }
    
    /**
     * Read this sequence, as a byte array.
     * @return the sequence as a byte array.
     */
    public byte[] read() {
        return Arrays.copyOf(bytes, length);
    }
    
    /**
     * Resets this sequence to 0.
     * @param free if the extra increments done before must be freed, 
     * reseting this sequence to the initial capacity.
     */
    public void reset(boolean free) {
        length = 0;
        
        if( free ) {
            bytes = new byte[initialCapacity];
        }
    }
    
}
