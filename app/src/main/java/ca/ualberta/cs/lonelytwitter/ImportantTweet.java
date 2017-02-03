package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 */
public class ImportantTweet extends Tweet {

    /**
     * Instantiates a new Important tweet.
     *
     * @param message - string message taken from the super class of Tweet
     */

    public ImportantTweet(String message) {
        super(message);
    }

    /**
     * Instantiates a new Important tweet.
     *
     * @param date    the date of the Tweet
     * @param message the message of the Tweet
     */
    public ImportantTweet(Date date, String message) {
        super(date, message);
    }

    @Override
    public Boolean isImportant(){
        return true;
    }
}
