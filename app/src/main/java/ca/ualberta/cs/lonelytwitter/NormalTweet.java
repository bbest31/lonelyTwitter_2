package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 */
public class NormalTweet extends Tweet {
    /**
     * Instantiates a new Normal tweet.
     *
     * @param message the message
     */
    public NormalTweet(String message) {
        super(message);
    }

    /**
     * Instantiates a new Normal tweet.
     *
     * @param date    the date of Tweet taken from super class Tweet
     * @param message the message of tweet taken from super class tweet
     */
    public NormalTweet(Date date, String message) {
        super(date, message);
    }

    @Override
    public Boolean isImportant(){
        return false;
    }
}
