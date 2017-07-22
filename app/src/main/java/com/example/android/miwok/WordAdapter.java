package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luisromo on 16/07/17.
 */

public class WordAdapter extends ArrayAdapter<Word>{
    private int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0, words);
        mColorResourceId = colorResourceId;
    }
    // @NonNull

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwok_default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentWord.getDefaulTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageIC);

        if(currentWord.hasImage()) {
            //Add imageView
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }else{
            //imageView.setVisibility(View.GONE); //dont show image
            imageView.setVisibility(View.GONE);
        }
        View textConteiner  = listItemView.findViewById(R.id.text_container);
        int colors = ContextCompat.getColor(getContext(), mColorResourceId);
        textConteiner.setBackgroundColor(colors);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
