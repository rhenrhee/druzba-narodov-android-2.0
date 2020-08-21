package com.druzbanarodov.relativlayoutjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities.Altai_Navigation;
import com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities.Indoeuropean_Navigation;
import com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities.Kartvelian_Navigation;
import com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities.NorthKaukazian_Navigation;
import com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities.Paleoasian_Navigation;
import com.druzbanarodov.relativlayoutjava.indoeuropean_slavic_activities.UralUkagir_Navigation;
import com.druzbanarodov.relativlayoutjava.multyplayer.InviteFrom_Activity;

public class Navigation_Family extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nav_header_nam, nav_header_emal;

    ImageView nav_header_imag;
    public final static String Message = "com.kvikesh800gmail.relativlayoutjava.MESSAGE";
    Button ff1, ff2, ff3, ff4, ff5, ff6;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);


        //Set name,email,image in  the navigation side drawer to those we enter in the login page
        String nav_header_name = sharedPreferences.getString("name", "xyz");
        String nav_header_email = sharedPreferences.getString("email", "abc@gmail.com");
        String nav_header_gender = sharedPreferences.getString("gender", "Муж");
        View header = navigationView.getHeaderView(0);//Used to get a reference to navigation header
        nav_header_nam = (TextView) header.findViewById(R.id.nav_header_name);
        nav_header_emal = (TextView) header.findViewById(R.id.nav_header_email);
        nav_header_imag = (ImageView) header.findViewById(R.id.nav_header_image);
        nav_header_nam.setText(nav_header_name);
        nav_header_emal.setText(nav_header_email);
        if (nav_header_gender.equals("Муж")) {
            nav_header_imag.setImageResource(R.drawable.man);
        } else {
            nav_header_imag.setImageResource(R.drawable.female);
        }

        ff1 = (Button) findViewById(R.id.f1);
        ff2 = (Button) findViewById(R.id.f2);
        ff3 = (Button) findViewById(R.id.f3);
        ff4 = (Button) findViewById(R.id.f4);
        ff5 = (Button) findViewById(R.id.f5);
        ff6 = (Button) findViewById(R.id.f6);

        ff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                //progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                //progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                //progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                //progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                //progressBar.setProgress(0);//attributes
                //progressBar.setMax(100);//attributes
                //progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        //progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Navigation_Family.this, Indoeuropean_Navigation.class);
                        intent.putExtra(Message, "ff1");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        ff2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                //progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                //progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                //progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                //progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                //progressBar.setProgress(0);//attributes
                //progressBar.setMax(100);//attributes
                //progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        //progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Navigation_Family.this, Altai_Navigation.class);
                        intent.putExtra(Message, "ff2");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        ff3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                //progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                //progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                //progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                //progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                //progressBar.setProgress(0);//attributes
                //progressBar.setMax(100);//attributes
                //progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        //progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Navigation_Family.this, NorthKaukazian_Navigation.class);
                        intent.putExtra(Message, "ff3");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        ff4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                //progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                //progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                //progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                //progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                //progressBar.setProgress(0);//attributes
                //progressBar.setMax(100);//attributes
                //progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        //progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Navigation_Family.this, UralUkagir_Navigation.class);
                        intent.putExtra(Message, "ff4");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        ff5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                //progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                //progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                //progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                //progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                //progressBar.setProgress(0);//attributes
                //progressBar.setMax(100);//attributes
                //progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        //progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Navigation_Family.this, Kartvelian_Navigation.class);
                        intent.putExtra(Message, "ff5");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        ff6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);


                //progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                //progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                //progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                //progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                //progressBar.setProgress(0);//attributes
                //progressBar.setMax(100);//attributes
                //progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        //progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Navigation_Family.this, Paleoasian_Navigation.class);
                        intent.putExtra(Message, "ff5");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

    }

    //Back button DrawerMwnu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Button in toolbar - add traditional
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_traditional_button, menu);
        return true;
    }

    //onClickMethod on Menu toolbar item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(this, Add_Tradition_Activity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scorecard) {
            Intent intent = new Intent(this, ScoreCard.class);
            startActivity(intent);

        } else  if (id == R.id.nav_invites) {
            Intent intent = new Intent(this, InviteFrom_Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Setting) {
            /*  startActivity(new Intent(this,Setting.class));*/
            startActivity(new Intent(this, Setting_activity.class));

        } else if (id == R.id.nav_share) {
            //shareApplication();
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_SUBJECT, "QuizBook");
            System.out.println(""+R.string.email_content);
            intent.putExtra(Intent.EXTRA_TEXT, ""+getText(R.string.email_content)+getText(R.string.link)+getText(R.string.last_content));
            Intent chooser = Intent.createChooser(intent, "Share using");
            startActivity(chooser);


        } /*else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] recipents = {"kvikesh800@gmail.com"};
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, recipents);
            intent.putExtra(Intent.EXTRA_SUBJECT, "QuizBook Reviews");
            Intent chooser = Intent.createChooser(intent, "Send Feedback Via");
            startActivity(chooser);

        } else if (id == R.id.nav_Help) {
            Intent i = new Intent(this, Help.class);
            startActivity(i);

        } else if (id == R.id.nav_aboutus) {
            Intent i = new Intent(this, AboutUs.class);
            startActivity(i);
        }*/ else if (id == R.id.follow_youtube) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/MAEK4CENTER/"));
            startActivity(intent);
        } else if (id == R.id.follow_vk) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/dnarodov"));
            startActivity(intent);
        } else if (id == R.id.follow_instagram) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/druzhba_narodov_rossii"));
            startActivity(intent);
        } else if (id == R.id.follow_facebook) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/112529236063709/"));
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

}
