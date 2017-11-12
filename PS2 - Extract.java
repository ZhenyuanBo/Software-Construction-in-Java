package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
	public static Instant getStart(List<Tweet> tweets) {
		
		if(tweets.isEmpty()) {
			return Instant.now();
		}
			
		Instant starttime = Instant.MAX;
		for(Tweet tweet:tweets) {
			if(tweet.getTimestamp().isBefore(starttime)) {
					starttime = tweet.getTimestamp();
			}
		}

		return starttime;
	}
	
	public static Instant getEnd(List<Tweet> tweets) {
		
		if(tweets.isEmpty()) {
			return Instant.now();
		}
		
		Instant endtime = Instant.MIN;
		for(Tweet tweet:tweets) {
			if(tweet.getTimestamp().isAfter(endtime)) {
					endtime = tweet.getTimestamp();
			}
		}
		
		return endtime;
	}

    public static Timespan getTimespan(List<Tweet> tweets) {
    	
    	if(tweets.isEmpty()) {
    		return new Timespan(Instant.MAX,Instant.MAX);
    	}
    	
    	Instant start = getStart(tweets);
    	Instant end = getEnd(tweets);

    	return new Timespan(start,end);

    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	
    	if(tweets.isEmpty()) {
    		return new HashSet<String>();
    	}
    	
    	Set<String> userNameSet = new HashSet<String>();
    	
    	//iterate through the tweets list and extract mentioned users from each tweet
    	for(Tweet tweet: tweets) {
    		
    		String text = tweet.getText();
    		String[] textStringArr = text.split("\\s+");
    		
    		for(String str:textStringArr) {
    			String userName = "";
    			//'@' or '#' (e.g. #mit)
    			if(str.indexOf('@')!=-1 || str.indexOf('#')!=-1) {
    				/*
    				 * username-mention at the beginning of the tweet and is not followed by valid tweet character
    				 */
    				if(str.length()>1 && (str.indexOf('@')==0 || str.indexOf('#')==0)) {
    					userName = userNameBuilder(str);
    				}else if(str.indexOf('@')>0 && 
    						validCharacterVerify(str.charAt(str.indexOf('@')-1)) &&
    						!validCharacterVerify(str.charAt(str.indexOf('@')+1))) {
    					userName = userNameBuilder(str);
    				}else if(str.indexOf('#') > 0 && 
    						validCharacterVerify(str.charAt(str.indexOf('#')-1)) &&
    						!validCharacterVerify(str.charAt(str.indexOf('#')+1))) {
    					userName = userNameBuilder(str);
    				}
    				System.out.println(userName);
    				if(userName!="") {
    					userNameSet.add(userName);
    				}
    			}
    		}
    		
    	}
    	return userNameSet;
    }
    
    private static String userNameBuilder(String str) {
    	StringBuilder strBuilder = new StringBuilder();
    	
		for(int i=1; i<str.length();i++) {
			char ch = str.charAt(i);
			if(!validCharacterVerify(ch)) {
				strBuilder.append(ch);
			}
		}
		
		return strBuilder.toString().toUpperCase();
    
    }
    
    private static boolean validCharacterVerify(char ch) {
    	
    	char[] validCharacterArr = {'_','-'};
    	
    	if((ch >='a' && ch <= 'z') || 
    			(ch >= 'A' && ch <= 'Z') || 
    			(ch >= '0' && ch <= '9') || 
    			(new String(validCharacterArr).indexOf(ch)!=-1)) {
    		return false;
    	}
    	
    	return true;
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
