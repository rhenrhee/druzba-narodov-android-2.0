package com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.druzbanarodov.relativlayoutjava.Questions;
import com.druzbanarodov.relativlayoutjava.R;

public class Altai_Navigation extends AppCompatActivity {
    TextView nav_header_nam, nav_header_emal;
    ImageView nav_header_imag;
    public final static String Message = "com.kvikesh800gmail.relativlayoutjava.MESSAGE";
    Button branch7, branch8, branch9, branch10;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altai_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        branch7 = (Button) findViewById(R.id.branch7);
        branch8 = (Button) findViewById(R.id.branch8);
        branch9 = (Button) findViewById(R.id.branch9);
        branch10 = (Button) findViewById(R.id.branch10);


        branch7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage("Идет загрузка вопросов ...");//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Altai_Navigation.this, Questions.class);
                        intent.putExtra(Message, "branch7");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        branch8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage("Идет загрузка вопросов ...");//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Altai_Navigation.this, Questions.class);
                        intent.putExtra(Message, "branch8");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        branch9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage("Идет загрузка вопросов ...");//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Altai_Navigation.this, Questions.class);
                        intent.putExtra(Message, "branch9");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        branch10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage("Идет загрузка вопросов ...");//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Altai_Navigation.this, Questions.class);
                        intent.putExtra(Message, "branch10");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

    }
}
