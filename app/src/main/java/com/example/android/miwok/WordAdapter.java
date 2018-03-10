package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 3/8/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    //Resource ID for the background color for this list of words
    private int mColorResourceId;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Get the {@link WordAdapter} object located at this position in the list
        Word currentWord = getItem(position);

        //Find the TextView with the default translation and set the text to the current word
        TextView defaultListView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultListView.setText(currentWord.getDefaultTranslation());

        TextView miwokListView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokListView.setText(currentWord.getmMiwokTranslation());

        //Find the ImageView that holds the picture for each word, and fill it with the proper image
        ImageView wordImage = (ImageView) listItemView.findViewById(R.id.word_image);

        //if the current word has an image, set the image
        if(currentWord.hasImage())
        {
            //set the word image to the image source specified in the current word
            wordImage.setImageResource(currentWord.getmImageResourceId());

            //make sure the view is visible because views get reused
            wordImage.setVisibility(View.VISIBLE);
        }
        else
        {
            //otherwise, hide the wordView (set visibility to GONE so it doesn't take up any space in the layout)
            wordImage.setVisibility(View.GONE);
        }

        //set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //find the color that the resource Id maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //set the background color of the text container view
        textContainer.setBackgroundColor(color);

        //return the whole list item layout (containing 2 TextViews) so that it can be shown in the ListView
        return listItemView;
    }
}
