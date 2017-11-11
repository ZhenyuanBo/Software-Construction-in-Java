package library;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * Test suite for BigLibrary's stronger specs.
 */
public class BigLibraryTest {
    
    /* 
     * NOTE: use this file only for tests of BigLibrary.find()'s stronger spec.
     * Tests of all other Library operations should be in LibraryTest.java 
     */

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for BigLibrary.find() should go here.
     * Make sure you have partitions.
     */
	private final Book book1 = new Book("The Twisted Lit", Arrays.asList("Amy Helmes", "Kim Askew"), 1988);
    private final Book book2 = new Book("The Twisted Lit", Arrays.asList("Amy Helmes", "Kim Askew"), 1947);
    private final Book book3 = new Book("The Unfortunate Miss Miss", Arrays.asList("Jennifer Cruise", "Eileen Dreyer", "Anne Stuart"), 1988);
    private final Book book4 = new Book("The Twisted series Miss", Arrays.asList("Kathy Reichs", "Brendan Reichs"),1920);

    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        // this is just an example test, you should delete it
        Library library = new BigLibrary();
        assertEquals(Collections.emptyList(), library.find("This Test Is Just An Example"));
    }

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testBigLibraryFindOperation() {

    	Library library = new BigLibrary();
    	
    	BookCopy bookCopy1 = library.buy(book1);
    	BookCopy bookCopy2 = library.buy(book2);
    	BookCopy bookCopy3 = library.buy(book3);
    	BookCopy bookCopy4 = library.buy(book4);
    	
    	for(Book book : library.find("The Twisted Lit")) {
    		System.out.println(book.getTitle());
    		System.out.println(book.getYear());
    	}
    	
    	//library.lose(bookCopy1);
    	/*
    	for(Book book : library.find("The Twisted series Miss")) {
    		System.out.println(book.getTitle());
    		System.out.println(book.getYear());
    	}
    		
    	for(Book book: library.find("This Test Is Just An Example")) {
    		System.out.println(book.getTitle());
    		System.out.println(book.getYear());
    	}
    	*/
    	//assertTrue(4 == library.find("The Twisted series Miss").size());
    }
    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
