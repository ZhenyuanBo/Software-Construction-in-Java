package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    private final String title;
    private final List<String> authors;
    private final int year;
    
    // Rep invariant:
    // 	title of a book contains at least one non-space character
    //  author list contains at least one name and each name must contain at least one non-space character
    //  year > 0
    // Abstraction Function:
    // 	represents a book identified by its title, author list, and publication year
    // Safety from rep exposure:
    // 	All fields are private;
    // 	title is String and year is int, so are guaranteed immutable;
    // 	author list is a mutable object, so getAuthors() make defensive copies to avoid 
    //		sharing the rep's author list object with clients
    
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String title, List<String> authors, int year) {
        this.title = title;
        this.authors = new ArrayList<String>(authors);
        this.year = year;
        checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
       assert title != null;
       assert title.trim().length() > 0;
       assert authors.size() > 0;
       for(String str: authors) {
    	   assert str!=null;
    	   assert str.trim().length() > 0;
       }
       assert year > 0;
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
    	/*
    	 * Instead of returning a direct reference, a defensive copy is made to avoid
    	 * aliasing as the list object is mutable; the content inside this list has to be 
    	 * protected without introducing any changes from outside
    	 */
    	checkRep();
       return new ArrayList<String>(this.authors);
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
        return this.year;
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
    	
        StringBuilder authorList = new StringBuilder();
        for(String author: this.authors) {
        	authorList.append(author + ",");
        }
        
        int authorListLen = authorList.length();
        authorList.deleteCharAt(authorListLen-1);
        String authorListStr = authorList.toString();
        
        String bookTitleInfo = "The title of this book is: "+this.title;
        String bookAuthorInfo = " The author list is: "+authorListStr;
        String pubYearInfo = " The publication year is: "+this.year;
        String bookInfo = bookTitleInfo + bookAuthorInfo + pubYearInfo;
        
        return bookInfo;
    }


    @Override
    public boolean equals(Object that) {
    	
    	if(!(that instanceof Book)) return false;
    	
    	final Book book = (Book) that;
    	
    	if(this.title == book.getTitle() && 
    			this.year == book.getYear() && 
    			this.authors.size() == book.getAuthors().size() && 
    			authorListEquals(book)) {
    		return true;
    	}
    	
    	return false;
    
    }
    
    private boolean authorListEquals(Book book) {
    	
    	int i=0;
    	
    	for(String author: this.authors) {
    		if(!book.getAuthors().get(i).equals(author)) return false;
    		i++;
    	}
    	return true;
    }

     
    @Override
    public int hashCode() {
    	
    	int result = 1;
    	int hashCode = this.getYear() + this.getTitle().hashCode() + this.getAuthors().hashCode();
    	
    	result = 37*result + hashCode;
    	
    	return result;
    }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
