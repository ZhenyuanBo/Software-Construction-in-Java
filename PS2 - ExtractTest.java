package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "bozhenyuan", "hello zhenyuan.bo@mail.utoronto.ca @bozhenyuan @yiren @iris @xihua hi", d1);
    private static final Tweet tweet4 = new Tweet(4, "bozhen", "hello h@mail hi",d1);
    private static final Tweet tweet5 = new Tweet(5, "boz1", "hello @*zy hihi", d2);
    private static final Tweet tweet6 = new Tweet(6, "boz2", "hello @yiren @Fukushima hi", d1);
    private static final Tweet tweet7 = new Tweet(7, "boz3", "hello @jp1 @jp1 hihi", d2);
    private static final Tweet tweet8 = new Tweet(8, "boz0", "hello @jp1 @jp1 hihi #hype #h1", d2);
    private static final Tweet tweet9 = new Tweet(9, "zyb0", "hello @xy @jwz hihi #china #canada", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1,tweet2));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUserHashTag() {
    	Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet8,tweet9));
    	
    	assertEquals("# of mentioned users is 7", 7, mentionedUsers.size());
    }
    
    @Test
    public void testGetMentionedUserValidCharacterBeforeMention() {
    	Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1,tweet4));
    	
    	assertTrue("expected empty set", mentionedUsers.isEmpty());
    	
    }
    
    @Test
    public void testGetMentionedUserValidCharacterAfterMention() {
    	Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
    	
    	assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUserInvalidCharacterAfterMention() {
    	Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5));
    	
    	assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUserValidMUser() {
    	Set<String> mentionedUsers= Extract.getMentionedUsers(Arrays.asList(tweet6, tweet7));
    	
    	assertEquals(mentionedUsers.size(), 3);
    	
    }


    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
