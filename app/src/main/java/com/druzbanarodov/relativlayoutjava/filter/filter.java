package com.druzbanarodov.relativlayoutjava.filter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.druzbanarodov.relativlayoutjava.R;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class filter extends AppCompatActivity {

    PhotoEditor mPhotoEditor;
    PhotoEditorView mPhotoEditorView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        //Use custom font using latest support library
       // Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);
        mPhotoEditorView =  findViewById(R.id.photoEditorView);

//loading font from assest
//        Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
              //  .setDefaultTextTypeface(mTextRobotoTf)
             //   .setDefaultEmojiTypeface(mEmojiTypeFace)
                .build();

    }
}
