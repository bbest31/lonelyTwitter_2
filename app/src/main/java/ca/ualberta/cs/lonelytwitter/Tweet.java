package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 */
public abstract class Tweet implements Tweetable{
    private Date date;
    private String message;

    /**
     * Instantiates a new Tweet.
     *
     * @param message the message of tweet
     * @variable date is set to current date
     */
    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    /**
     * Instantiates a new Tweet.
     *
     * @param date    the date
     * @param message the message
     */
    public Tweet(Date date, String message){
        this.message = message;
        this.date = date;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws TweetTooLongException{
        if (message.length() > 140){
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    /**
     * Is important boolean.
     *
     * @return the boolean
     */
    public abstract Boolean isImportant();

    @Override
    public String toString(){
        return date.toString() + " | " + message;
    }

}
