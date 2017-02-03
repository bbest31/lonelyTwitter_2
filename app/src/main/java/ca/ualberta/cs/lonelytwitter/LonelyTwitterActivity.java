package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class is the main view class of the project <b> In this class, user
 * interaction and file manipulation is performed.
 * All files are in the form of "json" files that are stored in Emulator's
 * accessible from Android Device Monitor:
 * <pre>
 *     pre-formatted text: </b>
 *     File-Explorere -> data -> data -> ca.ualberta.cslonelytwitter -> file
 * </pre>
 * <code>begin <br>
 * some pseudo code here
 * end.</code>
 * The file nae is indicated in the &nbsp &nbsp FILENAME constant.
 * <ul>
 * <ol>
 * <li>item l</li>
 * <li>item 2</li>
 * </ol>
 * </ul>
 *
 * @author Brandon Best
 * @version 1.4.2
 * @since 1.0
 */
public class LonelyTwitterActivity extends Activity {

	/**
	 * The file that all the tweets are saved there.
	 * The format of the file is JSON.
	 * @see #loadFromFile()
	 * @see #saveInFile()
	 */
	private enum  TweetListOrdering {
		/**
		 * Date ascending tweet list ordering. Orders tweets in the TweetList from oldest date to newest date
		 */
		DATE_ASCENDING,
		/**
		 * Date descending tweet list ordering. Orders tweets in the TweetList from newest date to oldest date
		 */
		DATE_DESCENDING,
		/**
		 * Text ascending tweet list ordering. Orders tweets in alphabetical order from Z-A
		 */
		TEXT_ASCENDING, /**
		 * Text descending tweet list ordering.Orders tweets in alphabetica order form A-Z
		 */
		TEXT_DESCENDING};
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweetList;
    private ArrayAdapter<Tweet> adapter;

	/**
	 * Called when the activity is first created
	 * @param savedInstanceState
     */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				text = trimExtraSpaces(text);


				Tweet tweet = new NormalTweet(text);

                tweetList.add(tweet);

                adapter.notifyDataSetChanged();

                saveInFile();

			}
		});

		Button clearButton = (Button) findViewById(R.id.clear);
		clearButton.setOnClickListener(new View.OnClickListener(){
			public  void onClick(View v){
				setResult(RESULT_OK);
				tweetList.clear();

				adapter.notifyDataSetChanged();

				saveInFile();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		loadFromFile();

		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * Reemoves the extra spaces in the given string
	 * @param inputString input string
	 * @return string without extra spaces
     */
    private String trimExtraSpaces(String inputString) {
	inputString = inputString.replaceAll("//s+"," ");
	return inputString;
}

	private void sortTweetListItems(TweetListOrdering ordering){

	}


	/**
	 * Loads tweets from file.
	 * @throws ThrowTooLongExecption - if the tweet is too long
	 * @exception  FileNotFoundException - if the tweet is empty
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
            tweetList = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
            tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Saves tweets in file in Json format.
	 * @throws FileNotFoundException if folder does nto exists.
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(tweetList, out);
            out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}