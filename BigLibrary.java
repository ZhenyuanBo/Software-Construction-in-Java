package library;

import java.util.*;


/**
 * BigLibrary represents a large collection of books that might be held by a city or
 * university library system -- millions of books.
 * 
 * In particular, every operation needs to run faster than linear time (as a function of the number of books
 * in the library).
 */

public class BigLibrary implements Library {
    

    private final Map<Book,Set<BookCopy>> inLibrary;
    private final Map<Book,Set<BookCopy>> checkedOut;
    private final Map<String,Set<Book>> bookFindEngine;
    
    public BigLibrary() {
        this.inLibrary = new HashMap<Book,Set<BookCopy>>();
        this.checkedOut = new HashMap<Book,Set<BookCopy>>();
        this.bookFindEngine = new HashMap<String,Set<Book>>();
    }
    
    // assert the rep invariant
    private void checkRep() {
    	for(Book book : inLibrary.keySet()) {
    		for(BookCopy bookCopy : inLibrary.get(book)) {
    			if(checkedOut.keySet().contains(book)) {
    				assert !checkedOut.get(book).contains(bookCopy);
    			}
    		}
    	}
    }

    @Override
    public BookCopy buy(Book book) {
    	
    	BookCopy newBookCopy = new BookCopy(book);
    	Set<BookCopy> newBookCopySet = new HashSet<BookCopy>();
    	
    	newBookCopySet.add(newBookCopy);
    	
    	String bookTitle = book.getTitle();
    	String[] bookTitleWords = bookTitle.split("\\s+");
    	List<String> authorList = book.getAuthors();
    	
    	//break each title into words and create a map using those words as key values
    	for(String str: bookTitleWords) {
    		if(!bookFindEngine.containsKey(str)) {
    			Set<Book> bookSetTitle = new HashSet<Book>();
    			bookSetTitle.add(book);
    			bookFindEngine.put(str, bookSetTitle);
    		}else {
    			bookFindEngine.get(str).add(book);
    		}
    	}
    	
    	//break the author list into multiple string values
    	for(String str:authorList) {
    		String[] authorNameSplit = str.split("\\s+");
    		for(String authorName:authorNameSplit) {
    			if(!bookFindEngine.containsKey(authorName)) {
    				Set<Book> bookSetAuthor = new HashSet<Book>();
    				bookSetAuthor.add(book);
    				bookFindEngine.put(authorName, bookSetAuthor);
    			}else {
    				bookFindEngine.get(authorName).add(book);
    			}
    		}
    	}
    	
    	if(this.inLibrary.containsKey(book)) {
    		this.inLibrary.get(book).add(newBookCopy);
    	}else {
    		this.inLibrary.put(book, newBookCopySet);
    	}
    	
    	checkRep();
    	
    	return newBookCopy;
    }
    
    @Override
    public void checkout(BookCopy copy) {
    	Book bookCheckOut = copy.getBook();
    	
    	if(!checkedOut.containsKey(bookCheckOut)) {
    		Set<BookCopy> newCheckedBookCopySet = new HashSet<BookCopy>();
    		newCheckedBookCopySet.add(copy);
    		checkedOut.put(bookCheckOut, newCheckedBookCopySet);
    	}else {
    		checkedOut.get(bookCheckOut).add(copy);
    	}
    	
    	//after checkout, we need to remove from the inLibrary
    	this.inLibrary.get(bookCheckOut).remove(copy);
    	
    	checkRep();
    }
    
    @Override
    public void checkin(BookCopy copy) {
        Book bookCheckIn = copy.getBook();
        
        //check in means the there is already a record in the library database
        this.inLibrary.get(bookCheckIn).add(copy);
        
        //after check in, we need to remove the record from the checkedOut
        this.checkedOut.get(bookCheckIn).remove(copy);
        
        checkRep();
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
    	Set<BookCopy> allBookCopiesSet = new HashSet<BookCopy>();
    	
    	if(this.inLibrary.get(book) != null) {
    		allBookCopiesSet.addAll(this.inLibrary.get(book));
    	}
    	
    	if(this.checkedOut.get(book) != null) {
    		allBookCopiesSet.addAll(this.checkedOut.get(book));
    	}
    	
    	return allBookCopiesSet;
    }

