/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewpager);

        //create an adapter that knows which format should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        //set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setElevation(0);
        tabLayout.setupWithViewPager(viewPager);

//        //find the view that shows the number category
//        TextView numbers = (TextView) findViewById(R.id.numbers);
//
//        //set a click listener on a new OnClickListener (similar to declaring a new separate class then constructing it)
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //create an intent to open the numbers activity
//                Intent intent = new Intent(MainActivity.this, NumbersActivity.class);
//
//                //start the new intent
//                startActivity(intent);
//            }
//        });
//
//        TextView colors = (TextView) findViewById(R.id.colors);
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        TextView family = (TextView) findViewById(R.id.family);
//        family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, FamilyMembersActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        TextView phrases = (TextView) findViewById(R.id.phrases);
//        phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    /**
     * @param view Receives onClick from a button and opens the NumbersList.
     *
     * //Create an implicit intent if you don't care which app component handles the intent, as long as they can. Usually used to open other apps b/c can't guarentee specific app is installed on device.
     * //Create an explicit intent if you know EXACTLY which app component should handle your intent. Usually used within the same applicaton b/c developer knows what's available. (this method is an explicit intent)
     */
    //originally used for onClick in XML, replaced by NumbersClickListener function
//    public void openNumbersList(View view) {
//        Intent intent = new Intent(this, NumbersActivity.class);
//        startActivity(intent);
//    }
}
