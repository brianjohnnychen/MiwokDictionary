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
}