    @Override
    public Set<BookCopy> availableCopies(Book book) {
    	Set<BookCopy> availableBookCopiesSet = new HashSet<BookCopy>();
    	
    	if(this.inLibrary.get(book) != null) {
    		availableBookCopiesSet.addAll(this.inLibrary.get(book));
    	}
    	
    	return availableBookCopiesSet;
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
        Book bookToSearch = copy.getBook();
        if(this.inLibrary.containsKey(bookToSearch)) {
        	if(this.inLibrary.get(bookToSearch).contains(copy)) {
        		return true;
        	}
        }
        return false;
    }
    
    @Override
    public List<Book> find(String query) {
    	
    	List<Book> finalFoundBookList = new ArrayList<Book>();
    	//make sure the amount of search is sorted in the descending order
    	Map<Integer, List<Book>> foundBookMap = new TreeMap<Integer,List<Book>>(Collections.reverseOrder());
    	
    	//split the query first into words
    	String[] queryWordSplit = query.split("\\s+");
    	
    	Map<Book,Integer> bookSearchRankingSystem = new HashMap<Book,Integer>();
    	
    	for(String wordSearch:queryWordSplit) {
    		Set<Book> foundBookSet = bookFindEngine.get(wordSearch);
    		if(foundBookSet != null) {
    			for(Book book:foundBookSet) {
    				if(!bookSearchRankingSystem.containsKey(book)) {
    					bookSearchRankingSystem.put(book, 1);
    				}else {
    					bookSearchRankingSystem.put(book, bookSearchRankingSystem.get(book)+1);
    				}
    			}
    		}
    	}
    	
    	/* sort the books in terms of the descending amount of search
    	 * map the number of match to a list of corresponding books
    	 * Example:
    	 * 4 - ["The Twisted Lit", "The Twisted Lit", "The Twisted Lit"]
    	 * order the books within the list according to the publication year
    	 */
    	while(!bookSearchRankingSystem.isEmpty()) {
    		int currentMax = Collections.max(bookSearchRankingSystem.values());
    		System.out.println("Max value: " + currentMax);
    		for(Book book: bookSearchRankingSystem.keySet()) {
    			if(bookSearchRankingSystem.get(book).equals(currentMax)) {
    				if(foundBookMap.get(currentMax) == null) {
    					List<Book> foundBookList = new ArrayList<Book>();
    					foundBookList.add(book);
    					System.out.println(book.getTitle());
    					foundBookMap.put(currentMax, foundBookList);
    				}else {
    					foundBookMap.get(currentMax).add(book);
    					Collections.sort(foundBookMap.get(currentMax), new BookComp());
    				}
    				bookSearchRankingSystem.remove(book);
    				break;
    			}
    		}
    	}
    	
    	
    	/*
    	 * put all the lists together to form the final list
    	 */
    	for(Integer i : foundBookMap.keySet()) {
    		finalFoundBookList.addAll(foundBookMap.get(i));
    	}
    	
    	return finalFoundBookList;
    }
    
    @Override
    public void lose(BookCopy copy) {
    	
    	Book book = copy.getBook();
    	
    	if(this.isAvailable(copy)) {
    		this.inLibrary.get(book).remove(copy);
    	}else {
    		this.checkedOut.get(book).remove(copy);
    	}
    	
    	//if we lose the last copy of the book, we need to remove the book index from searching system
    	if(this.inLibrary.get(book).size() == 0) {
    		//remove the book from bookFindEngine
    		String bookTitle = book.getTitle();
    		String[] bookTitleArr = bookTitle.split("\\s+");
    		
    		for(String str: bookTitleArr) {
    			bookFindEngine.get(str).remove(book);
    		}
    		
        	for(String str:book.getAuthors()) {
        		String[] authorNameSplit = str.split("\\s+");
        		for(String authorName:authorNameSplit) {
        			bookFindEngine.get(authorName).remove(book);
        		}
        	}
    		
    	}
    	
    	checkRep();
    	
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


