package com.example.android.miwok;

import android.media.MediaPlayer;
import android.view.MenuItem;

/**
 * Created by brian on 3/8/2018.
 * {@link Word} represents a vocabulary word that hte user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    //default translation for the words. Lowercase m is added to indicate that these are private variables meant only for this class
    private String mDefaultTranslation;
    private String mMiwokTranslation;

    //image resource ID, set it to -1 by default to indicate no image, will be changed to true if an image id is found
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //variable to determine if an image is provided for the word
    private static final int NO_IMAGE_PROVIDED = -1;

    //Audio resource ID for the word
    private int mAudioResourceId;

    /**
     * Class constructor for word and translation only
     *
     * @param defaultTranslation The translation of the word in the user's primary language
     * @param miwokTranslation   Miwok translation of the word
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Class constructor for phrases that has no corresponding image
     *
     * @param defaultTranslation The translation of the word in the user's primary language
     * @param miwokTranslation   Miwok translation of the word
     * @param audioResourceId    Id for the icon image
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Class constructor for Words that need a corresponding image and sound
     *
     * @param defaultTranslation The translation of the word in the user's primary language
     * @param miwokTranslation   Miwok translation of the word
     * @param imageResourceId    Id for the icon image
     * @param audioResourceId   resource ID for the pronunciation audio file
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get Miwok translation of the word
     */
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    //check if an image is provided for the word
    public boolean hasImage()
    {
        //if the image resource is not equal to -1, then there is a valid image, so return true
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId()
    {
        return mAudioResourceId;
    }
}
