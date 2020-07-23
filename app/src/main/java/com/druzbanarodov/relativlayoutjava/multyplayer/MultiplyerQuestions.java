package com.druzbanarodov.relativlayoutjava.multyplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.druzbanarodov.relativlayoutjava.Navigation_Activity;
import com.druzbanarodov.relativlayoutjava.R;

import java.util.ArrayList;
import java.util.Random;

//import android.support.design.widget.CoordinatorLayout;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;

public class MultiplyerQuestions extends AppCompatActivity  {
    DonutProgress donutProgress;
    int variable =0;
    TextView ques;
    Button OptA, OptB, OptC, OptD;
    Button play_button;
    String get;
    CardView cardResultFistPlayer;
    //Objects of different classes
    ArrayList<Integer> questionNumbers = new ArrayList<>();
    static String family;
    static String branch;
    FirebaseDatabase firebaseQuestion;
    DatabaseReference referenceQuestion;
    TextView resultPlayerOne;
    TextView resultPlayerTwo;
    int i = 0;
    long endOnePlayer = 0;
    long endTwoPlayer = 0;
    boolean flag;
    boolean flag2 = true;
    static String role;
    int resultPlayerOneFB = 0;
    int resultPlayerTwoFB = 0;
    long resultIntentOne ;
    long resultIntentTwo;
    static String playerNameOne;
    static String playerNameTwo;
    TextView namePlayerOne;
    TextView namePlayerTwo;
    public  static String name;


    public static void setNames(String nameOne,String nameTwo){
        playerNameOne = nameOne;
        playerNameTwo = nameTwo;
    }
    public static void setName(String names){
        name = names;
    }
    public static void setRole(String rol){
        role = rol;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplyer_qustions);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        namePlayerOne = findViewById(R.id.name_playerOne);
        namePlayerTwo = findViewById(R.id.name_playerTwo);
        namePlayerOne.setText(playerNameOne);
        namePlayerTwo.setText(playerNameTwo);
        Intent intent = getIntent();//recieving the intent send by the Navigation activity
        get = intent.getStringExtra(Navigation_Activity.Message);//converting that intent message to string using the getStringExtra() method
        //attribute of the circular progress bar
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setMax(100);
        donutProgress.setFinishedStrokeColor(Color.parseColor("#FFFB385F"));
        donutProgress.setTextColor(Color.parseColor("#FFFB385F"));
        donutProgress.setKeepScreenOn(true);
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);
        resultPlayerOne = (TextView) findViewById(R.id.player_one_nickname_txt);
        resultPlayerTwo = (TextView) findViewById(R.id.player_two_nickname_txt);
        cardResultFistPlayer = (CardView) findViewById(R.id.result_fist_player);

        //Now the linking of all the datbase files with the Question activity

