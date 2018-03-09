package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 3/8/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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

        return listItemView;
    }
}
