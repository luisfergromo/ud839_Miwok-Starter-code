package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.raw.phrase_are_you_coming;
import static com.example.android.miwok.R.raw.phrase_come_here;
import static com.example.android.miwok.R.raw.phrase_how_are_you_feeling;
import static com.example.android.miwok.R.raw.phrase_im_coming;
import static com.example.android.miwok.R.raw.phrase_im_feeling_good;
import static com.example.android.miwok.R.raw.phrase_lets_go;
import static com.example.android.miwok.R.raw.phrase_my_name_is;
import static com.example.android.miwok.R.raw.phrase_what_is_your_name;
import static com.example.android.miwok.R.raw.phrase_where_are_you_going;
import static com.example.android.miwok.R.raw.phrase_yes_im_coming;

public class PhrasesActivity extends AppCompatActivity {
public MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletitionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?","minto wuksus",phrase_where_are_you_going));
        words.add(new Word("What is your name?","tinnә oyaase'nә",phrase_what_is_your_name));
        words.add(new Word("My name is...","oyaaset...",phrase_my_name_is));
        words.add(new Word("How are you feeling?","michәksәs?",phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.","kuchi achit",phrase_im_feeling_good));
        words.add(new Word("Are you coming?","әәnәs'aa?",phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.","hәә’ әәnәm",phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",phrase_im_coming));
        words.add(new Word("Let’s go.","yoowutis",phrase_lets_go));
        words.add(new Word("Come here.","әnni'nem",phrase_come_here));
        WordAdapter adapter  =
                new WordAdapter(this, words,R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.phrases_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(NumbersActivity.this,"List item clicked",Toast.LENGTH_SHORT).show();
                Word word = words.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getmAudioResourceId());
                mMediaPlayer.start();
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
