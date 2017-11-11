package library;

import org.junit.Test;

import library.BookCopy.Condition;

import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test suite for BookCopy ADT.
 */
public class BookCopyTest {

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
	
	private final BookCopy book1Copy = new BookCopy(book1);
	private final BookCopy book2Copy = new BookCopy(book2);
	private final BookCopy book3Copy = book1Copy;
	
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy = new BookCopy(book);
        assertEquals(book, copy.getBook());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetBookCopy() {
    	assertTrue("book1Copy is the copy of book1", book1Copy.getBook().equals(book1));
    }
    
    @Test
    public void testFailGetBookCopy() {
    	assertTrue("book2Copy is not the copy of book1", !book2Copy.getBook().equals(book1));
    }
    
    @Test
    public void testBookSetCondition() {
    	book1Copy.setCondition(Condition.DAMAGED);
    	assertTrue("book1Copy condition is Gamaged", Condition.DAMAGED == book1Copy.getCondition());
    }
    
    @Test
    public void testBookToString() {
    	book1Copy.setCondition(Condition.DAMAGED);
    	Condition condition = book1Copy.getCondition();
    	System.out.println(condition);
    	book1Copy.toString();
    }
    
    @Test
    public void testBookCopyEquality() {
    	assertTrue("book1Copy and book3Copy are the same", book1Copy.equals(book3Copy));
    	assertFalse("book1Copy and book2Copy are different", book1Copy.equals(book2Copy));
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
