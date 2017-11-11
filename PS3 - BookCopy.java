package library;


/**
 * BookCopy is a mutable type representing a particular copy of a book that is held in a library's
 * collection.
 */
public class BookCopy {

	private final Book bookCopy;
    Condition bookCondition;
    
    // Rep invariant:
    // 	bookCopy contains all the information that a book has (title, author list, publication year) 
    //  and book condition
    // Abstraction Function:
    // 	creates a copy of a book
    // Safety from rep exposure:
    // 	bookCopy is immutable;
    
    
    public static enum Condition {
        GOOD, DAMAGED
    };
    
    /**
     * Make a new BookCopy, initially in good condition.
     * @param book the Book of which this is a copy
     */
    public BookCopy(Book book) {
    	this.bookCopy = book;
    	this.bookCondition = Condition.GOOD;
    	checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
    	assert this.bookCopy != null;
    	assert this.bookCondition != null;
    }
    
    /**
     * @return the Book of which this is a copy
     */
    public Book getBook() {
    	return this.bookCopy;
    }
    
    /**
     * @return the condition of this book copy
     */
    public Condition getCondition() {
        return this.bookCondition;
    }

    /**
     * Set the condition of a book copy.  This typically happens when a book copy is returned and a librarian inspects it.
     * @param condition the latest condition of the book copy
     */
    public void setCondition(Condition condition) {
    	this.bookCondition = condition;
    }
    
    /**
     * @return human-readable representation of this book that includes book.toString()
     *    and the words "good" or "damaged" depending on its condition
     */
    public String toString() {
        String bookCopyInfo = this.bookCopy.toString() + " The book condition is: " + this.bookCondition.toString().toLowerCase();
        System.out.println(bookCopyInfo);
        return bookCopyInfo;
    }
    
    /*
     * The BookCopy is a mutable object, thus the equals() and hashCode() won't be overridden
     */
    //@Override
    /*public boolean equals(Object that) {
    	if(!(that instanceof BookCopy)) return false;
    	
    	BookCopy thatCopy = (BookCopy) that;
    	
    	return this.getBook() == thatCopy.getBook();
    }
    */

    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
