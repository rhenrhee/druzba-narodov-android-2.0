package com.druzbanarodov.relativlayoutjava.multyplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.druzbanarodov.relativlayoutjava.R;
import com.druzbanarodov.relativlayoutjava.multyplayer.gameprocess.Invites;
import com.druzbanarodov.relativlayoutjava.multyplayer.players.Players;

import java.util.Random;

public class Multyplayer_Activity  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final private int CHOOSE_PLAYER = 0; //variable return for result
    public final static String Message = "com.kvikesh800gmail.relativlayoutjava.MESSAGE";
    private static final String TAG = "" ;
    String playerOne = "";
    String playerTwo = "";
    String pl2Name = ""; //Переменная для проверки игрока, кому было отправлено приглашение
    String nickName = "";
    String email = "";
    Integer rating = 0;
    private static boolean selectedPlayer = false;
    String status = "ready";
    String role = "";
    String invAccept = "Not accepted";
    private final String Default = "N/A";
    private String categoryString = "branch1";
    private ProgressDialog progressBar;//Create a circular progressBar Dialog

    //Answers buttons
    Button btnOptionA, btnOptionB, btnOptionC, btnOptionD;

    //Invite button
    LinearLayout choose;
    Button btnInvite;
    Button f1;
    Button f2;
    Button f3;
    Button f4;
    Button f5;
    Button f6;
    Button whistle1;
    Button whistle2;
    Button whistle3;
    Button whistle4;
    Button whistle5;
    Button whistle6;
    //ready button
    Button btnP1Ready, btnP2ready;

    //Nicknames
    TextView player_one, player_two;


    static String inviteTo; // отправляем
    static String inviteFrom; // получаем



    //Create Firebase database and Reference for save players and games
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference playerRef; // data about players
    DatabaseReference gamesRef; // data about games
    DatabaseReference checkAcceptedRef; // check Accept status invite

    FirebaseDatabase databaseQuestion = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceQuestion;
    String category;
    int[] arrayQuestuion;
    String semiCategory;


    public static void setSelectedPlayer(boolean selectedPlayer) {
        Multyplayer_Activity.selectedPlayer = selectedPlayer;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multyplay_activity);
        System.out.println(inviteTo + " Проверка InviteTo");
        btnOptionA = findViewById(R.id.OptionA);
        btnOptionB = findViewById(R.id.OptionB);
        btnOptionC = findViewById(R.id.OptionC);
        btnOptionD = findViewById(R.id.OptionD);

        btnInvite = (Button)findViewById(R.id.invite_button);

        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
        f6 = findViewById(R.id.f6);
        choose = findViewById(R.id.family_choose);

        whistle1 = findViewById(R.id.whistle1);
        whistle2 = findViewById(R.id.whistle2);
        whistle3 = findViewById(R.id.whistle3);
        whistle4 = findViewById(R.id.whistle4);
        whistle5 = findViewById(R.id.whistle5);
        whistle6 = findViewById(R.id.whistle6);


        btnP1Ready = findViewById(R.id.p1_button);
        btnP2ready = findViewById(R.id.p2_button);

        player_one  = findViewById(R.id.player_one_nickname_txt);
        player_two = findViewById(R.id.player_two_nickname_txt);


        checkAcceptedRef = database.getReference("Invites");
        Invites invite = new Invites();
        role = getIntent().getStringExtra("role");
        if (role.equals("host")){
            //Getting nickname player
            getPlayerOne();
        } else {
            getPlayerTwo();
            //checkAcceptedInvite();
        }

        onClickButton();
        categoryString = "branch"+ String.valueOf( randomCategory());
        System.out.println(categoryString + " КАТЕГОРИЯ");



    }
    private int randomCategory(){
        int min = 0;
        int max = 19;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;

        return i;
    }
    private void onClickButton(){
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose.setVisibility(View.VISIBLE);
                btnInvite.setVisibility(View.INVISIBLE);
                //invitePlayers();
            }

        });
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            category ="Indoevrop";
                choose.setVisibility(View.INVISIBLE);
                whistle1.setVisibility(View.VISIBLE);
                whistle2.setVisibility(View.VISIBLE);
                whistle3.setVisibility(View.VISIBLE);
                whistle4.setVisibility(View.VISIBLE);
                whistle5.setVisibility(View.VISIBLE);
                whistle6.setVisibility(View.VISIBLE);
                whistle1.setText("Славянская ветвь");
                whistle2.setText("Армянская ветвь");
                whistle3.setText("Германская ветвь");
                whistle4.setText("Иранская ветвь");
                whistle5.setText("Индорайская ветвь");
                whistle6.setText("Греческая ветвь");




            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) { // Altayskaya
                category = "Altayskaya";
                choose.setVisibility(View.INVISIBLE);
                whistle1.setVisibility(View.VISIBLE);
                whistle2.setVisibility(View.VISIBLE);
                whistle3.setVisibility(View.VISIBLE);
                whistle4.setVisibility(View.VISIBLE);
                whistle1.setText("Тюрская ветвь");
                whistle2.setText("Монгольская ветвь");
                whistle3.setText("Тунгусо-маньжурская ветвь");
                whistle4.setText("Корейская ветвь");
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"Совсем скоро мы выпустим новые категории для вас!",Snackbar.LENGTH_LONG).show();
                /* choose.setVisibility(View.INVISIBLE);
                whistle1.setVisibility(View.VISIBLE);
                whistle2.setVisibility(View.VISIBLE);
                whistle1.setText("Абхазско-адыгская ветвь");
                whistle2.setText("Нахско-дагестанская ветвь"); */
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Совсем скоро мы выпустим новые категории для вас!",Snackbar.LENGTH_LONG).show();
               /* choose.setVisibility(View.INVISIBLE);
                whistle1.setVisibility(View.VISIBLE);
                whistle2.setVisibility(View.VISIBLE);
                whistle3.setVisibility(View.VISIBLE);
                whistle1.setText("Самодийская ветвь");
                whistle2.setText("Финно-угорская ветвь");
                whistle3.setText("Юкагирская ветвь"); */
            }
        });
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Kartavelskaya";
                Snackbar.make(v,"Совсем скоро мы выпустим новые категории для вас!",Snackbar.LENGTH_LONG).show();
                choose.setVisibility(View.INVISIBLE);
                whistle1.setVisibility(View.VISIBLE);
                whistle1.setText("Грузинская ветвь");
            }
        });
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Совсем скоро мы выпустим новые категории для вас!",Snackbar.LENGTH_LONG).show();
               /* choose.setVisibility(View.INVISIBLE);
                whistle1.setVisibility(View.VISIBLE);
                whistle2.setVisibility(View.VISIBLE);
                whistle3.setVisibility(View.VISIBLE);
                whistle1.setText("Чукотско-камчатская ветвь");
                whistle2.setText("Эскимосско-алеутская ветвь");
                whistle3.setText("Енисейская ветвь"); */
            }
        });


         /// SemiCategory
        whistle1.setOnClickListener(new View.OnClickListener() { //
            @Override
            public void onClick(View v) {
                if(category.equals("Indoevrop")){ // Armyanskaya
                    semiCategory="Slavyansk";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,58,10);
                    invitePlayers("Indoevrop",semiCategory);
                }

                if(category.equals("Altayskaya")){ //Koreyskaya
                    semiCategory="Turskya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,78 ,10);
                    invitePlayers("Altayskaya",semiCategory);
                }if(category.equals("Kartavelskaya")){
                    semiCategory="Gruzinskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,20 ,10);
                    invitePlayers("Kartavelskaya",semiCategory);
                }




                invisiblebtn();
            }
        });
        whistle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equals("Indoevrop")){
                    semiCategory="Armyanskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,23,10);
                    invitePlayers("Indoevrop",semiCategory);
                }

                if(category.equals("Altayskaya")){
                    semiCategory="Mongolskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,30,10);
                    invitePlayers("Altayskaya",semiCategory);
                }

                invisiblebtn();

            }
        });
        whistle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equals("Indoevrop")){
                    semiCategory="Germanskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,24,10);
                    invitePlayers("Indoevrop",semiCategory);
                }

                if(category.equals("Altayskaya")){
                    semiCategory="TungusoManshurskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,37,10);
                    invitePlayers("Altayskaya",semiCategory);
                }

                invisiblebtn();

            }
        });
        whistle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equals("Indoevrop")){
                    semiCategory="Iranskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,46,10);
                    invitePlayers("Indoevrop",semiCategory);
                }

                if(category.equals("Altayskaya")){
                    semiCategory="Koreyskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,39,10);
                    invitePlayers("Altayskaya",semiCategory);
                }

                invisiblebtn();

            }
        });
        whistle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equals("Indoevrop")){
                    semiCategory="IndoAriyskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,7,7);
                    invitePlayers("Indoevrop",semiCategory);
                }


                invisiblebtn();

            }
        });
        whistle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category.equals("Indoevrop")){
                    semiCategory="Grecheskaya";
                    arrayQuestuion = sampleRandomNumbersWithoutRepetition(0,21,10);
                    invitePlayers("Indoevrop",semiCategory);
                }


              invisiblebtn();
            }
        });

        btnP1Ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                      //  progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds

                        Intent intent = new Intent(Multyplayer_Activity.this, MultiplyerQuestions.class);
                        intent.putExtra(Message, categoryString);//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        intent.putExtra("namePlayer", pl2Name);
                        startActivity(intent);
                    }
                }, 2000);
                btnInvite.setVisibility(View.INVISIBLE);
                btnP1Ready.setVisibility(View.INVISIBLE);
                btnP2ready.setVisibility(View.INVISIBLE);

            }
        });
        btnP2ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                      //  progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(Multyplayer_Activity.this, MultiplyerQuestions.class);
                        intent.putExtra(Message, categoryString);//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE

                        intent.putExtra("namePlayer", getIntent().getStringExtra("inviteTo"));
                       startActivity(intent);
                    }
                }, 0);
                btnP1Ready.setVisibility(View.INVISIBLE);
                btnP2ready.setVisibility(View.INVISIBLE);
                btnOptionA.setVisibility(View.INVISIBLE);
                btnOptionB.setVisibility(View.INVISIBLE);
                btnOptionC.setVisibility(View.INVISIBLE);
                btnOptionD.setVisibility(View.INVISIBLE);

            }
        });

        setQustion();
    }

    private void invisiblebtn(){
        whistle1.setVisibility(View.INVISIBLE);
        whistle2.setVisibility(View.INVISIBLE);
        whistle3.setVisibility(View.INVISIBLE);
        whistle4.setVisibility(View.INVISIBLE);
        whistle5.setVisibility(View.INVISIBLE);
        whistle6.setVisibility(View.INVISIBLE);
    }
    private void setQustion(){
        }
    public static int[] sampleRandomNumbersWithoutRepetition(int start, int end, int count) {
        Random rng = new Random();

        int[] result = new int[count];
        int cur = 0;
        int remaining = end - start;
        for (int i = start; i < end && count > 0; i++) {
            double probability = rng.nextDouble();
            if (probability < ((double) count) / (double) remaining) {
                count--;
                result[cur++] = i;
            }
            remaining--;
        }
        return result;
    }
    private void waiting(){
        final Handler handler =new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("Проверка");
                //Intent start to open the navigation drawer activity
                while (true) {
                    if (invAccept.equals("Accepted")) {
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 60 seconds
                        //getPlayerTwo();
                        handler.removeCallbacks(null);

                    }
                    System.out.println(invAccept);

                }
            }
        });
    }

    //Function getting data from user and set this data how Player 1
    private void getPlayerOne(){
        //Get current user name(nickName) from Content_main config file
        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        nickName = sharedPreferences.getString("name", Default);
        email = sharedPreferences.getString("email", Default);

        if (nickName.equals("")) {
            //playerRef.setValue("");
            writeNewPlayerOne(nickName, email, 0, status, role);
            Log.d(nickName, Default);
        } else {
            playerRef = database.getReference("Players");
            //playerRef.setValue(nickName);
            status = "not ready";
            role = "host";
            //Write user how Player 1
            writeNewPlayerOne(nickName, email, rating, status, role);
            player_one.setText(nickName);
            Log.d(nickName, Default);
        }

    }

    //Write new player to database
    private void writeNewPlayerOne(String nickname, String email, Integer rating, String status, String role) {
        Players player_one = new Players(nickname, email, rating, status, role);
        playerRef.child("Players_one").child(nickname).setValue(player_one);
    }

    private void getPlayerTwo(){
        playerOne = getIntent().getStringExtra("inviteAcceptFrom");
        playerTwo = getIntent().getStringExtra("inviteTo");
        player_one.setText(playerOne);
        player_two.setText(playerTwo);
        btnInvite.setVisibility(View.INVISIBLE);
        btnP2ready.setVisibility(View.VISIBLE);
        //checkAcceptedInvite();
        //Log.d(playerOne, "Empty");
    }

    //Call list activity for inviting user to game
    private void invitePlayers(String category,String branch){
        MultiplyerQuestions.setRole("host");
        Invite_Activity.setCategory(arrayQuestuion,category,semiCategory);
        Intent intent = new Intent(Multyplayer_Activity.this, Invite_Activity.class);
        intent.putExtra("Family",category);
        intent.putExtra("Branch",branch);
        intent.putExtra("Array",arrayQuestuion);

        SharedPreferences sPref = getSharedPreferences("Content_main",MODE_PRIVATE);
        String savedText = sPref.getString("name", "");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference invitesRef;
        invitesRef = database.getReference("Invites");
        System.out.println(savedText + " ПРОВЕРКА ЕБУЧЕГО ТЕКСТА");
        // invitesRef.child("InviteTo" + savedText).child("Family").setValue((category));
       // invitesRef.child("InviteTo" + savedText).child("Branch").setValue((branch));
        startActivityForResult(intent, CHOOSE_PLAYER);
        btnInvite.setVisibility(View.INVISIBLE);
        //player_two.setText("Ожидание игрока");
    }

    private void checkAcceptedInvite(){
        // Здесь прописать проверку БД
        checkAcceptedRef = FirebaseDatabase.getInstance().getReference();
        checkAcceptedRef.child("Invites").child("InviteTo" + pl2Name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Invites invite = dataSnapshot.getValue(Invites.class);
                if (invite != null) {
                    String invTo = invite.inviteTo;
                    String invFrom = invite.inviteFrom;
                    String invAccepted = invite.inviteAcceptStatus;
                    MultiplyerQuestions.setName(invTo);
                    invAccept = invAccepted;
                    if (invAccept.equals("Accepted")) {
                        player_two.setText(invTo);
                        btnP1Ready.setVisibility(View.VISIBLE);
                    } else {
                        //player_one.setText(playerOne);
                        btnInvite.setVisibility(View.VISIBLE);
                    }

                    Log.d(invTo, "Error");
                    Log.d(invFrom, "Error");
                    Log.d(invAccepted, "Error");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_PLAYER) {
            if (resultCode == RESULT_OK) {
                pl2Name = data.getStringExtra(Invite_Activity.PLAYER2NAME);
                checkAcceptedInvite();
                Log.d(pl2Name, "Empty");
            }else {
                String pl2Name = ""; // стираем текст
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
