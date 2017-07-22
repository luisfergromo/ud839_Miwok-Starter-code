package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.miwok.R.drawable.family_daughter;
import static com.example.android.miwok.R.drawable.family_grandfather;
import static com.example.android.miwok.R.drawable.family_grandmother;
import static com.example.android.miwok.R.drawable.family_mother;
import static com.example.android.miwok.R.drawable.family_older_brother;
import static com.example.android.miwok.R.drawable.family_older_sister;
import static com.example.android.miwok.R.drawable.family_son;
import static com.example.android.miwok.R.drawable.family_younger_brother;
import static com.example.android.miwok.R.drawable.family_younger_sister;

public class FamilyActivity extends AppCompatActivity {
private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletitionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother","әṭa",family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",family_son,R.raw.family_son));
        words.add(new Word("daughter","tune", family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","teṭe",family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",family_grandfather,R.raw.family_grandfather));
        WordAdapter adapter  =
                new WordAdapter(this, words,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.family_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(NumbersActivity.this,"List item clicked",Toast.LENGTH_SHORT).show();
                Word word = words.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //Toast.makeText(FamilyActivity.this,"I'm Done",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Log.v("MainActivity", "onStop");
        releaseMediaPlayer();
    }
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
        }
    }
}