//Индо-Европейская семья славянская ветвь


        //Till here we are linking the database file
        OptA = (Button) findViewById(R.id.OptionA);
        OptB = (Button) findViewById(R.id.OptionB);
        OptC = (Button) findViewById(R.id.OptionC);
        OptD = (Button) findViewById(R.id.OptionD);
        ques = (TextView) findViewById(R.id.Questions);
        play_button = (Button) findViewById(R.id.play_button);//Play button to start the game
        onClicker();

        init();
        i=0;
        // playerResult();
    }
    public void init () {
        final SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);

        //showing the buttons which were previously invisible
        OptA.setVisibility(View.VISIBLE);
        ques.setVisibility(View.VISIBLE);
        OptB.setVisibility(View.VISIBLE);
        OptC.setVisibility(View.VISIBLE);
        OptD.setVisibility(View.VISIBLE);
        play_button.setVisibility(View.GONE);
        //  donutProgress.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        firebaseQuestion = FirebaseDatabase.getInstance();
        //  String name = getIntent().getStringExtra("namePlayer");
        SharedPreferences sPref;
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString("SAVED_TEXT", "");

        referenceQuestion = firebaseQuestion.getReference("Invites").child("InviteTo" + name);

        referenceQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue() + " VALUE");

                family = dataSnapshot.child("family").getValue(String.class);
                branch = dataSnapshot.child("branch").getValue(String.class);
                if((dataSnapshot.child("resultPlayerOneFB").exists())) {
                    resultPlayerOne.setText(dataSnapshot.child("resultPlayerOneFB").getValue().toString());
                    resultPlayerTwo.setText(dataSnapshot.child("resultPlayerTwoFB").getValue().toString());
                    resultIntentOne = (long) dataSnapshot.child("resultPlayerOneFB").getValue();
                    resultIntentTwo = (long) dataSnapshot.child("resultPlayerTwoFB").getValue();
                    endOnePlayer = (long) dataSnapshot.child("endOnePlayer").getValue();
                    endTwoPlayer = (long) dataSnapshot.child("endTwoPlayer").getValue();
                }
                if(endTwoPlayer == 1 & endOnePlayer ==1){
                    System.out.println(" ЗАРАБОТАЛА БЛЯТЬ");
                    Intent intent = new Intent(MultiplyerQuestions.this,ResultQuest.class);
                    intent.putExtra("OnePlayer",playerNameOne);
                    intent.putExtra("TwoPlayer",playerNameTwo);
                    intent.putExtra("resultOne",resultIntentOne);
                    System.out.println( resultIntentOne + " " +resultIntentTwo + "проверка результата");
                    intent.putExtra("resultTwo",resultIntentTwo);
                    intent.putExtra("Role",role);
                    startActivity(intent);
                }


                int k = 0;
                if(questionNumbers.isEmpty()){
                    while (dataSnapshot.child("Question " + k).exists() == true) {
                        questionNumbers.add(Integer.parseInt(dataSnapshot.child("Question " + k).getValue().toString()));
                        k++;
                    }
                }
                System.out.println(questionNumbers);
                firstLoadQuestion();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void playerResult(){

        firebaseQuestion = FirebaseDatabase.getInstance();
        //String name = getIntent().getStringExtra("namePlayer");
        referenceQuestion = firebaseQuestion.getReference("Invites").child("InviteTo" + name);

        //referenceQuestion.child("resultPlayerOneFB").setValue(resultPlayerOneFB);
        //referenceQuestion.child("resultPlayerTwoFB").setValue(resultPlayerTwoFB);

        if(role.equals("host")){
            referenceQuestion.child("resultPlayerOneFB").setValue(resultPlayerOneFB);
        }else{
            referenceQuestion.child("resultPlayerTwoFB").setValue(resultPlayerTwoFB);
        }

    }

    String question;
    String correctAnswer;
    String wrong1;
    String wrong2;
    String wrong3;


    private void firstLoadQuestion(){
        firebaseQuestion = FirebaseDatabase.getInstance();
        System.out.println(family + " " + branch + " что то там");
        if(family!=null) {
            referenceQuestion = firebaseQuestion.getReference("QuestCategories").child(family).child(branch).child("0");
        }
        referenceQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                correctAnswer =  dataSnapshot.child(questionNumbers.get(i).toString()).child("CorrectAnswer").getValue().toString();
                question = dataSnapshot.child(questionNumbers.get(i).toString()).child("Question").getValue().toString();
                wrong1 = dataSnapshot.child(questionNumbers.get(i).toString()).child("WrongAnswer").getValue().toString();
                wrong2 = dataSnapshot.child(questionNumbers.get(i).toString()).child("WrongAnswer_1").getValue().toString();
                wrong3 = dataSnapshot.child(questionNumbers.get(i).toString()).child("WrongAnswer_2").getValue().toString();
                System.out.println(question + "  " + correctAnswer + "  " + wrong1 + "  " + wrong2 + "  " + wrong3);
                if(flag2 ==true) {
                    visibilityTextOnButtonQuestion();
                    flag2 =false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(i<questionNumbers.size() && flag ==true) {
            i++;
            System.out.println("I == " + i);
            flag = false;

        }
        System.out.println(i + " i " + questionNumbers + " questionNumbers");
    if(i==questionNumbers.size()){
        referenceQuestion = firebaseQuestion.getReference("Invites").child("InviteTo" + name);
        i=0;
        System.out.println(role + "role");
        if(role.equals("host")){
            endOnePlayer = 1;
            referenceQuestion.child("endOnePlayer").setValue(endOnePlayer);
            OptA.setVisibility(View.INVISIBLE);
            ques.setVisibility(View.INVISIBLE);
            OptB.setVisibility(View.INVISIBLE);
            OptC.setVisibility(View.INVISIBLE);
            OptD.setVisibility(View.INVISIBLE);
            cardResultFistPlayer.setVisibility(View.VISIBLE);

        }if(role.equals("client")){
            endTwoPlayer = 1;
            referenceQuestion.child("endTwoPlayer").setValue(endTwoPlayer);
            OptA.setVisibility(View.INVISIBLE);
            ques.setVisibility(View.INVISIBLE);
            OptB.setVisibility(View.INVISIBLE);
            OptC.setVisibility(View.INVISIBLE);
            OptD.setVisibility(View.INVISIBLE);
            cardResultFistPlayer.setVisibility(View.VISIBLE);
        }

    }

    }


    ArrayList<String> questionList;
    private void visibilityTextOnButtonQuestion(){
        int a[] = {0,1,2,3};

        questionList = new ArrayList<>();
        questionList.clear();
        questionList.add(correctAnswer);
        questionList.add(wrong1);
        questionList.add(wrong2);
        questionList.add(wrong3);

        Random rnd = new Random();
        for (int i = 1; i < a.length; i++) {
            int j = rnd.nextInt(i);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        ques.setText(question);
        OptA.setText(questionList.get(a[0]));
        OptB.setText(questionList.get(a[1]));
        OptC.setText(questionList.get(a[2]));
        OptD.setText(questionList.get(a[3]));
    }


    public void onClicker() {
        OptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag =true;
                flag2 = true;
                if(OptA.getText().equals(correctAnswer)){
                    Snackbar.make(v,"Правильный ответ!",Snackbar.LENGTH_SHORT).show();
                    if(role.equals("host")){
                        resultPlayerOneFB++;
                        playerResult();


                    }if(role.equals("client")){
                        resultPlayerTwoFB++;
                        playerResult();

                    }
                    firstLoadQuestion();
                }else{
                    playerResult();
                    Snackbar.make(v,"Неправильный ответ!",Snackbar.LENGTH_SHORT).show();
                    firstLoadQuestion();

                }
            }
        });

        OptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag =true;
                flag2 = true;
                if(OptB.getText().equals(correctAnswer)){
                    Snackbar.make(v,"Правильный ответ!",Snackbar.LENGTH_SHORT).show();
                    if(role.equals("host")){
                        resultPlayerOneFB++;
                        playerResult();


                    }if(role.equals("client")){
                        resultPlayerTwoFB++;
                        playerResult();

                    }
                    firstLoadQuestion();
                }else{
                    playerResult();
                    Snackbar.make(v,"Неправильный ответ!",Snackbar.LENGTH_SHORT).show();
                    firstLoadQuestion();

                }
            }
        });

        OptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag =true;
                flag2 = true;
                if(OptC.getText().equals(correctAnswer)){
                    Snackbar.make(v,"Правильный ответ!",Snackbar.LENGTH_SHORT).show();
                    if(role.equals("host")){
                        resultPlayerOneFB++;
                        playerResult();

                    }if(role.equals("client")){
                        resultPlayerTwoFB++;
                        playerResult();

                    }
                    firstLoadQuestion();
                }else{
                    playerResult();
                    Snackbar.make(v,"Неправильный ответ!",Snackbar.LENGTH_SHORT).show();
                    firstLoadQuestion();

                }
            }

        });

        OptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag =true;
                flag2 = true;
                if(OptD.getText().equals(correctAnswer)){
                    Snackbar.make(v,"Правильный ответ!",Snackbar.LENGTH_SHORT).show();
                    if(role.equals("host")){
                        resultPlayerOneFB++;
                        playerResult();


                    }if(role.equals("client")){
                        resultPlayerTwoFB++;
                        playerResult();

                    }
                    firstLoadQuestion();
                }else{
                    playerResult();
                    Snackbar.make(v,"Неправильный ответ!",Snackbar.LENGTH_SHORT).show();

                    firstLoadQuestion();
                }
            }
        });

    }
    private void point(){

        referenceQuestion.child("resultPlayerOneFB").setValue(resultPlayerOneFB);
        referenceQuestion.child("resultPlayerTwoFB").setValue(resultPlayerTwoFB);
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
