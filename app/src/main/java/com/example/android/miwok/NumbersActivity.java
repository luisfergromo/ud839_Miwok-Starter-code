package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteControlClient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.miwok.R.drawable.number_eight;
import static com.example.android.miwok.R.drawable.number_five;
import static com.example.android.miwok.R.drawable.number_four;
import static com.example.android.miwok.R.drawable.number_nine;
import static com.example.android.miwok.R.drawable.number_one;
import static com.example.android.miwok.R.drawable.number_seven;
import static com.example.android.miwok.R.drawable.number_six;
import static com.example.android.miwok.R.drawable.number_ten;
import static com.example.android.miwok.R.drawable.number_three;
import static com.example.android.miwok.R.drawable.number_two;


public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti", number_one, R.raw.number_one));
        words.add(new Word("two","otiiko",number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",number_three,R.raw.number_three));
        words.add(new Word("four", "oyyisa",number_four,R.raw.number_four));
        words.add(new Word("five", "massokka",number_five,R.raw.number_five));
        words.add(new Word("six", "temmokka",number_six,R.raw.number_six));
        words.add(new Word("seven", "kenekaku", number_seven,R.raw.number_seven));
        words.add(new Word ("eight","kawinta", number_eight,R.raw.number_eight));
        words.add(new Word ("nine", "wo’e",number_nine,R.raw.number_nine));
        words.add(new Word("ten","na’aacha",number_ten,R.raw.number_ten));

        WordAdapter adapter  =
                new WordAdapter(this, words, R.color.category_numbers);

        //LinearLayout rootView =(LinearLayout)findViewById(R.id.rootView);
        ListView listView = (ListView) findViewById(R.id.numbers_list);

        //GridView gridView =(GridView) findViewById(R.id.list);
       /*
       for (int i=0;i<words.size();i++) {

            TextView wordView = new TextView(this);
            wordView.setText(words.get(i));
            listView.addView(wordView);
        }
 */
        /*String[] words = new String[10];
        words[0]="one";
        words[1]="two";
        words[2]="three";
        words[3]="four";
        words[4]="five";
        words[5]="six";
        words[6]="seven";
        words[7]="eight";
        words[8]="nine";
        words[9]="ten";
        */

        /*
        Log.v("NumberActivity", "Word at index 0:" + words.get(0));
        Log.v("NumberActivity", "Word at index 1:" + words.get(1));
        Log.v("NumberActivity", "Word at index 2:" + words.get(2));
        Log.v("NumberActivity", "Word at index 3:" + words.get(3));
        Log.v("NumberActivity", "Word at index 4:" + words.get(4));
        Log.v("NumberActivity", "Word at index 5:" + words.get(5));
        Log.v("NumberActivity", "Word at index 6:" + words.get(6));
        Log.v("NumberActivity", "Word at index 7:" + words.get(7));
        Log.v("NumberActivity", "Word at index 8:" + words.get(8));
        Log.v("NumberActivity", "Word at index 9:" + words.get(9));
 */
        listView.setAdapter(adapter);
        //gridView.setAdapter(itemsAdapter);
   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
           //Toast.makeText(NumbersActivity.this,"List item clicked",Toast.LENGTH_SHORT).show();
           Word word = words.get(position);
           //Log.v("NumbersActivity", "Current word: " + word);

           releaseMediaPlayer();

           // Request audio focus for playback
           int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                   // Use the music stream.
                   AudioManager.STREAM_MUSIC,
                   // Request permanent focus.
                   AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

           if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
              // mAudioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
               // Start playback



           mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResourceId());
           mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
           }
   }
       });
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Log.v("MainActivity", "onStop");
        releaseMediaPlayer();
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
