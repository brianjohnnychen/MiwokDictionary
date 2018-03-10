package com.example.android.miwok;

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

    /**
     * Class constructor
     *
     * @param defaultTranslation The translation of the word in the user's primary language
     * @param miwokTranslation   Miwok translation of the word
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * 2nd Class constructor for Words that need a corresponding image
     *
     * @param defaultTranslation The translation of the word in the user's primary language
     * @param miwokTranslation   Miwok translation of the word
     * @param imageResourceId    Id for the icon image
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
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
}
