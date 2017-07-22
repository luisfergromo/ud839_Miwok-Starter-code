package com.example.android.miwok;

/**
 * Created by luisromo on 16/07/17.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1; //0
    private int mAudioResourceId;
/*
    Create a constructor for the view that have image
*/
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }


    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }

    public String getDefaulTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int getImageResourceId(){return mImageResourceId;}
    public boolean hasImage(){ return mImageResourceId != NO_IMAGE_PROVIDED;}
    public int getmAudioResourceId(){return mAudioResourceId;}
}
