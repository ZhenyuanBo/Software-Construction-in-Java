package library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import library.BookCopy.Condition;

/**
 * Test suite for Library ADT.
 */
@RunWith(Parameterized.class)
public class LibraryTest {

    /*
     * Note: all the tests you write here must be runnable against any
     * Library class that follows the spec.  JUnit will automatically
     * run these tests against both SmallLibrary and BigLibrary.
     */

    /**
     * Implementation classes for the Library ADT.
     * JUnit runs this test suite once for each class name in the returned array.
     * @return array of Java class names, including their full package prefix
     */
    @Parameters(name="{0}")
    public static Object[] allImplementationClassNames() {
        return new Object[] { 
            "library.SmallLibrary", 
            "library.BigLibrary"
        }; 
    }

    /**
     * Implementation class being tested on this run of the test suite.
     * JUnit sets this variable automatically as it iterates through the array returned
     * by allImplementationClassNames.
     */
    @Parameter
    public String implementationClassName;    

    /**
     * @return a fresh instance of a Library, constructed from the implementation class specified
     * by implementationClassName.
     */
    public Library makeLibrary() {
        try {
            Class<?> cls = Class.forName(implementationClassName);
            return (Library) cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
   
    private final Book book1 = new Book("The Twisted Lit", Arrays.asList("Amy Helmes", "Kim Askew"), 1990);
    private final Book book2 = new Book("The Twisted Lit Hello", Arrays.asList("Amy ZB", "Kim EN"), 1992);
    private final Book book3 = new Book("The Unfortunate Miss Fortune", Arrays.asList("Jennifer Cruise", "Eileen Dreyer", "Anne Stuart"), 1988);
    private final Book book4 = new Book("The Virals series", Arrays.asList("Kathy Reichs", "Brendan Reichs"),1920);
    private final Book book5 = new Book("Sense and Sensibility and Sea Monsters",Arrays.asList("Jane Austen","Ben H. Winters"),1974);
    private final Book book6 = new Book("The Strain by Guillermo", Arrays.asList("Del Toro", "Chuck Hogan"), 1943);
    private BookCopy bookCopy1;
    private BookCopy bookCopy2;
    private BookCopy bookCopy3;
    private BookCopy bookCopy4;
    private BookCopy bookCopy5;
    private BookCopy bookCopy6;
    private BookCopy bookCopy7;
    
    @Test
    public void testExampleTest() {
        Library library = makeLibrary();
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals(Collections.emptySet(), library.availableCopies(book));
    }
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testLibraryMultipleOperations() {
    	
    	Library library = makeLibrary();
    	
    	//buy operation
    	bookCopy1 = library.buy(book1);
    	bookCopy2 = library.buy(book2);
    	bookCopy3 = library.buy(book1);
    	bookCopy4 = library.buy(book2);
    	bookCopy5 = library.buy(book1);
    	
    	bookCopy1.setCondition(Condition.DAMAGED);
    	bookCopy2.setCondition(Condition.GOOD);
    	
    	assertTrue("bookCopy1's title is the same book1's title", bookCopy1.getBook().getTitle().equals(book1.getTitle()));
    	assertTrue("bookCopy1's condition is DAMAGED", bookCopy1.getCondition() == Condition.DAMAGED);
    	assertTrue("bookCopy2's condition is GOOD", bookCopy2.getCondition().equals(Condition.GOOD));
    	
    	//checkout operation
    	library.checkout(bookCopy1);
    	
    	//confirm it is not available in library any more
    	assertFalse(library.isAvailable(bookCopy1));
    	
    	System.out.println("Is bookCopy1 in library? " + library.isAvailable(bookCopy1));
    	
    	//checkin operation
    	library.checkin(bookCopy1);
    	
    	//confirm it is available in library
    	assertTrue(library.isAvailable(bookCopy1));
    	
    	System.out.println("Is bookCopy1 in library? " + library.isAvailable(bookCopy1));
    	
    	assertEquals("The number of all book copies in the library is 3",3, library.allCopies(book1).size());
    	
    	System.out.println("The number of book copies is "+library.allCopies(book1).size());
    	
    	assertEquals("Numebr of available copies for book 2 is 2", 2, library.availableCopies(book2).size());
    	
    	System.out.println("Numebr of available copies for book 2 is " + library.availableCopies(book2).size());
    	
    	library.checkout(bookCopy2);
    	
    	assertEquals("Number of available copies for book 2 now is 1", 1, library.availableCopies(book2).size());
    	System.out.println("Numebr of available copies for book 2 is " + library.availableCopies(book2).size());
    	
    	//test lose
    	//library.lose(bookCopy1);
    	//assertTrue(!library.isAvailable(bookCopy1));
    }
    
    @Test 
    public void testFoundOperation() {
    	
    	Library library = new SmallLibrary();
    	
    	bookCopy7 = library.buy(book1);
    	bookCopy6 = library.buy(book6);
    	
    	System.out.println("the amount of match is: " + library.find("The Twisted Lit").size());
    }
    
    @Test
    public void testLoseOperation() {
    	
    	Library library = new BigLibrary();
    	
    	bookCopy1 = library.buy(book1);
    	bookCopy2 = library.buy(book1);
    	
    	System.out.println("BookCopy1 availability: " + library.isAvailable(bookCopy1));
    	System.out.println("BookCopy2 availability: " + library.isAvailable(bookCopy2));
    	
    	library.lose(bookCopy1);
    	
    	System.out.println("BookCopy1 availability: " + library.isAvailable(bookCopy1));
    	System.out.println("BookCopy2 availability: " + library.isAvailable(bookCopy2));
    	
    }

   
    

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
