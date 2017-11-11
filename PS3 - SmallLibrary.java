package library;


import java.util.*;

/** 
 * SmallLibrary represents a small collection of books, like a single person's home collection.
 */

public class SmallLibrary implements Library {

    // This rep is required! 
    // Do not change the types of inLibrary or checkedOut, 
    // and don't add or remove any other fields.
    // (BigLibrary is where you can create your own rep for
    // a Library implementation.)

    // rep
    private Set<BookCopy> inLibrary;
    private Set<BookCopy> checkedOut;
    
    // rep invariant:
    //    the intersection of inLibrary and checkedOut is the empty set
    //
    // abstraction function:
    //    represents the collection of books inLibrary union checkedOut,
    //      where if a book copy is in inLibrary then it is available,
    //      and if a copy is in checkedOut then it is checked out

    // TODO: safety from rep exposure argument
    
    public SmallLibrary() {
    	this.inLibrary = new HashSet<BookCopy>();
    	this.checkedOut = new HashSet<BookCopy>();
    }
    
    // assert the rep invariant
    private void checkRep() {
    	for(BookCopy bookCopy: inLibrary) {
    		assert !checkedOut.contains(bookCopy);
    	}
    }
    
    
    @Override
    public BookCopy buy(Book book) {
    	BookCopy newBookCopy = new BookCopy(book);
    	this.inLibrary.add(newBookCopy);
    	checkRep();
    	return newBookCopy;
    }
    
    @Override
    public void checkout(BookCopy copy) {
    	this.checkedOut.add(copy);
    	this.inLibrary.remove(copy);
    	checkRep();
    }
    
    @Override
    public void checkin(BookCopy copy) {
    	this.inLibrary.add(copy);
    	this.checkedOut.remove(copy);
    	checkRep();
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
    	if(this.inLibrary.contains(copy)) return true;
    	else return false;
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
    	
    	Set<BookCopy> allCopies = new HashSet<BookCopy>();
    	
    	for(BookCopy bookCopy: this.inLibrary) {
    		if(bookCopy.getBook().equals(book)) {
    			allCopies.add(bookCopy);
    		}
    	}
    	
    	for(BookCopy bookCopy: this.checkedOut) {
    		if(bookCopy.getBook().equals(book)) {
    			allCopies.add(bookCopy);
    		}
    	}
    	
    	return allCopies;

    }
    
    @Override
    public Set<BookCopy> availableCopies(Book book) {
    	
    	Set<BookCopy> availableCopies = new HashSet<BookCopy>();
    	
    	for(BookCopy bookCopy: this.inLibrary) {
    		if(bookCopy.getBook().equals(book)) {
    			availableCopies.add(bookCopy);
    		}
    	}
    	
    	return availableCopies;

    }

    @Override
    public List<Book> find(String query) {
    	
    	TreeSet<Book> bookSet = new TreeSet<Book>(new BookComp());
    	
    	for(BookCopy bookCopy:this.inLibrary) {
    		if(bookCopy.getBook().getTitle().equals(query)) {
    			bookSet.add(bookCopy.getBook());
    		}else if(bookCopy.getBook().getAuthors().contains(query)) {
    			bookSet.add(bookCopy.getBook());
    		}
    	}
    	
    	for(BookCopy bookCopy:this.checkedOut) {
    		if(bookCopy.getBook().getTitle().equals(query)) {
    			bookSet.add(bookCopy.getBook());
    		}else if(bookCopy.getBook().getAuthors().contains(query)) {
    			bookSet.add(bookCopy.getBook());
    		}
    	}
    	
    	List<Book> foundBookList = new ArrayList<Book> (bookSet);
    	
    	return foundBookList;
    }
    
    @Override
    public void lose(BookCopy copy) {
    	
    	if(this.inLibrary.contains(copy)) {
    		this.inLibrary.remove(copy);
    	}else if(this.checkedOut.contains(copy)) {
    		this.checkedOut.remove(copy);
    	}
    	
    	if(!this.inLibrary.contains(copy) && !this.checkedOut.contains(copy)) {
    		System.out.println("The following book is lost: "+ copy.toString());
    	}
    	
    	
    }

    
    
    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }
    

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}

class BookComp implements Comparator<Book>{
    @Override
    public int compare(Book book1, Book book2) {
        return book2.getYear() - book1.getYear();
    }
     
}
