package com.druzbanarodov.relativlayoutjava.multyplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.druzbanarodov.relativlayoutjava.Navigation_Nome_Menu;
import com.druzbanarodov.relativlayoutjava.R;

public class ResultQuest extends AppCompatActivity {

    TextView namePlayerOne;
    TextView namePlayerTwo;
    TextView scorePlayerOne;
    TextView scorePlayerTwo;
    ImageView win;
    ImageView loos;

    TextView winText;
    TextView winText2;
    TextView loosText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button button;

    String onePlayer ;
    String twoPlayer;
    int resultOne ;
    int resultTwo;
    String role ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_result_quest);
        namePlayerOne = findViewById(R.id.player1);
        namePlayerTwo = findViewById(R.id.player2);
        scorePlayerOne = findViewById(R.id.player1Score);
        scorePlayerTwo = findViewById(R.id.player2Score);

        button = findViewById(R.id.buttonBack);

        winText = findViewById(R.id.winner);
        winText2 = findViewById(R.id.winner2);

        loosText = findViewById(R.id.looser);

        win = findViewById(R.id.onePlace);
        loos = findViewById(R.id.twoPlace);


      onePlayer = getIntent().getStringExtra("OnePlayer");
      twoPlayer = getIntent().getStringExtra("TwoPlayer");
      resultOne = (int) getIntent().getLongExtra("resultOne",0);
      resultTwo = (int) getIntent().getLongExtra("resultTwo",0);
      role = getIntent().getStringExtra("Role");


        namePlayerTwo.setText(twoPlayer);
        namePlayerOne.setText(onePlayer);
        scorePlayerOne.setText(String.valueOf(resultOne));
        System.out.println(resultOne);
        scorePlayerTwo.setText(String.valueOf(resultTwo));
        System.out.println(resultTwo  + " Результат");
        if (role.equals("host")) {
            if (resultOne > resultTwo) {
                winText.setVisibility(View.VISIBLE);
                winText2.setVisibility(View.VISIBLE);
                win.setVisibility(View.VISIBLE);
            } else {
                loosText.setVisibility(View.VISIBLE);
                loos.setVisibility(View.VISIBLE);
            }
        }
        if (role.equals("client")) {
            if (resultTwo > resultOne) {
                winText.setVisibility(View.VISIBLE);
                winText2.setVisibility(View.VISIBLE);
                win.setVisibility(View.VISIBLE);
            } else {
                loosText.setVisibility(View.VISIBLE);
                loos.setVisibility(View.VISIBLE);
            }

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultQuest.this, Navigation_Nome_Menu.class);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        database();
    }


    private void database(){
        DatabaseReference inviteFromRef;
        inviteFromRef = FirebaseDatabase.getInstance().getReference();
        inviteFromRef.child("Invites").child("InviteTo" + twoPlayer).removeValue();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultQuest.this, Navigation_Nome_Menu.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
