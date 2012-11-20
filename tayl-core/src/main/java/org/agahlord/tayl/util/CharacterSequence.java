package org.agahlord.tayl.util;

import java.util.Arrays;

/**
 * Holds a dynamic sequence of characters, as a buffer, with resizing capabilities.
 * @author John Vásquez
 */
public class CharacterSequence {
    
    private int size;
    private int increment;
    private int initialCapacity;
    private char[] chars;
    
    /**
     * Creates a {@code CharacterSequence} with a 512 initial capacity and 
     * increment.
     */
    public CharacterSequence() { 
        this(512);
    }
    
    /**
     * Creates a {@code CharacterSequence} with the given initial capacity and 
     * 512 bytes increment.
     * @param initialCapacity the initial character capacity.
     */
    public CharacterSequence(int initialCapacity) {
        this(initialCapacity, 512);
    }
    
    /**
     * Creates a {@code CharacterSequence} with the given initial capacity and 
     * the given increment.
     * @param initialCapacity the initial character capacity.
     * @param increment the initial character increment.
     */
    public CharacterSequence(int initialCapacity, int increment) {
        if( initialCapacity < 1 ) {
            throw new IllegalArgumentException("initialCapacity must be greater than 0.");
        }
        
        if( increment < 1 ) {
            throw new IllegalArgumentException("increment must be greater than 0.");
        }
        
        this.size = 0;
        this.increment = increment;
        this.initialCapacity = initialCapacity;
        
        this.chars = new char[initialCapacity];
    }
    
    private void resize(int times) {
        chars = Arrays.copyOf(chars, chars.length + (increment * times) );
    }
    
    /**
     * Writes a character at the end of the sequence.
     * @param c the character to be written.
     */
    public void write(char c) {
        if(size == chars.length) {
            resize(1);
        }
        
        chars[size++] = c;
    }
    
    /**
     * Writes a character array at the end of the sequence.
     * @param ca the character array to be written.
     */
    public void write(char[] ca) {
        write(ca, 0, ca.length);
    }
    
    /**
     * Writes {@code length} characters from the specified character array 
     * starting at offset {@code offset} at then end of this sequence.
     * @param ca the given character array.
     * @param offset the start offset in the data.
     * @param length he number of characters to write.
     */
    public void write(char[] ca, int offset, int length) {
        // If the new size is bigger than our current byte array, resize it
        if( size + length > chars.length ) {
            // The extra bytes added to our byte array
            int difference = (size + length) - chars.length;
            
            // resize in a sufficient increment
            resize( (difference / increment) + 1 );
        }
        
        for( int i = offset ; i < length ; i++ ) {
            chars[size++] = ca[i];
        }
    }
    
    /**
     * Read this sequence, as a character array.
     * @return the sequence as a character array.
     */
    public char[] read() {
        return Arrays.copyOf(chars, size);
    }
    
    /**
     * Resets this sequence to 0.
     * @param free if the extra increments done before must be freed, 
     * reseting this sequence to the initial capacity.
     */
    public void reset(boolean free) {
        size = 0;
        
        if( free ) {
            chars = new char[initialCapacity];
        }
    }
    
}
