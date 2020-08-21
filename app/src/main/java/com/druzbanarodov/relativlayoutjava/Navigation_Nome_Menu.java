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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.druzbanarodov.relativlayoutjava.multyplayer.InviteFrom_Activity;
import com.druzbanarodov.relativlayoutjava.multyplayer.Multyplayer_Activity;

public class Navigation_Nome_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nav_header_nam, nav_header_emal;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference invitesToRef;
    DatabaseReference   inviteFromRef;
    ImageView nav_header_imag;
    public final static String Message = "com.kvikesh800gmail.relativlayoutjava.MESSAGE";
    String role = "host";
    Button single_player_btn, multiplayer_btn, show_nations_btn;
    private ProgressDialog progressBar;
    String inviteFrom;
    String inviteTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_2level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        single_player_btn = (Button) findViewById(R.id.button_single_player);
        multiplayer_btn = (Button) findViewById(R.id.button_multiplayer);
        show_nations_btn = (Button) findViewById(R.id.button_show_nations);

        single_player_btn.setOnClickListener(new View.OnClickListener() {
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
                        Intent intent = new Intent(Navigation_Nome_Menu.this, Navigation_Family.class);
                        intent.putExtra(Message, "single_player_btn");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        multiplayer_btn.setOnClickListener(new View.OnClickListener() {
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
                        Intent intent = new Intent(Navigation_Nome_Menu.this, Multyplayer_Activity.class);
                        intent.putExtra("role", "host");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE

                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        show_nations_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fadn.gov.ru/news/2015/07/27/2335-v-rossii-prozhivaet-193-naroda"));
                startActivity(intent);
            }
        });

        inviteFromRef = FirebaseDatabase.getInstance().getReference();
        inviteTo = sharedPreferences.getString("name", "Default");
        invitesToRef = database.getReference("Invites");
        invitesToRef.child("InviteTo" + inviteTo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("gameInvite").exists()) {
                    if (dataSnapshot.child("gameInvite").getValue().equals("invite")) {
                        System.out.println(" ОТРАБОТАЛ");
                        invite();
                    }
                    if (dataSnapshot.child("inviteFrom").exists()){
                     inviteFrom =   dataSnapshot.child("inviteFrom").getValue().toString();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void invite(){
        Toast.makeText(this,"У вас новое приглашения в игру от:" + inviteFrom,Toast.LENGTH_LONG).show();
        inviteFromRef.child("Invites").child("InviteTo" + inviteTo).child("gameInvite").setValue("0");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scorecard) {
            Intent intent = new Intent(this, ScoreCard.class);
            startActivity(intent);

        } else if (id == R.id.nav_Setting) {
            /*  startActivity(new Intent(this,Setting.class));*/
            startActivity(new Intent(this, Setting_activity.class));

        } else  if (id == R.id.nav_invites) {
                Intent intent = new Intent(this, InviteFrom_Activity.class);
                startActivity(intent);

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
