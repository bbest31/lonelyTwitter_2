package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class EditTweetActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        Tweet oldtweet = (Tweet)getIntent().getSerializableExtra("oldTweet");
       // position = getIntent().getIntExtra("i",0);

        TextView TweetView = (TextView) findViewById(R.id.sentTweetText);
        TweetView.setText(oldtweet.getMessage());

    }
}
