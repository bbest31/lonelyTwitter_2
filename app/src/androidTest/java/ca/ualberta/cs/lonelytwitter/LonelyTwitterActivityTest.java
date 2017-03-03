package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2<LonelyTwitterActivity> {

    private Solo solo;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    //Junit methods that run before and after test cases
    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(),getActivity());

    }


    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testTweet(){

        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.clickOnButton("Clear");
        //can set a automated user input into the desired text entry on test
        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
        //automated button click on a button with the text Save on it, searches for this button.
        solo.clickOnButton("Save");
        //if we don't clear the text it will just always pass
        solo.clearEditText((EditText) solo.getView(R.id.body));


        assertTrue(solo.waitForText("Test Tweet!"));
        solo.clickOnButton("Clear");
        assertFalse(solo.searchText("Test Tweet!"));
    }

    public void testClickTweetList(){

        LonelyTwitterActivity  activity = (LonelyTwitterActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.clickOnButton("Clear");
        //can set a automated user input into the desired text entry on test
        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
        //automated button click on a button with the text Save on it, searches for this button.
        solo.clickOnButton("Save");
        solo.waitForText("Test Tweet!");

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("Test Tweet!", tweet.getMessage());


        solo.clickInList(0);

        solo.assertCurrentActivity("Wrong Activity", EditTweetActivity.class);
        assertTrue(solo.waitForText("Test Tweet!"));

        //same as hitting back button on device
        solo.goBack();
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
    }

    @Override
    public void tearDown() throws Exception {
        //very important method to clear up activities
        solo.finishOpenedActivities();
    }

}