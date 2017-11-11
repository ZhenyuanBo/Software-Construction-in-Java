package library;


import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Book ADT.
 */
public class BookTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
	private final Book book1 = new Book("Jane Eyre", Arrays.asList("Charlotte Bront", "ZYB", "SHENZ", "IZUMI"), 1920);
	private final Book book2 = new Book("Jane Eyre", Arrays.asList("Fukushima","ZYC1","YUCHEN", "WEBO"), 1920);
	private final Book book3 = new Book("Jane Eyre", Arrays.asList("Charlotte Bront", "ZYB", "SHENZ", "IZUMI"), 1920);
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals("This Test Is Just An Example", book.getTitle());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testBookGetAuthors() {
    	assertEquals("The number of author for this book is 4", 4, book1.getAuthors().size());
    }
    
    @Test
    public void testBookPubYear() {
    	assertEquals("The publication year is 1920", 1920, book1.getYear());
    }
    
    @Test
    public void testBookSame() {
    	assertTrue("Book1 and Book3 are the same book", book1.equals(book3));
    }
    
    @Test
    public void testBookDiff() {
    	assertFalse("Book1 and Book2 are different book", book1.equals(book2));
    }
    
    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
