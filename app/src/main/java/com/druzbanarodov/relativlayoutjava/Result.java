package com.druzbanarodov.relativlayoutjava;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {
    TextView correct, incorrect, attempted, score, you;
    int cor = 0, incorr = 0, attempt = 0, scor = 0, yo = 0;
    int x = 0;
    private static final int def = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        cor = intent.getIntExtra("correct", 0);
        attempt = intent.getIntExtra("attemp", 0);
        incorr = attempt - cor;
        scor = 10 * cor;
        correct = (TextView) findViewById(R.id.correct);
        incorrect = (TextView) findViewById(R.id.incorrect);
        attempted = (TextView) findViewById(R.id.attempted);
        score = (TextView) findViewById(R.id.score);
        you = (TextView) findViewById(R.id.you);

        attempted.setText("  " + attempt);
        correct.setText("  " + cor);
        incorrect.setText("  " + incorr);
        score.setText("Счет  :    " + scor);
        float x1 = (cor * 100) / attempt;
        if (x1 < 40)
            you.setText("Вам нужно больше изучать ");
        else if (x1 < 70)
            you.setText("Ваши знания на среднем уровне ");
        else if (x1 < 90)
            you.setText("Ваши знания выше среднего, следует немного потренероваться ");
        else
            you.setText("Блестяще! Вы отлично разбираетесь в теме! ");
    }
}
