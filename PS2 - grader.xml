package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     */
	
	 private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
	    
	 private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
	    
	 private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
	 private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
	 private static final Tweet tweet3 = new Tweet(3, "bozhenyuan", "hello zhenyuan.bo@mail.utoronto.ca @bozhenyuan @yiren @iris @xihua hi", d1);
	 private static final Tweet tweet4 = new Tweet(4, "bozhen", "hello h@mail hi",d1);
	 private static final Tweet tweet5 = new Tweet(5, "boz1", "hello @*zy hihi", d2);
	 private static final Tweet tweet6 = new Tweet(6, "boz2", "hello @Yiren @Fukushima hi", d1);
	 private static final Tweet tweet7 = new Tweet(7, "boz3", "hello @Yiren @jp1 hihi", d2);
	 private static final Tweet tweet8 = new Tweet(8, "boz4", "hello @zy1 @zy2 @zy3 88", d1);
	 private static final Tweet tweet9 = new Tweet(9, "boz5", "hello @Yiren @zy1 @zy6 99", d2);
	 private static final Tweet tweet10 = new Tweet(10, "bo6", "hello @zy5 @zy2 @zy1 @Yiren 99", d2);  
	 private static final Tweet tweet11 = new Tweet(11, "boz0", "hello @jp1 @jp1 hihi #hype #h1 #canada", d2);
	 private static final Tweet tweet12 = new Tweet(12, "zyb0", "hello @xy @jwz hihi #china #canada", d2);
	    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    @Test
    public void testGuessFollowsGraphNotEmpty() {
    	Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet6,tweet7));
    	
    	assertFalse("expected non-empty graph", followsGraph.isEmpty());
    }
    
    @Test
    //Check if the usernames are printed out in the descending order of the number of follows
    public void testInfluencersNotEmpty() {
    	Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet6,tweet7, tweet8, tweet9, tweet10, tweet11, tweet12));
    	List<String> influencers = SocialNetwork.influencers(followsGraph);
    	
    	assertFalse("expected non-empty influencers list", influencers.isEmpty());   	
    }
    

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
