package ca.ualberta.cs.lonelytwitter;

/**
 * Created by shida3 on 1/19/17.
 */
public interface Tweetable {
    /**
     * Gets message from the tweet in a string format
     *
     * @return the message in string format
     */
    public String getMessage();

    /**
     * Sets message.
     *
     * @param string the string
     * @throws TweetTooLongException the tweet too long exception
     */
    public void setMessage(String string) throws TweetTooLongException;
}
