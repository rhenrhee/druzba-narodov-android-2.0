package com.druzbanarodov.relativlayoutjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ScoreCard extends AppCompatActivity {
    TextView a11, a12, a13, a14, a15, a16, a17, a18, a19,
            a20, a21, a22, a23, a24, a25, a26, a27, a28, a29;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences = getSharedPreferences("Score", Context.MODE_PRIVATE);
        a11 = (TextView) findViewById(R.id.indoeuropeanslavic);
        a12 = (TextView) findViewById(R.id.indoeuropeanarmenian);
        a13 = (TextView) findViewById(R.id.indoeuropeangermaniс);
        a14 = (TextView) findViewById(R.id.indoeuropeaniranian);
        a15 = (TextView) findViewById(R.id.indoeuropeanindorian);
        a16 = (TextView) findViewById(R.id.indoeuropeangreek);
        a17 = (TextView) findViewById(R.id.altaiturkic);
        a18 = (TextView) findViewById(R.id.altaimongolian);
        a19 = (TextView) findViewById(R.id.altaitungusmanchurian);
        a20 = (TextView) findViewById(R.id.altaikorean);
        a21 = (TextView) findViewById(R.id.northcaucasianabkhazadyghe);
        a22 = (TextView) findViewById(R.id.northcaucasiannakhdagestan);
        a23 = (TextView) findViewById(R.id.uralyukagirsamoyed);
        a24 = (TextView) findViewById(R.id.uralyukagirfinnougric);
        a25 = (TextView) findViewById(R.id.uralyukagiryukagirskaya);
        a26 = (TextView) findViewById(R.id.kartvelskayageorgian);
        a27 = (TextView) findViewById(R.id.paleoasianchukotkakamchatka);
        a28 = (TextView) findViewById(R.id.paleoasianeskimoaleutian);
        a29 = (TextView) findViewById(R.id.paleoasianyenisei);
        try {
            a11.setText("" + sharedPreferences.getInt("Индоевропейская семья. Славянская ветвь", 0));
            a12.setText("" + sharedPreferences.getInt("Индоевропейская семья. Армянская ветвь", 0));
            a13.setText("" + sharedPreferences.getInt("Индоевропейская семья. Германская ветвь", 0));
            a14.setText("" + sharedPreferences.getInt("Индоевропейская семья. Иранская ветвь", 0));
            a15.setText("" + sharedPreferences.getInt("Индоевропейская семья. Индорайская ветвь", 0));
            a16.setText("" + sharedPreferences.getInt("Индоевропейская семья. Греческая ветвь", 0));
            a17.setText("" + sharedPreferences.getInt("Алтайская семья. Тюрская ветвь", 0));
            a18.setText("" + sharedPreferences.getInt("Алтайская семья. Монгольская ветвь", 0));
            a19.setText("" + sharedPreferences.getInt("Алтайская семья. Тунгусо-манчжурская ветвь", 0));
            a20.setText("" + sharedPreferences.getInt("Алтайская семья. Корейская ветвь", 0));
            a21.setText("" + sharedPreferences.getInt("Северо-кавказская семья. Абхазско-адыгская ветвь", 0));
            a22.setText("" + sharedPreferences.getInt("Северо-кавказская семья. Нахско-дагестанская ветвь", 0));
            a23.setText("" + sharedPreferences.getInt("Уральско-юкагирская семья. Самодийская ветвь", 0));
            a24.setText("" + sharedPreferences.getInt("Уральско-юкагирская семья. Финно-угорская ветвь", 0));
            a25.setText("" + sharedPreferences.getInt("Уральско-юкагирская семья. Юкагирская ветвь", 0));
            a26.setText("" + sharedPreferences.getInt("Картвельская семья. Грузинская ветвь", 0));
            a27.setText("" + sharedPreferences.getInt("Палеоазиатская семья. Чукотско-камчатская ветвь", 0));
            a28.setText("" + sharedPreferences.getInt("Палеоазиатская семья. Эскимосско-алеутская ветвь", 0));
            a29.setText("" + sharedPreferences.getInt("Палеоазиатская семья. Енисейская ветвь", 0));
        } catch (Exception e) {
            Toast.makeText(ScoreCard.this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

}
