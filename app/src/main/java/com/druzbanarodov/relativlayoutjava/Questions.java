package com.druzbanarodov.relativlayoutjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class Questions extends AppCompatActivity
{
    DonutProgress donutProgress;
    int variable =0;
    TextView ques;
    Button OptA, OptB, OptC, OptD;
    Button play_button;
    String get;
    //Objects of different classes
    IndoEuropeanSlavic _indoEuropeanSlavic;
    IndoEuropeanArmenian _indoEuropeanArmenian;
    IndoEuropeanGermanic _indoEuropeanGermanic;
    IndoEuropeanIranian _indoEuropeanIranian;
    IndoEuropeanIndorian _indoEuropeanIndorian;
    IndoEuropeanGreek _indoEuropeanGreek;
    AltaiTurkic _altaiTurkic;
    AltaiMongolian _altaiMongolian;
    AltaiTungusManchurian _altaiTungusManchurian;
    AltaiKorean _altaiKorean;
    NorthCaucasianAbkhazAdyghe _northCaucasianAbkhazAdyghe;
    NorthcaucasianNakhDagestan _northcaucasianNakhDagestan;
    UralyUkagirSamoyed _uralyUkagirSamoyed;
    UralyUkagirFinnoUgric _uralyUkagirFinnoUgric;
    UralyUkagiryUkagirskaya _uralyUkagiryUkagirskaya;
    KartvelskayaGeorgian _kartvelskayaGeorgian;
    PaleoAsianChukotkaKamchatka _paleoAsianChukotkaKamchatka;
    PaleoAsianEskimoAleutian _paleoAsianEskimoAleutian;
    PaleoAsianyEnisei _paleoAsianyEnisei;
    Maths _maths;
    Science _science;
    public int visibility = 0,
            branch1 = 0, branch2 = 0, branch3 = 0, branch4 = 0,
            branch5 = 0, branch6 = 0, branch7 = 0, branch8 = 0,
            branch9 = 0, branch10 = 0, branch11 = 0, branch12 = 0,
            branch13 = 0, branch14 = 0, branch15 = 0, branch16 = 0,
            branch17 = 0, branch18 = 0, branch19 = 0,
            i, j = 0, k = 0, l = 0;
    String global = null, Ques, Opta, Optb, Optc, Optd;
    ArrayList<Integer> list = new ArrayList<Integer>();
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();//recieving the intent send by the Navigation activity
        get = intent.getStringExtra(Navigation_Activity.Message);//converting that intent message to string using the getStringExtra() method
        toast = new Toast(this);
        //attribute of the circular progress bar
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setMax(100);
        donutProgress.setFinishedStrokeColor(Color.parseColor("#FFFB385F"));
        donutProgress.setTextColor(Color.parseColor("#FFFB385F"));
        donutProgress.setKeepScreenOn(true);
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);

        //Now the linking of all the datbase files with the Question activity
        // Индо-Европейская семья славянская ветвь
        _indoEuropeanSlavic = new IndoEuropeanSlavic(this);
        _indoEuropeanSlavic.createDatabase();
        _indoEuropeanSlavic.openDatabase();
        _indoEuropeanSlavic.getWritableDatabase();

        // Индоевропейская семья армянская ветвь
        _indoEuropeanArmenian = new IndoEuropeanArmenian(this);
        _indoEuropeanArmenian.createDatabase();
        _indoEuropeanArmenian.openDatabase();
        _indoEuropeanArmenian.getWritableDatabase();

        // Индоевропейская семья германская ветвь
        _indoEuropeanGermanic = new IndoEuropeanGermanic(this);
        _indoEuropeanGermanic.createDatabase();
        _indoEuropeanGermanic.openDatabase();
        _indoEuropeanGermanic.getWritableDatabase();

        // Индоевропейская семья иранская ветвь
        _indoEuropeanIranian = new IndoEuropeanIranian(this);
        _indoEuropeanIranian.createDatabase();
        _indoEuropeanIranian.openDatabase();
        _indoEuropeanIranian.getWritableDatabase();

        // Индоевропейская семья индрайская ветвь
        _indoEuropeanIndorian = new IndoEuropeanIndorian(this);
        _indoEuropeanIndorian.createDatabase();
        _indoEuropeanIndorian.openDatabase();
        _indoEuropeanIndorian.getWritableDatabase();

        // Индоевропейская семья греческая ветвь
        _indoEuropeanGreek = new IndoEuropeanGreek(this);
        _indoEuropeanGreek.createDatabase();
        _indoEuropeanGreek.openDatabase();
        _indoEuropeanGreek.getWritableDatabase();

        // Алтайская семья тюрская ветвь
        _altaiTurkic = new AltaiTurkic(this);
        _altaiTurkic.createDatabase();
        _altaiTurkic.openDatabase();
        _altaiTurkic.getWritableDatabase();

        // Алтайская семья монгольская ветвь
        _altaiMongolian = new AltaiMongolian(this);
        _altaiMongolian.createDatabase();
        _altaiMongolian.openDatabase();
        _altaiMongolian.getWritableDatabase();

        // Алтайская семья тунгусо-манчжурская ветвь
        _altaiTungusManchurian = new AltaiTungusManchurian(this);
        _altaiTungusManchurian.createDatabase();
        _altaiTungusManchurian.openDatabase();
        _altaiTungusManchurian.getWritableDatabase();

        // Атлтайская семья корейская ветвь
        _altaiKorean = new AltaiKorean(this);
        _altaiKorean.createDatabase();
        _altaiKorean.openDatabase();
        _altaiKorean.getWritableDatabase();

        // Северокавказская семья Абхазско-адыгская ветвь
        _northCaucasianAbkhazAdyghe = new NorthCaucasianAbkhazAdyghe(this);
        _northCaucasianAbkhazAdyghe.createDatabase();
        _northCaucasianAbkhazAdyghe.openDatabase();
        _northCaucasianAbkhazAdyghe.getWritableDatabase();

        // Северокавказская семья Нахско-дагестанская ветвь
        _northcaucasianNakhDagestan = new NorthcaucasianNakhDagestan(this);
        _northcaucasianNakhDagestan.createDatabase();
        _northcaucasianNakhDagestan.openDatabase();
        _northcaucasianNakhDagestan.getWritableDatabase();

        // Уральско-юкагирская семья Самодийская ветвь
        _uralyUkagirSamoyed = new UralyUkagirSamoyed(this);
        _uralyUkagirSamoyed.createDatabase();
        _uralyUkagirSamoyed.openDatabase();
        _uralyUkagirSamoyed.getWritableDatabase();

        // Уральско-юкагирская семья Финно-угорская ветвь
        _uralyUkagirFinnoUgric = new UralyUkagirFinnoUgric(this);
        _uralyUkagirFinnoUgric.createDatabase();
        _uralyUkagirFinnoUgric.openDatabase();
        _uralyUkagirFinnoUgric.getWritableDatabase();

        // Уральско-юкагирская семья Юкагирская ветвь
        _uralyUkagiryUkagirskaya = new UralyUkagiryUkagirskaya(this);
        _uralyUkagiryUkagirskaya.createDatabase();
        _uralyUkagiryUkagirskaya.openDatabase();
        _uralyUkagiryUkagirskaya.getWritableDatabase();

        // Картвельская семья грущинская ветвь
        _kartvelskayaGeorgian = new KartvelskayaGeorgian(this);
        _kartvelskayaGeorgian.createDatabase();
        _kartvelskayaGeorgian.openDatabase();
        _kartvelskayaGeorgian.getWritableDatabase();

        // Палеоазиатская семья Чукотско-камчатская ветвь
        _paleoAsianChukotkaKamchatka = new PaleoAsianChukotkaKamchatka(this);
        _paleoAsianChukotkaKamchatka.createDatabase();
        _paleoAsianChukotkaKamchatka.openDatabase();
        _paleoAsianChukotkaKamchatka.getWritableDatabase();

        // Палеоазиатская семья Эскимосско-алеутская ветвь
        _paleoAsianEskimoAleutian = new PaleoAsianEskimoAleutian(this);
        _paleoAsianEskimoAleutian.createDatabase();
        _paleoAsianEskimoAleutian.openDatabase();
        _paleoAsianEskimoAleutian.getWritableDatabase();

        // Палеоазиатская семья Енисейская ветвь
        _paleoAsianyEnisei = new PaleoAsianyEnisei(this);
        _paleoAsianyEnisei.createDatabase();
        _paleoAsianyEnisei.openDatabase();
        _paleoAsianyEnisei.getWritableDatabase();

        //
        _maths = new Maths(this);
        _maths.createDatabase();
        _maths.openDatabase();
        _maths.getWritableDatabase();

        //
        _science = new Science(this);
        _science.createDatabase();
        _science.openDatabase();
        _science.getWritableDatabase();

        //Till here we are linking the database file
        OptA = (Button) findViewById(R.id.OptionA);
        OptB = (Button) findViewById(R.id.OptionB);
        OptC = (Button) findViewById(R.id.OptionC);
        OptD = (Button) findViewById(R.id.OptionD);
        ques = (TextView) findViewById(R.id.Questions);
        play_button = (Button) findViewById(R.id.play_button);//Play button to start the game
    }


    public void onClick(View v) {//When this method is executed then there will be new question came and also same method for play button
        final SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);
        k++;
        if (visibility == 0) {
            //showing the buttons which were previously invisible
            OptA.setVisibility(View.VISIBLE);
            OptB.setVisibility(View.VISIBLE);
            OptC.setVisibility(View.VISIBLE);
            OptD.setVisibility(View.VISIBLE);
            play_button.setVisibility(View.GONE);
            donutProgress.setVisibility(View.VISIBLE);
            visibility = 1;
            new CountDownTimer(50000, 1000) {//countdowntimer
                int i = 100;

                @Override
                public void onTick(long millisUntilFinished) {
                    i = i - 2;
                    donutProgress.setProgress(i);


                }

                @Override
                public void onFinish() {
                    toast.cancel();
                    SharedPreferences.Editor editor = shared.edit();//here we are saving the data when the countdown timer will finish and it is saved in shared prefrence file that is defined in onCreate method as score
                    editor.putInt("Questions", k).commit();
                    if (get.equals("branch1") && shared.getInt("Индоевропейская семья. Славянская ветвь", 0) < l)
                        editor.putInt("Индоевропейская семья. Славянская ветвь", l * 10).apply();
                    else if (get.equals("branch2") && shared.getInt("Индоевропейская семья. Армянская ветвь", 0) < l)
                        editor.putInt("Индоевропейская семья. Армянская ветвь", l * 10).apply();
                    else if (get.equals("branch3") && shared.getInt("Индоевропейская семья. Германская ветвь", 0) < l)
                        editor.putInt("Индоевропейская семья. Германская ветвь", l * 10).apply();
                    else if (get.equals("branch4") && shared.getInt("Индоевропейская семья. Иранская ветвь", 0) < l)
                        editor.putInt("Индоевропейская семья. Иранская ветвь", l * 10).apply();
                    else if (get.equals("branch5") && shared.getInt("Индоевропейская семья. Индорайская ветвь", 0) < l)
                        editor.putInt("Индоевропейская семья. Индорайская ветвь", l * 10).apply();
                    else if (get.equals("branch6") && shared.getInt("Индоевропейская семья. Греческая ветвь", 0) < l)
                        editor.putInt("Индоевропейская семья. Греческая ветвь", l * 10).apply();
                    else if (get.equals("branch7") && shared.getInt("Алтайская семья. Тюрская ветвь", 0) < l)
                        editor.putInt("Алтайская семья. Тюрская ветвь", l * 10).apply();
                    else if (get.equals("branch8") && shared.getInt("Алтайская семья. Монгольская ветвь", 0) < l)
                        editor.putInt("Алтайская семья. Монгольская ветвь", l * 10).apply();
                    else if (get.equals("branch9") && shared.getInt("Алтайская семья. Тунгусо-манчжурская ветвь", 0) < l)
                        editor.putInt("Алтайская семья. Тунгусо-манчжурская ветвь", l * 10).apply();
                    else if (get.equals("branch10") && shared.getInt("Алтайская семья. Корейская ветвь", 0) < l)
                        editor.putInt("Алтайская семья. Корейская ветвь", l * 10).apply();
                    else if (get.equals("branch11") && shared.getInt("Северо-кавказская семья. Абхазско-адыгская ветвь", 0) < l)
                        editor.putInt("Северо-кавказская семья. Абхазско-адыгская ветвь", l * 10).apply();
                    else if (get.equals("branch12") && shared.getInt("Северо-кавказская семья. Нахско-дагестанская ветвь", 0) < l)
                        editor.putInt("Северо-кавказская семья. Нахско-дагестанская ветвь", l * 10).apply();
                    else if (get.equals("branch13") && shared.getInt("Уральско-юкагирская семья. Самодийская ветвь", 0) < l)
                        editor.putInt("Уральско-юкагирская семья. Самодийская ветвь", l * 10).apply();
                    else if (get.equals("branch14") && shared.getInt("Уральско-юкагирская семья. Финно-угорская ветвь", 0) < l)
                        editor.putInt("Уральско-юкагирская семья. Финно-угорская ветвь", l * 10).apply();
                    else if (get.equals("branch15") && shared.getInt("Уральско-юкагирская семья. Юкагирская ветвь", 0) < l)
                        editor.putInt("Уральско-юкагирская семья. Юкагирская ветвь", l * 10).apply();
                    else if (get.equals("branch16") && shared.getInt("Картвельская семья. Грузинская ветвь", 0) < l)
                        editor.putInt("Картвельская семья. Грузинская ветвь", l * 10).apply();
                    else if (get.equals("branch17") && shared.getInt("Палеоазиатская семья. Чукотско-камчатская ветвь", 0) < l)
                        editor.putInt("Палеоазиатская семья. Чукотско-камчатская ветвь", l * 10).apply();
                    else if (get.equals("branch18") && shared.getInt("Палеоазиатская семья. Эскимосско-алеутская ветвь", 0) < l)
                        editor.putInt("Палеоазиатская семья. Эскимосско-алеутская ветвь", l * 10).apply();
                    else if (get.equals("branch19") && shared.getInt("Палеоазиатская семья. Енисейская ветвь", 0) < l)
                        editor.putInt("Палеоазиатская семья. Енисейская ветвь", l * 10).apply();
                    donutProgress.setProgress(0);
                    if(variable==0) {
                        Intent intent = new Intent(Questions.this, Result.class);
                        intent.putExtra("correct", l);
                        intent.putExtra("attemp", k);
                        startActivity(intent);
                        finish();
                    }
                }
            }.start();
        }

        if (global != null) {
            if (global.equals("A")) {
                if (v.getId() == R.id.OptionA) {
                    //Here we use the snackbar because if we use the toast then they will be stacked an user cannot idetify which questions answer is it showing
                    Snackbar.make(v, "         Верно ☺", Snackbar.LENGTH_SHORT).show();

                    l++;
                } else {
                    Snackbar.make(v, "Неверно\t      Ответ :" + Opta + "", Snackbar.LENGTH_SHORT).show();
                }

            } else if (global.equals("B")) {
                if (v.getId() == R.id.OptionB) {
                    Snackbar.make(v, "          Верно ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {
                    Snackbar.make(v, "Неверно\t      Ответ :" + Optb + "", Snackbar.LENGTH_SHORT).show();
                }

            } else if (global.equals("C")) {
                if (v.getId() == R.id.OptionC) {

                    Snackbar.make(v, "         Верно ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {
                    Snackbar.make(v, "Неверно\t Ответ :" + Optc + "", Snackbar.LENGTH_SHORT).show();
                }
            } else if (global.equals("D")) {
                if (v.getId() == R.id.OptionD) {
                    Snackbar.make(v, "        Верно ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {

                    Snackbar.make(v, "Неверно\t Ответ:" + Optd + "", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
        if (get.equals("branch1")) {
            if (branch1 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.indoyevropeyskaya_slavs);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 65; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch1=1;
            }
            if (j == 64 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch1") && shared.getInt("Индоевропейская семья. Славянская ветвь", 0) < l)
                    editor.putInt("Индоевропейская семья. Славянская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _indoEuropeanSlavic.readQuestion(list.get(j));
                Opta = _indoEuropeanSlavic.readOptionA(list.get(j));
                Optb = _indoEuropeanSlavic.readOptionB(list.get(j));
                Optc = _indoEuropeanSlavic.readOptionC(list.get(j));
                Optd = _indoEuropeanSlavic.readOptionD(list.get(j));
                global = _indoEuropeanSlavic.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch2")) {
            if (branch2 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.indoyevropeyskaya_armenians);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 25; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch2=1;
            }
            if (j == 24 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch2") && shared.getInt("Индоевропейская семья. Армянская ветвь", 0) < l)
                editor.putInt("Индоевропейская семья. Армянская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _indoEuropeanArmenian.readQuestion(list.get(j));
                Opta = _indoEuropeanArmenian.readOptionA(list.get(j));
                Optb = _indoEuropeanArmenian.readOptionB(list.get(j));
                Optc = _indoEuropeanArmenian.readOptionC(list.get(j));
                Optd = _indoEuropeanArmenian.readOptionD(list.get(j));
                global = _indoEuropeanArmenian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch3")) {
            if (branch3 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.indoyevropeyskaya_germ);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 25; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch3=1;
            }
            if (j == 24 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch3") && shared.getInt("Индоевропейская семья. Германская ветвь", 0) < l)
                    editor.putInt("Индоевропейская семья. Германская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _indoEuropeanGermanic.readQuestion(list.get(j));
                Opta = _indoEuropeanGermanic.readOptionA(list.get(j));
                Optb = _indoEuropeanGermanic.readOptionB(list.get(j));
                Optc = _indoEuropeanGermanic.readOptionC(list.get(j));
                Optd = _indoEuropeanGermanic.readOptionD(list.get(j));
                global = _indoEuropeanGermanic.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch4")) {
            if (branch4 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.indoyevropeyskaya_iran);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 46; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch4=1;
            }
            if (j == 45 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch4") && shared.getInt("Индоевропейская семья. Иранская ветвь", 0) < l)
                    editor.putInt("Индоевропейская семья. Иранская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _indoEuropeanIranian.readQuestion(list.get(j));
                Opta = _indoEuropeanIranian.readOptionA(list.get(j));
                Optb = _indoEuropeanIranian.readOptionB(list.get(j));
                Optc = _indoEuropeanIranian.readOptionC(list.get(j));
                Optd = _indoEuropeanIranian.readOptionD(list.get(j));
                global = _indoEuropeanIranian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch5")) {
            if (branch5 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.indoyevropeyskaya_ariycy);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 8; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch5=1;
            }
            if (j == 7 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch5") && shared.getInt("Индоевропейская семья. Индорайская ветвь", 0) < l)
                    editor.putInt("Индоевропейская семья. Индорайская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _indoEuropeanIndorian.readQuestion(list.get(j));
                Opta = _indoEuropeanIndorian.readOptionA(list.get(j));
                Optb = _indoEuropeanIndorian.readOptionB(list.get(j));
                Optc = _indoEuropeanIndorian.readOptionC(list.get(j));
                Optd = _indoEuropeanIndorian.readOptionD(list.get(j));
                global = _indoEuropeanIndorian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch6")) {
            if (branch6 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.indoyevropeyskaya_greeks);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 23; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch6=1;
            }
            if (j == 22 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch6") && shared.getInt("Индоевропейская семья. Греческая ветвь", 0) < l)
                    editor.putInt("Индоевропейская семья. Греческая ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _indoEuropeanGreek.readQuestion(list.get(j));
                Opta = _indoEuropeanGreek.readOptionA(list.get(j));
                Optb = _indoEuropeanGreek.readOptionB(list.get(j));
                Optc = _indoEuropeanGreek.readOptionC(list.get(j));
                Optd = _indoEuropeanGreek.readOptionD(list.get(j));
                global = _indoEuropeanGreek.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch7")) {
            if (branch7 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.altay_tyurki);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 79; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch7=1;
            }
            if (j == 78 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch7") && shared.getInt("Алтайская семья. Тюрская ветвь", 0) < l)
                    editor.putInt("Алтайская семья. Тюрская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _altaiTurkic.readQuestion(list.get(j));
                Opta = _altaiTurkic.readOptionA(list.get(j));
                Optb = _altaiTurkic.readOptionB(list.get(j));
                Optc = _altaiTurkic.readOptionC(list.get(j));
                Optd = _altaiTurkic.readOptionD(list.get(j));
                global = _altaiTurkic.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch8")) {
            if (branch8 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.altay_mongoly);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 32; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch8=1;
            }
            if (j == 31 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch8") && shared.getInt("Алтайская семья. Монгольская ветвь", 0) < l)
                    editor.putInt("Алтайская семья. Монгольская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _altaiMongolian.readQuestion(list.get(j));
                Opta = _altaiMongolian.readOptionA(list.get(j));
                Optb = _altaiMongolian.readOptionB(list.get(j));
                Optc = _altaiMongolian.readOptionC(list.get(j));
                Optd = _altaiMongolian.readOptionD(list.get(j));
                global = _altaiMongolian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch9")) {
            if (branch9 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.altay_tungussko_manchzhurskaya);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 38; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch9=1;
            }
            if (j == 37 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch9") && shared.getInt("Алтайская семья. Тунгусо-манчжурская ветвь", 0) < l)
                    editor.putInt("Алтайская семья. Тунгусо-манчжурская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _altaiTungusManchurian.readQuestion(list.get(j));
                Opta = _altaiTungusManchurian.readOptionA(list.get(j));
                Optb = _altaiTungusManchurian.readOptionB(list.get(j));
                Optc = _altaiTungusManchurian.readOptionC(list.get(j));
                Optd = _altaiTungusManchurian.readOptionD(list.get(j));
                global = _altaiTungusManchurian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch10")) {
            if (branch10 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.altay_koreya);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 40; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch10=1;
            }
            if (j == 39 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch10") && shared.getInt("Алтайская семья. Корейская ветвь", 0) < l)
                    editor.putInt("Алтайская семья. Корейская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _altaiKorean.readQuestion(list.get(j));
                Opta = _altaiKorean.readOptionA(list.get(j));
                Optb = _altaiKorean.readOptionB(list.get(j));
                Optc = _altaiKorean.readOptionC(list.get(j));
                Optd = _altaiKorean.readOptionD(list.get(j));
                global = _altaiKorean.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch11")) {
            if (branch11 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.caucasus_abkhaz_adygea);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 38; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch11=1;
            }
            if (j == 37 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch11") && shared.getInt("Северо-кавказская семья. Абхазско-адыгская ветвь", 0) < l)
                    editor.putInt("Северо-кавказская семья. Абхазско-адыгская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _northCaucasianAbkhazAdyghe.readQuestion(list.get(j));
                Opta = _northCaucasianAbkhazAdyghe.readOptionA(list.get(j));
                Optb = _northCaucasianAbkhazAdyghe.readOptionB(list.get(j));
                Optc = _northCaucasianAbkhazAdyghe.readOptionC(list.get(j));
                Optd = _northCaucasianAbkhazAdyghe.readOptionD(list.get(j));
                global = _northCaucasianAbkhazAdyghe.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch12")) {
            if (branch12 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.caucasus_nakh_dagestani);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 40; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch12=1;
            } if (j == 39 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch12") && shared.getInt("Северо-кавказская семья. Нахско-дагестанская ветвь", 0) < l)
                    editor.putInt("Северо-кавказская семья. Нахско-дагестанская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _northcaucasianNakhDagestan.readQuestion(list.get(j));
                Opta = _northcaucasianNakhDagestan.readOptionA(list.get(j));
                Optb = _northcaucasianNakhDagestan.readOptionB(list.get(j));
                Optc = _northcaucasianNakhDagestan.readOptionC(list.get(j));
                Optd = _northcaucasianNakhDagestan.readOptionD(list.get(j));
                global = _northcaucasianNakhDagestan.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch13")) {
            if (branch13 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.ural_yuk_samodiytsy);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 20; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch13=1;
            }  if (j == 19 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch13") && shared.getInt("Уральско-юкагирская семья. Самодийская ветвь", 0) < l)
                    editor.putInt("Уральско-юкагирская семья. Самодийская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _uralyUkagirSamoyed.readQuestion(list.get(j));
                Opta = _uralyUkagirSamoyed.readOptionA(list.get(j));
                Optb = _uralyUkagirSamoyed.readOptionB(list.get(j));
                Optc = _uralyUkagirSamoyed.readOptionC(list.get(j));
                Optd = _uralyUkagirSamoyed.readOptionD(list.get(j));
                global = _uralyUkagirSamoyed.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch14")) {
            if (branch14 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.ural_yuk_finno_ugorskaya);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 39; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch14=1;
            }
            if (j == 38 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch14") && shared.getInt("Уральско-юкагирская семья. Финно-угорская ветвь", 0) < l)
                    editor.putInt("Уральско-юкагирская семья. Финно-угорская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _uralyUkagirFinnoUgric.readQuestion(list.get(j));
                Opta = _uralyUkagirFinnoUgric.readOptionA(list.get(j));
                Optb = _uralyUkagirFinnoUgric.readOptionB(list.get(j));
                Optc = _uralyUkagirFinnoUgric.readOptionC(list.get(j));
                Optd = _uralyUkagirFinnoUgric.readOptionD(list.get(j));
                global = _uralyUkagirFinnoUgric.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch15")) {
            if (branch15 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.ural_yuk_yukagiry);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 24; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch15=1;
            }
            if (j == 23 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch15") && shared.getInt("Уральско-юкагирская семья. Юкагирская ветвь", 0) < l)
                    editor.putInt("Уральско-юкагирская семья. Юкагирская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _uralyUkagiryUkagirskaya.readQuestion(list.get(j));
                Opta = _uralyUkagiryUkagirskaya.readOptionA(list.get(j));
                Optb = _uralyUkagiryUkagirskaya.readOptionB(list.get(j));
                Optc = _uralyUkagiryUkagirskaya.readOptionC(list.get(j));
                Optd = _uralyUkagiryUkagirskaya.readOptionD(list.get(j));
                global = _uralyUkagiryUkagirskaya.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch16")) {
            if (branch16 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.kartvelian_gruzin);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 21; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch16=1;
            } if (j == 20 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch16") && shared.getInt("Картвельская семья. Грузинская ветвь", 0) < l)
                    editor.putInt("Картвельская семья. Грузинская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _kartvelskayaGeorgian.readQuestion(list.get(j));
                Opta = _kartvelskayaGeorgian.readOptionA(list.get(j));
                Optb = _kartvelskayaGeorgian.readOptionB(list.get(j));
                Optc = _kartvelskayaGeorgian.readOptionC(list.get(j));
                Optd = _kartvelskayaGeorgian.readOptionD(list.get(j));
                global = _kartvelskayaGeorgian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch17")) {
            if (branch17 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.paleoasian_chukchi_kamchatka);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 31; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch17=1;
            }if (j == 30 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch17") && shared.getInt("Палеоазиатская семья. Чукотско-камчатская ветвь", 0) < l)
                    editor.putInt("Палеоазиатская семья. Чукотско-камчатская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _paleoAsianChukotkaKamchatka.readQuestion(list.get(j));
                Opta = _paleoAsianChukotkaKamchatka.readOptionA(list.get(j));
                Optb = _paleoAsianChukotkaKamchatka.readOptionB(list.get(j));
                Optc = _paleoAsianChukotkaKamchatka.readOptionC(list.get(j));
                Optd = _paleoAsianChukotkaKamchatka.readOptionD(list.get(j));
                global = _paleoAsianChukotkaKamchatka.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch18")) {
            if (branch18 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.paleoasian_eskimo_aleutian);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 36; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch18=1;
            }
            if (j == 35 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch18") && shared.getInt("Палеоазиатская семья. Эскимосско-алеутская ветвь", 0) < l)
                    editor.putInt("Палеоазиатская семья. Эскимосско-алеутская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _paleoAsianEskimoAleutian.readQuestion(list.get(j));
                Opta = _paleoAsianEskimoAleutian.readOptionA(list.get(j));
                Optb = _paleoAsianEskimoAleutian.readOptionB(list.get(j));
                Optc = _paleoAsianEskimoAleutian.readOptionC(list.get(j));
                Optd = _paleoAsianEskimoAleutian.readOptionD(list.get(j));
                global = _paleoAsianEskimoAleutian.readAnswer(list.get(j++));
            }
        } else if (get.equals("branch19")) {
            if (branch19 == 0) {
                RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_bg);
                CoordinatorLayout bg_image = (CoordinatorLayout) findViewById(R.id.root_bg);
                bg_image.setBackgroundResource(R.drawable.paleoasian_yenisei);
                view.setBackgroundResource(R.drawable.transluent_background);
                for (i = 1; i < 16; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                branch19=1;
            }
            if (j == 15 )
            {
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();
                if (get.equals("branch19") && shared.getInt("Палеоазиатская семья. Енисейская ветвь", 0) < l)
                    editor.putInt("Палеоазиатская семья. Енисейская ветвь", l * 10).apply();
                Intent intent = new Intent(Questions.this, Result.class);
                intent.putExtra("correct", l);
                intent.putExtra("attemp", k);
                startActivity(intent);
                finish();
            }
            else {
                Ques = _paleoAsianyEnisei.readQuestion(list.get(j));
                Opta = _paleoAsianyEnisei.readOptionA(list.get(j));
                Optb = _paleoAsianyEnisei.readOptionB(list.get(j));
                Optc = _paleoAsianyEnisei.readOptionC(list.get(j));
                Optd = _paleoAsianyEnisei.readOptionD(list.get(j));
                global = _paleoAsianyEnisei.readAnswer(list.get(j++));
            }
        }
        ques.setText("" + Ques);
        OptA.setText(Opta);
        OptB.setText(Optb);
        OptC.setText(Optc);
        OptD.setText(Optd);
    }

    @Override
    protected void onPause() {
        super.onPause();
        variable =1;
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        variable =1;
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        variable = 1;
        finish();
    }
}
