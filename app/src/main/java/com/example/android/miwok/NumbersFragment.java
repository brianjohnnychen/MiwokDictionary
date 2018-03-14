package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    //handles playback of all the sound files
    private MediaPlayer mMediaPlayer;

    //handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    //listener to determine who has audio focus
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        /*
                        * AUDIO_FOCUS_LOSS_TRANSIENT means we've lost audio focus for a short amount of time. The CAN_DUCK case menas
                        * our app is allowed to continue playing sound but at a lower volume.
                        * We'll treat both cases the same way because our app is playing short sound files.
                        * */

                        //pause playback
                        mMediaPlayer.pause();
                        //set audio back to play from beginning since word/phrase is short
                        //also better to listen to whole word again than half way
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                        releaseMediaPlayer();
                    }
                }
            };

    //declare globally so it only needs to be created once
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolockosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyylsa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        int logIndex = 0;
        while (logIndex < words.size()) {
            Log.v("NumbersActivity", "Word at index " + logIndex + " is: " + words.get(logIndex));
            logIndex++;
        }


        /**
         * Create an {@link ArrayAdapter}, whose data source is a list of Strings.
         * The adapter knows how to create layouts for each item in the list, using the simple_list_item_1.xml layout resource defined in the Android framework.
         * This list item layout contains a single {@link TextView}, which the adapter will set to display a single word
         * */
        //ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);
        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        /**
         * Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
         * There should be a {@link ListView} with the view ID called list, which is declared in activity_numbers.xml layout file.*/
        ListView listView = rootView.findViewById(R.id.list);
        //GridView gridView = (GridView) findViewById(R.id.list);

        /**
         * Make the {@link ListView} use the {@link ArrayAdapter} created above, so that the {@link ListView} will display list items for each word in the list of words.
         * Do this by calling the setAdapter method on the {@link ListView} object and pass in 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.*/
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //change from NumbersActivity.this to getActivity() because we're now inside a fragment instead of an activity
                Toast.makeText(getActivity(), "List item clicked.", Toast.LENGTH_SHORT).show();

                //release media player if it currently exists because we are about to play a different sound file
                //in case user taps multiple items in a row before sound ends and OnCompleteListener doesn't get triggered
                releaseMediaPlayer();

                Word word = words.get(position);

/*              Note: If you concatenate (with the “+” operator) a string with a Word object,
                then Java will implicitly call the toString() method on the object.
                That means, these two statements are equivalent:
*/
                Log.v("NumbersActivity", "Current word: " + word); //implicitly call toString method to see contents of current word
                Log.v("NumbersActivity", "Current word: " + word.toString());

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request short term focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback if audio focus is granted
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start(); //no need to call prepare(), create() does that for you

                    //setup a listener on the media player to stop and release media player once sound done playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //release media player to stop sounds when activity is stopped
        //use releaseMediaPlyaer() method instead of release() because there's extra logic to set MediaPlayer back to null
        releaseMediaPlayer();
    }
}
